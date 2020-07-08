package com.lge.ex2;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


// TestMethodOrder
//  1. @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//     - @Order(N)
//  2. @TestMethodOrder(MethodOrderer.Random.class)
//  3. @TestMethodOrder(MethodOrderer.Alphanumeric.class)


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class SampleTest {
    @Test
    public void foo() {
    }

    @Test
    public void goo() {

    }

    @Test
    public void hoo() {

    }

    @Test
    public void foo1() {
    }

    @Test
    public void goo1() {

    }

    @Test
    public void hoo1() {

    }
}

/*
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SampleTest {
    @Test
    @Order(3)
    public void foo() {
    }

    @Test
    @Order(2)
    public void goo() {

    }

    @Test
    @Order(1)
    public void hoo() {

    }
}
*/
