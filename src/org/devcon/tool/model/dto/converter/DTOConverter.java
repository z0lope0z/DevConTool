package org.devcon.tool.model.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.devcon.tool.model.EventMember;
import org.devcon.tool.model.Member;
import org.devcon.tool.model.dto.MemberDTO;

import com.google.appengine.api.datastore.KeyFactory;

/**
 * Class helper for converting Models into Data transfer objects
 * @author lemano
 *
 */
public class DTOConverter {

    /**
     * Converts a member into a DTO
     * @param member
     * @return
     */
    public final static MemberDTO convert(Member member){
        List<EventMember> eventMemberList = member.getEventMemberListRef().getModelList();
        String[] eventNames = new String[eventMemberList.size()];
        
        int i=0;
        for(EventMember eventMember: eventMemberList){
            eventNames[i] = eventMember.getEventRef().getModel().getName();
            i++;
        }
        
        MemberDTO memberDTO = new MemberDTO(KeyFactory.keyToString(member.getKey()), member.getName(), member.getEmail().getEmail(), eventNames);
        
        return memberDTO;
    }
    
    /**
     * Converts a list of Members into a list of DTOs
     * @param memberList
     * @return
     */
    public final static List<MemberDTO> convert(List<Member> memberList){
        List<MemberDTO> memberDTOList = new ArrayList<MemberDTO>();
        for (Member member : memberList){
            memberDTOList.add(convert(member));
        }
        return memberDTOList;
    }
}
