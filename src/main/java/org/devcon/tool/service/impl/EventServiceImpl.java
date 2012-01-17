package org.devcon.tool.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.devcon.tool.meta.EventMeta;
import org.devcon.tool.model.Event;
import org.devcon.tool.model.EventMember;
import org.devcon.tool.model.Member;
import org.devcon.tool.service.EventMemberService;
import org.devcon.tool.service.EventService;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class EventServiceImpl extends GenericServiceImpl<Event, String> implements EventService{
    
    private EventMemberService eventMemberService = new EventMemberServiceImpl();
    
    public EventServiceImpl() {
        super(Event.class, new EventMeta());
    }

    @Override
    public Event save(Map<String, Object> input) throws Exception{
        Event event = new Event();
        String name = (String) input.get("name");
        String keyString = (String) input.get("key");
        Key key = null;
        System.out.println("=============================================");
        if (StringUtils.isNotEmpty(keyString)){
            key = KeyFactory.stringToKey(keyString);
            event.setKey(key);
        }
        event.setName(name);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(event);
        tx.commit();
        return event;
    }
    
    public List<Event> getListByName(String name) {
        EventMeta eventMeta = EventMeta.get();
        List<Event> eventList = (List<Event>) Datastore.query(eventMeta).filter(eventMeta.name.startsWith(name)).sort(eventMeta.name.asc).asList();
        return eventList;
    }
    
    public List<Event> getEventList(Member member){
        List<Event> eventList = new ArrayList<Event>();
        try {
            List<EventMember> eventMemberList = eventMemberService.getEventMemberList(member);
            for (EventMember eventMember : eventMemberList){
                eventList.add(get(KeyFactory.keyToString(eventMember.getEventRef().getKey())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventList;
    }

}
