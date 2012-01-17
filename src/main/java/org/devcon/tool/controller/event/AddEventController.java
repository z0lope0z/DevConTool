package org.devcon.tool.controller.event;

import org.apache.commons.lang.StringUtils;
import org.devcon.tool.model.Event;
import org.devcon.tool.service.EventService;
import org.devcon.tool.service.impl.EventServiceImpl;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddEventController extends Controller {

    private EventService eventService = new EventServiceImpl();

    /**
     * Adds a event object to the view
     * 
     * @param stringKey
     */
    private void fillForm(String stringKey) {
        Event event = new Event();
        if (StringUtils.isNotEmpty(stringKey)) {
            try {
                event = eventService.get(stringKey);
            } catch (Exception e) {
                event = new Event();
            }
        }
        requestScope("event", event);
    }

    @Override
    public Navigation run() throws Exception {
        String stringKey = (String) request.getAttribute("key");
        String submitForm = (String) request.getAttribute("saveOrUpdate");
        fillForm(stringKey);
        if (StringUtils.isNotEmpty(submitForm)) {
            eventService.save(new RequestMap(request));
            return redirect("searchEvent");
        }
        return forward("addEvent.jsp");
    }
}
