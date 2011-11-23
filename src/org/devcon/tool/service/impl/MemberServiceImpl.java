package org.devcon.tool.service.impl;

import java.util.List;
import java.util.Map;

import org.devcon.tool.helper.Converter;
import org.devcon.tool.meta.MemberMeta;
import org.devcon.tool.model.Member;
import org.devcon.tool.service.MemberService;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Transaction;

public class MemberServiceImpl extends GenericServiceImpl<Member, String> implements MemberService{

    public MemberServiceImpl() {
        super(Member.class, new MemberMeta());
    }
    
    @Override
    public Member save(Map<String, Object> input) throws Exception{
        Member member = new Member();
        String name = (String) input.get("name");
        String email = (String) input.get("email");
        member.setName(name);
        try {
            member.setEmail(Converter.stringToEmail(email));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(member);
        tx.commit();
        return member;
    }
    
    public List<Member> getListByEmail(Email email) {
        MemberMeta memberMeta = MemberMeta.get();
        List<Member> memberList = (List<Member>) Datastore.query(memberMeta).filter(memberMeta.email.greaterThanOrEqual(email)).sort(memberMeta.email.asc).asList();
        return memberList;
    }

    public List<Member> getListByName(String name) {
        MemberMeta memberMeta = MemberMeta.get();
        List<Member> memberList = (List<Member>) Datastore.query(memberMeta).filter(memberMeta.name.startsWith(name)).sort(memberMeta.name.asc).asList();
        return memberList;
    }

    public List<Member> getListByNameAndEmail(String name, Email email) {
        MemberMeta memberMeta = MemberMeta.get();
        List<Member> memberList = (List<Member>) Datastore.query(memberMeta).filter(memberMeta.name.greaterThanOrEqual(name), memberMeta.email.greaterThanOrEqual(email)).sort(memberMeta.name.asc).asList();
        return memberList;
    }

    public Member save(Member member) {
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(member);
        tx.commit();
        return member;
    }

}
