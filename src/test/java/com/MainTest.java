package com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
    private Main calculator;

    @BeforeEach
    void setUp() {
        calculator = new Main();
    }

    @Test
    void testAddPositiveNumbers() {
        // 测试正常情况：两个正数相加
        assertEquals(10, calculator.add(5, 5));
        assertEquals(0, calculator.add(0, 0));
        assertEquals(100, calculator.add(99, 1));
    }

    @Test
    void testAddWithNegativeNumber() {
        // 测试负数输入时抛出异常
        IllegalArgumentException exception1 = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add(-1, 5)
        );
        assertEquals("Both numbers must be non-negative.", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add(5, -1)
        );
        assertEquals("Both numbers must be non-negative.", exception2.getMessage());
    }

    @Test
    void testAddWithOverflow() {
        // 测试整数溢出时抛出异常
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> calculator.add(Integer.MAX_VALUE, 1)
        );
        assertTrue(exception.getMessage().contains("Integer overflow"));
    }

    @Test
    void testAddWithZero() {
        // 测试边界值：与0相加
        assertEquals(5, calculator.add(5, 0));
        assertEquals(5, calculator.add(0, 5));
    }

    @Test
    void testAddLargeNumbers() {
        // 测试大数相加（不溢出的情况）
        int largeNumber = Integer.MAX_VALUE - 100;
        assertEquals(Integer.MAX_VALUE - 50, calculator.add(largeNumber, 50));
    }
}
