package org.devcon.tool.controller.member;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UploadSpreadsheetControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/member/uploadSpreadsheet");
        UploadSpreadsheetController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/member/uploadSpreadsheet.jsp"));
    }
}
