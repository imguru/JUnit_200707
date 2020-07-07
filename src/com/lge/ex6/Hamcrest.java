package com.lge.ex6;

// Hamcrest Matcher
// : 비교 표현의 확장 라이브러리
//  - 테스트 단언문을 작성할 때, 문맥적으로 자연스러운 문장으로 만들 수 있도록 해준다.
//  - jMock 라이브러리에서 독립해서, jUnit 4.4 이후로 정식 포함되었습니다.
//  * JUnit 5에서는 Hamcrest 라이브러리가 기본적으로 포함되어 있지 않습니다.

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class Hamcrest {
    @Test
    public void test1() throws Exception {
        Bank bank = new Bank();

        // assertEquals("예금을 꺼냈을 때", 100, bank.getBalance());
        assertThat(bank.getBalance(), is(equalTo(100)));
    }

    @Test
    public void test2() throws Exception {
        Bank bank = new Bank();

        // assertNotNull(actual);
        assertThat(bank.newBank(), is(notNullValue()));
    }

    @Test
    public void test3() throws Exception {
        Bank bank = new Bank();

        // assertTrue(bank.getUserName().contains("guest"));
        assertThat(bank.getUserName(), containsString("guest"));
    }
}


class Bank {
    public int getBalance() {
        return 0;
    }

    public Bank newBank() {
        return null;
    }

    public String getUserName() {
        return "";
    }
}