package org.devcon.tool.controller.member;

import java.util.List;

import org.devcon.tool.model.Member;
import org.devcon.tool.model.dto.MemberDTO;
import org.devcon.tool.model.dto.converter.DTO2FILEConverter;
import org.devcon.tool.model.dto.converter.DTOConverter;
import org.devcon.tool.service.EventService;
import org.devcon.tool.service.MemberService;
import org.devcon.tool.service.impl.EventServiceImpl;
import org.devcon.tool.service.impl.MemberServiceImpl;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.datastore.Email;

public class SearchMemberController extends Controller {

    private MemberService memberService = new MemberServiceImpl();
    private EventService eventService = new EventServiceImpl();
    
    private List<Member> searchFilter(String nameQuery, String emailQuery){
        if (((nameQuery != null) && (emailQuery != "")) && (nameQuery != "") && (emailQuery !=null)){
            return memberService.getListByNameAndEmail(nameQuery, new Email(emailQuery));
        } else if ((nameQuery != null) && (nameQuery != "")){
            return memberService.getListByName(nameQuery);
        } else if (emailQuery != null){
            return memberService.getListByEmail(new Email(emailQuery));
        } else {
            return memberService.getAll();
        }
    }
    
    public void deleteMembers(String[] deleteKeys){
        for(String stringKey : deleteKeys){
            memberService.remove(stringKey);
        }
    }
    
    @Override
    public Navigation run() throws Exception {
        String nameQuery = (String) request.getAttribute("name");
        String emailQuery = (String) request.getAttribute("email");
        String modify = (String) request.getAttribute("modify");
        if (modify != null){
            if (modify.equals("delete")) {
                String[] deleteKeys = (String[]) request.getParameterValues("delete[]");
                if (deleteKeys.length > 0){
                    deleteMembers(deleteKeys);
                }
            } else if (modify.equals("edit") || modify.equals("add")){
                return redirect("addMember?key=" + request.getParameter("edit[]"));
            }
        }
        requestScope("eventList", eventService.getAll());
        List<Member> memberList = searchFilter(nameQuery, emailQuery);
        requestScope("memberList",DTOConverter.convert(memberList) );
        download("members.xls", createSpreadsheet(DTOConverter.convert(memberList)));
        return forward("searchMember.jsp");
    }

    private byte[] createSpreadsheet(List<MemberDTO> memberDTOs){
        return DTO2FILEConverter.convertToSpreadsheet(memberDTOs, MemberDTO.NAME, MemberDTO.EMAIL, MemberDTO.EVENTS);
    }

}
