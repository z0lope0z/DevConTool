package org.devcon.tool.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class EventMemberController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("EventMember.jsp");
    }
}
