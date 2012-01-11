package org.devcon.tool.model.dto;

public class MemberDTO {
    
    public static final int NAME = 1;
    public static final int EMAIL = 2;
    public static final int EVENTS = 3;
    
    private String key;
    private String name;
    private String email;
    private String[] events;

    public MemberDTO(String key, String name, String email, String[] events) {
        this.key = key;
        this.name = name;
        this.email = email;
        this.events = events;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getEvents() {
        return events;
    }

    public void setEvents(String[] events) {
        this.events = events;
    }

}
