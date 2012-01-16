package org.devcon.tool.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MemberTest extends AppEngineTestCase {

    private Member model = new Member();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
