package org.devcon.tool.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.devcon.tool.exception.EmailFormatException;
import org.devcon.tool.helper.Converter;
import org.devcon.tool.meta.UploadedDataFragmentMeta;
import org.devcon.tool.meta.UploadedDataMeta;
import org.devcon.tool.model.Event;
import org.devcon.tool.model.EventMember;
import org.devcon.tool.model.Member;
import org.devcon.tool.model.UploadedData;
import org.devcon.tool.model.UploadedDataFragment;
import org.devcon.tool.service.impl.EventMemberServiceImpl;
import org.devcon.tool.service.impl.EventServiceImpl;
import org.devcon.tool.service.impl.MemberServiceImpl;
import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.util.ByteUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class UploadService {

    private static final int FRAGMENT_SIZE = 900000;
    private static final int MEMBER_NUMBER = 0;
    private static final int LAST_NAME = 1;
    private static final int FIRST_NAME = 2;
    private static final int EMAIL = 3;

    private UploadedDataMeta d = UploadedDataMeta.get();
    private UploadedDataFragmentMeta f = UploadedDataFragmentMeta.get();

    private MemberService memberService = new MemberServiceImpl();
    private EventService eventService = new EventServiceImpl();
    private EventMemberService eventMemberService =
        new EventMemberServiceImpl();

    public List<UploadedData> getDataList() {
        return Datastore.query(d).asList();
    }

    private void createEventMember(String name, String emailString, Key eventKey) {
        Member member = new Member();
        Event event = new Event();
        EventMember eventMember = new EventMember();

        member.setName(name);

        try {
            member.setEmail(Converter.stringToEmail(emailString));
        } catch (EmailFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        event = eventService.get(KeyFactory.keyToString(eventKey));
        member = memberService.save(member);
        eventMember.getMemberRef().setModel(member);
        eventMember.getEventRef().setModel(event);

        try {
            eventMemberService.save(eventMember);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Algorithm for inserting values in a spreadsheet to the datastore
     * 
     * @param inputStream
     * @throws Exception
     */
    private void convertSpreadsheet(InputStream inputStream, Key eventKey)
            throws Exception {
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        
        for (Member member : memberService.getAll()){
            memberService.remove(KeyFactory.keyToString(member.getKey()));
        }
        // Iterate over each row in the sheet
        Iterator rows = sheet.rowIterator();

        int cellCount = 0;
        int rowCount = 0;
        boolean stop = false;
        while ((rows.hasNext()) && (stop == false)) {
            HSSFRow row = (HSSFRow) rows.next();
            System.out.println("Row #" + row.getRowNum());

            String name = "";
            String emailString = "";
            
            // Iterate over each cell in the row and print out the cell's
            // content
            Iterator cells = row.cellIterator();

            while ((cells.hasNext()) && (stop == false)) {
                HSSFCell cell = (HSSFCell) cells.next();
                System.out.println("Cell #" + cell.getCellNum());
                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    System.out.println("Value Numeric= "
                        + cell.getNumericCellValue());
                } else {
                    System.out.println("Value String= "
                        + cell.getStringCellValue());
                }
                switch (cell.getCellNum()) {
                case MEMBER_NUMBER:
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        if ((cell.getStringCellValue() == "")
                            || (cell.getStringCellValue() == null)) {
                            stop = true;
                            break;
                        } else if (cell.getStringCellValue().equals("END")) {
                            stop = true;
                            break;
                        }
                    } 
                    break;
                case LAST_NAME:
                    System.out.println(name + cell.getCellType());
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        name = name + cell.getStringCellValue();
                    } else {
                        name =
                            name + String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case FIRST_NAME:
                    name = name + ", " + cell.getStringCellValue();
                    break;
                case EMAIL:
                    emailString = cell.getStringCellValue();
                }

                cellCount += 1;
            }

            if (row.getRowNum() != 0 && stop == false) {
                System.out.println("Creating " + name + " " + emailString);
                createEventMember(name, emailString, eventKey);
            }
            rowCount += 1;
        }
    }

    public UploadedData upload(FileItem formFile, Key eventKey) {
        if (formFile == null) {
            return null;
        }
        List<Object> models = new ArrayList<Object>();
        UploadedData data = new UploadedData();
        models.add(data);
        data.setKey(Datastore.allocateId(d));
        data.setFileName(formFile.getShortFileName());
        data.setLength(formFile.getData().length);
        byte[] bytes = formFile.getData();
        byte[][] bytesArray = ByteUtil.split(bytes, FRAGMENT_SIZE);
        Iterator<Key> keys =
            Datastore
                .allocateIds(data.getKey(), f, bytesArray.length)
                .iterator();

        InputStream input = new ByteArrayInputStream(bytes);
        try {
            convertSpreadsheet(input, eventKey);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < bytesArray.length; i++) {
            byte[] fragmentData = bytesArray[i];
            UploadedDataFragment fragment = new UploadedDataFragment();
            models.add(fragment);
            fragment.setKey(keys.next());
            fragment.setBytes(fragmentData);
            fragment.setIndex(i);
            fragment.getUploadDataRef().setModel(data);
        }
        Transaction tx = Datastore.beginTransaction();
        for (Object model : models) {
            // Datastore.put(tx, model);
        }
        tx.commit();
        return data;
    }

    public UploadedData getData(Key key, Long version) {
        return Datastore.get(d, key, version);
    }

    public byte[] getBytes(UploadedData uploadedData) {
        if (uploadedData == null) {
            throw new NullPointerException(
                "The uploadedData parameter must not be null.");
        }
        List<UploadedDataFragment> fragmentList =
            uploadedData.getFragmentListRef().getModelList();
        byte[][] bytesArray = new byte[fragmentList.size()][0];
        for (int i = 0; i < fragmentList.size(); i++) {
            bytesArray[i] = fragmentList.get(i).getBytes();
        }
        return ByteUtil.join(bytesArray);
    }

    public void delete(Key key) {
        Transaction tx = Datastore.beginTransaction();
        List<Key> keys = new ArrayList<Key>();
        keys.add(key);
        keys.addAll(Datastore.query(f, key).asKeyList());
        Datastore.delete(tx, keys);
        tx.commit();
    }
}
