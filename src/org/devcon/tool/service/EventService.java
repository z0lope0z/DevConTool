package org.devcon.tool.service;

import java.util.List;

import org.devcon.tool.model.Event;
import org.devcon.tool.model.Member;

public interface EventService extends GenericService<Event, String>{
    public List<Event> getListByName(String name);
    public List<Event> getEventList(Member member);
}
