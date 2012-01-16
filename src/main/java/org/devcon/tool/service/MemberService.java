package org.devcon.tool.service;

import java.util.List;

import org.devcon.tool.model.Member;

import com.google.appengine.api.datastore.Email;

public interface MemberService extends GenericService<Member, String>{
    public Member save(Member member);
    public List<Member> getListByName(String name);
    public List<Member> getListByEmail(Email email);
    public List<Member> getListByNameAndEmail(String name, Email email);
}
