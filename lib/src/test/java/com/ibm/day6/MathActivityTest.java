package com.ibm.day6;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class MathActivityTest {
    /**
     * Test addition
     */
    @Test
    void exec001() {
        // Check if 5 + 2 equals 7
        assertEquals(7, MathActivity.add(5, 2));
    }
    /**
     * Test subtraction
     */
    @Test
    void exec002() {
        // Check if 5 - 2 equals 3
        assertEquals(3, MathActivity.subtract(5, 2));
    }
    /**
     * Test multiplication
     */
    @Test
    void exec003() {
        // Check if 5 * 2 equals 10
        assertEquals(10, MathActivity.multiply(5, 2));
    }
    /**
     * Test division
     */
    @Test
    void exec004() {
        // Check if 5 / 2 equals 2.5
        assertEquals(2.5f, MathActivity.divide(5, 2));
    }
    /**
     * Test divide by zero
     */
    @Test
    void exec005() {
        // Check if dividing by zero throws ArithmeticException
        assertThrows(ArithmeticException.class, () -> MathActivity.divide(5, 0));
    }
}