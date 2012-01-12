package org.devcon.tool.service.impl;

import java.util.List;

import org.devcon.tool.meta.EventMemberMeta;
import org.devcon.tool.model.Event;
import org.devcon.tool.model.EventMember;
import org.devcon.tool.model.Member;
import org.devcon.tool.service.EventMemberService;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

public class EventMemberServiceImpl extends GenericServiceImpl<EventMember, String> implements EventMemberService{
    
    public EventMemberServiceImpl() {
        super(EventMember.class, new EventMemberMeta());
    }

    public EventMember save(EventMember eventMember) throws Exception{
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(eventMember);
        tx.commit();
        return eventMember;
    }
    
    /**
     * @param Member
     * @return List of event members
     */
    public List<EventMember> getEventMemberList(Member member) throws Exception {
        EventMemberMeta eventMemberMeta = EventMemberMeta.get();
        List<EventMember> eventMemberList = (List<EventMember>) Datastore.query(eventMemberMeta).filter(eventMemberMeta.memberRef.equal(member.getKey())).asList();
        return eventMemberList;
    }
    
    public List<EventMember> getEventMemberList(Event event) throws Exception {
        EventMemberMeta eventMemberMeta = EventMemberMeta.get();
        List<EventMember> eventMemberList = (List<EventMember>) Datastore.query(eventMemberMeta).filter(eventMemberMeta.eventRef.equal(event.getKey())).asList();
        return eventMemberList;
    }
    
}