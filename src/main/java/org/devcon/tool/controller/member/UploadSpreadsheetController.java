package org.devcon.tool.controller.member;

import org.devcon.tool.service.EventService;
import org.devcon.tool.service.UploadService;
import org.devcon.tool.service.impl.EventServiceImpl;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import com.google.appengine.api.datastore.KeyFactory;

public class UploadSpreadsheetController extends Controller {

    private UploadService service = new UploadService();
    private EventService eventService = new EventServiceImpl();
    
    /**
     * Adds a list of event objects to the view
     * @param stringKey
     */
    private void fillForm(){
        requestScope("eventList", eventService.getAll());
    }
    
    @Override
    public Navigation run() throws Exception {
        FileItem formFile = requestScope("formFile");
        String eventKey = (String) request.getAttribute("event");
        if (eventKey != null && eventKey != ""){
            service.upload(formFile, KeyFactory.stringToKey(eventKey));
            return redirect("searchMember");
        }
        
        fillForm();
        return forward("uploadSpreadsheet.jsp");
    }
}
