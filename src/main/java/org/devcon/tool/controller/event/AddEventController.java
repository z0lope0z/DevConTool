package org.devcon.tool.controller.event;

import org.devcon.tool.model.Event;
import org.devcon.tool.service.EventService;
import org.devcon.tool.service.impl.EventServiceImpl;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddEventController extends Controller {
    
    private EventService eventService =  new EventServiceImpl();
    
    /**
     * Adds a event object to the view
     * @param stringKey
     */
    private void fillForm(String stringKey){
        Event event = new Event();
        if (stringKey != null){
            if (!stringKey.equals("")){
                try {
                    event = eventService.get(stringKey);
                } catch (Exception e) {
                    event = new Event();
                }
            }
        }
        requestScope("event", event);
    }
    
    @Override
    public Navigation run() throws Exception {
        String stringKey = (String) request.getAttribute("key");
        String submitForm = (String) request.getAttribute("saveOrUpdate");
        fillForm(stringKey);
        if (submitForm != null){
            Event event = eventService.save(new RequestMap(request));
            if (event.getKey() != null){
                return redirect("searchEvent");
            } else { 
                return redirect("searchEvent");
            }
        }
        return forward("addEvent.jsp");
    }
}
