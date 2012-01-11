package org.devcon.tool.service;

import java.util.List;

import org.devcon.tool.model.EventMember;
import org.devcon.tool.model.Member;

public interface EventMemberService extends GenericService<EventMember, String>{
    public EventMember save(EventMember eventMember) throws Exception;
    public List<EventMember> getEventMemberList(Member member) throws Exception;
}
