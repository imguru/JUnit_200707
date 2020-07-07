package com.lge.ex3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalTest {

    private Terminal terminal;
    private static final String TEST_ID = "test_id";
    private static final String TEST_PASSWORD = "test_password";


    @Before
    public void setUp() throws Exception {
        terminal = new Terminal();
        terminal.connect();
    }

    @After
    public void tearDown() throws Exception {
        terminal.disconnect();
    }

    @Test
    public void loginTest() throws Exception {
        terminal.login(TEST_ID, TEST_PASSWORD);

        assertTrue("로그인하였을 때", terminal.isLogin());
    }

    @Test
    public void logoutTest() throws Exception {
        terminal.login(TEST_ID, TEST_PASSWORD);
        terminal.logout();

        assertFalse("로그아웃하였을 때", terminal.isLogin());
    }
}


/*
public class TerminalTest {
    @Test
    public void loginTest() throws Exception {
        Terminal terminal = new Terminal();
        terminal.connect();
        String testId = "test_id";
        String testPassword = "test_password";

        terminal.login(testId, testPassword);

        assertTrue("로그인하였을 때", terminal.isLogin());

        terminal.disconnect();
    }

    @Test
    public void logoutTest() throws Exception {
        Terminal terminal = new Terminal();
        terminal.connect();
        String testId = "test_id";
        String testPassword = "test_password";

        terminal.login(testId, testPassword);
        terminal.logout();

        assertFalse("로그아웃하였을 때", terminal.isLogin());

        terminal.disconnect();
    }
}
*/

//  ------- SUT -------
class Terminal {
    public void connect() throws Exception {

    }

    public void disconnect() throws Exception {

    }

    public void login(String id, String password) {

    }

    public void logout() {

    }

    public boolean isLogin() {
        return true;
    }
}