package com.lge.ex2;

import com.lge.ex1.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void testSub() throws Exception {
        // Given
        Calculator calculator = new Calculator();
        // When
        calculator.sub(2);
        // Then
        assertEquals(-2, calculator.getValue());
    }

    @Test
    public void testAdd() throws Exception {
        Calculator calculator = new Calculator();

        calculator.add(2);
        calculator.add(2);

        assertEquals("2 + 2 하였을 때", 4, calculator.getValue());
    }
}
