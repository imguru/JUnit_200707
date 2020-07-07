package com.lge.ex3;

import org.junit.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

// 테스트 픽스쳐의 설치와 해체로 인해서 '느린 테스트의 문제'가 발생한다.
//  : Slow Test
//  => 테스트를 실행하는 속도가 느려서, 개발자들이 코드가 변경되어도 테스트를 수행하지 않는다.
//     테스트를 실행하는 개발자의 생산성을 떨어뜨린다.

// 어떻게 해결해야 할까요?
// => Suite Fixture Setup / TearDown
//  : xUnit Test Framework은 Test Suite 단위로 setUp() / tearDown() 할 수 있는 기능을 제공하고 있습니다.

// => 이제는 '신선한 픽스쳐 전략' 이 아닌 '공유 픽스쳐의 전략' 입니다.
//  : 공유되는 테스트 픽스쳐가 이전 테스트에 의해 망가질 경우, 이후의 테스트의 신뢰성이 보장될 수 없다. => '변덕스러운 테스트'
//  - xUnit Test Pattern에서는 공유 픽스쳐의 전략을 사용할 경우, 하나의 TestSuite 안에서 너무 많은 TC가 존재하지 않도록 제어해야 한다.

/*

TestSuite setUp
TerminalTest ts = new TerminalTest();
ts.setUp();
ts.loginTest();
ts.tearDown();

TerminalTest ts = new TerminalTest();
ts.setUp();
ts.loginTest();
ts.tearDown();

TerminalTest ts = new TerminalTest();
ts.setUp();
ts.loginTest();
ts.tearDown();
TestSuite teardown

*/

public class TerminalTest {
    private static Terminal terminal;
    private static final String TEST_ID = "test_id";
    private static final String TEST_PASSWORD = "test_password";

    public TerminalTest() {
        System.out.println("TerminalTest()");
    }

    @BeforeClass // static
    public static void setUpTestSuite() throws Exception {
        System.out.println("setUpTestSuite()");
        terminal = new Terminal();
        terminal.connect();
    }

    @AfterClass // static
    public static void tearDownTestSuite() throws Exception {
        System.out.println("tearDownTestSuite()");
        terminal.disconnect();
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp()");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown()");
    }

    @Test
    public void loginTest() throws Exception {
        System.out.println("loginTest()");
        terminal.login(TEST_ID, TEST_PASSWORD);

        assertTrue("로그인하였을 때", terminal.isLogin());
    }

    @Test
    public void logoutTest() throws Exception {
        System.out.println("logoutTest()");
        terminal.login(TEST_ID, TEST_PASSWORD);
        terminal.logout();

        assertFalse("로그아웃하였을 때", terminal.isLogin());
    }

    @Test
    public void foo() throws Exception {

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
// 가정: connect() / disconnect()이 시간이 걸리는 작업이다.
class Terminal {
    public void connect() throws Exception {
        TimeUnit.SECONDS.sleep(2);
    }

    public void disconnect() throws Exception {
        TimeUnit.SECONDS.sleep(1);
    }

    public void login(String id, String password) {

    }

    public void logout() {

    }

    public boolean isLogin() {
        return true;
    }
}