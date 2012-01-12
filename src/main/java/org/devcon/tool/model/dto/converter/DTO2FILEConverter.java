package org.devcon.tool.model.dto.converter;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.devcon.tool.model.dto.MemberDTO;

/**
 * Converts a list of members into CSV or Spreadsheet
 * @author lemano
 *
 */
public class DTO2FILEConverter {
    
    /**
     * Converts a list of memberDTOs into a spreadsheet
     * @param memberDTOs
     * @param columns 
     * <p>Each column corresponds to a property of the memberDTO.
     * Please use the constant values found the the memberDTO class.</p>
     * @return byte[]
     * <p>A spreadsheet in the form of a byte[].</p>
     */
    public final static byte[] convertToSpreadsheet(List<MemberDTO> memberDTOs, int ... columns ){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        int rownum = 0;
        for (MemberDTO memberDTO : memberDTOs){
            HSSFRow row = sheet.createRow(rownum);
            int cellnum = 0;
            for (int column : columns){
                HSSFCell cell = row.createCell(cellnum);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                switch (column) {
                case MemberDTO.NAME:
                    cell.setCellValue(new HSSFRichTextString(memberDTO.getName()));
                    break;
                case MemberDTO.EMAIL:
                    cell.setCellValue(new HSSFRichTextString(memberDTO.getEmail()));
                    break;
                case MemberDTO.EVENTS:
                    String stringEventCell = "";
                    for (String stringEvent : memberDTO.getEvents()){
                        stringEventCell += stringEventCell + stringEvent + ",";
                    }
                    cell.setCellValue(new HSSFRichTextString(stringEventCell));
                    break;
                default:
                    break;
                }
                // 50 characters divided by 1/20th of a point
                sheet.setColumnWidth(cellnum + 1, (int) (50 * 8 / 0.05));
                cellnum++;
            }
            rownum++;
        }
        wb.setSheetName(0, "Members");
        
        return wb.getBytes();
    }
}
