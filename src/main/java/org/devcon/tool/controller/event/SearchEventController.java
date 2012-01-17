package org.devcon.tool.controller.event;

import java.util.List;

import org.devcon.tool.model.Event;
import org.devcon.tool.service.EventService;
import org.devcon.tool.service.impl.EventServiceImpl;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class SearchEventController extends Controller {
    private EventService eventService = new EventServiceImpl();
    
    private List<Event> searchFilter(String nameQuery){
        if ((nameQuery != null) && (nameQuery != "")){
            return eventService.getListByName(nameQuery);
        } else {
            return eventService.getAll();
        }
    }
    
    public void deleteEvents(String[] deleteKeys){
        for(String stringKey : deleteKeys){
            eventService.remove(stringKey);
        }
    }
    
    @Override
    public Navigation run() throws Exception {
        String nameQuery = (String) request.getAttribute("name");
        String modify = (String) request.getAttribute("modify");
        if (modify != null){
            if (modify.equals("delete")) {
                String[] deleteKeys = (String[]) request.getParameterValues("delete[]");
                if (deleteKeys.length > 0){
                    deleteEvents(deleteKeys);
                }
            } else if (modify.equals("edit")){
                return redirect("addEvent");
            }
        }
        requestScope("eventList", searchFilter(nameQuery));
        return forward("searchEvent.jsp");
    }
}