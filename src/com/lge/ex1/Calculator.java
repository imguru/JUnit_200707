package com.lge.ex1;

// SUT: System Under Test
// CUT(Class or Code Under Test)
public class Calculator {
    private int value;

    public Calculator() {
        value = 0;
    }

    public void add(int a) {
        value += a;
    }

    public void sub(int a) {
        value -= a;
    }

    public int getValue() {
        return 0;
        // return value;
    }
}
