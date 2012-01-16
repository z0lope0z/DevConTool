package org.devcon.tool.controller.member;

import java.util.ArrayList;

import org.devcon.tool.meta.EventMeta;
import org.devcon.tool.model.Event;
import org.devcon.tool.model.EventMember;
import org.devcon.tool.model.Member;
import org.devcon.tool.service.EventMemberService;
import org.devcon.tool.service.EventService;
import org.devcon.tool.service.MemberService;
import org.devcon.tool.service.impl.EventMemberServiceImpl;
import org.devcon.tool.service.impl.EventServiceImpl;
import org.devcon.tool.service.impl.MemberServiceImpl;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddMemberController extends Controller {

    private MemberService memberService = new MemberServiceImpl();
    private EventService eventService = new EventServiceImpl();
    private EventMemberService eventMemberService = new EventMemberServiceImpl();
    private EventMeta eventMeta = EventMeta.get();
    
    /**
     * Adds a member object and its corresponding event to the view
     * @param stringKey
     */
    private void fillForm(String stringKey){
        Member member = new Member();
        if (stringKey != null){
            if (!stringKey.equals("")){
                try {
                    member = memberService.get(stringKey);
                    requestScope("memberEventList", eventMeta.modelsToJson(eventService.getEventList(member)));
                } catch (Exception e) {
                    member = new Member();
                    requestScope("memberEventList", new ArrayList<EventMember>());
                }
            }
        }
        requestScope("member", member);
    }
    
    /**
     * Sets the new member's event  
     * @param stringEventKey
     * @param member
     */
    private void saveEventMember(String[] stringEventKeys, Member member){
        EventMember eventMember = new EventMember();
        for (String stringEventKey: stringEventKeys){
            eventMember = new EventMember();
            Event event = eventService.get(stringEventKey);
            eventMember.getMemberRef().setModel(member);
            eventMember.getEventRef().setModel(event);
            try {
                eventMemberService.save(eventMember);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public Navigation run() throws Exception {
        String stringKey = (String) request.getAttribute("key");
        String submitForm = (String) request.getAttribute("saveOrUpdate");
        String[] stringEventKey = (String[]) request.getParameterValues("event");
        fillForm(stringKey);

        requestScope("eventList", eventService.getAll());
        response.getWriter().write(eventMeta.modelsToJson(eventService.getAll()));
        
        if (submitForm != null){
            Member member = memberService.save(new RequestMap(request));
            saveEventMember(stringEventKey, member);
            if (member.getKey() != null){
                return redirect("searchMember");
            } else { 
                return redirect("searchMember");
            }
        }
        return forward("addMember.jsp");
    }
}
