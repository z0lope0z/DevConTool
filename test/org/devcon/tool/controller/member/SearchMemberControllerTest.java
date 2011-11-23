package org.devcon.tool.controller.member;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SearchMemberControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/member/searchMember");
        SearchMemberController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/member/searchMember.jsp"));
    }
}
