package org.devcon.tool.controller;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class EventMemberControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/EventMember");
        EventMemberController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/EventMember.jsp"));
    }
}
