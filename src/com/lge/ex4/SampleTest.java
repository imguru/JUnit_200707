package com.lge.ex4;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SampleTest {
    // 2. 비기능 테스트(속도)
    //  : 기능적으로 잘 동작할 뿐 아니라, 테스트의 수행 시간에 대한 부분을 검증하는 작업

    // 아래의 테스트가 1초 안에 수행되어야 한다.
    @Test(timeout = 1000)
    public void slowJobTest() throws Exception {
        SUT.slowJob();
    }

    // 1. 예외 테스트(Exception Test)
    // : parseInt에 대해서 잘못된 문자열을 전달하였을 경우, NumberFormatException 예외가 발생하는지
    //   여부를 검증하고 싶다.
    @Test(expected = NumberFormatException.class)
    public void parseIntTest() throws Exception {
        String badNumberStr = "x10";

        SUT.parseInt(badNumberStr);
    }

    @Test
    public void parseIntTest_JUnit3() throws Exception {
        String badNumberStr = "x10";

        try {
            SUT.parseInt(badNumberStr);
            fail("예외가 발생하지 않았음.");
        } catch (NumberFormatException e) {
            // Succeed
        } catch (Exception e) {
            fail("다른 종류의 예외가 발생하였음.");
        }
    }
}

class SUT {
    public static void slowJob() throws Exception {
        TimeUnit.SECONDS.sleep(2);
    }

    public static Integer parseInt(String value) throws Exception {
        // throw new IOException("123");
        return Integer.parseInt(value);
    }
}
