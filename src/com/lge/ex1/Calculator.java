package com.lge.ex1;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

// SUT: System Under Test
// CUT(Class or Code Under Test)
public class Calculator {
    private int value;

//    public Calculator(int value) {
//        value = value;
//    }

    public void add(int a) {
        value += a;
    }

    public void sub(int a) {
        value -= a;
    }

    public int getValue() {
        // return 0;
        return value;
    }

    // Junit 4는 중첩 클래스에 대해서 TestSuite class를 지원하지 않았습니다.
    // @Nested - Inner class
    @Nested
    class TestSuite {
        // 제품 코드 안에서 Test 코드를 사용할 수 있다는 장점이 있다.
        @Test
        public void testAdd() {
            Calculator calculator = new Calculator();
        }
    }

    @Nested
    class TestSuite2 {
        // 제품 코드 안에서 Test 코드를 사용할 수 있다는 장점이 있다.
        @Test
        public void testAdd() {
            Calculator calculator = new Calculator();
        }
    }

    public static boolean isPrime(int value) {
        for (int i = 2; i < value; ++i) {
            if (value % i == 0)
                return false;
        }

        return true;
    }
}
