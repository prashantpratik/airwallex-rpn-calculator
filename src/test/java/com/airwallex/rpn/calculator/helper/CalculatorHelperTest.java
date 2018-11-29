package com.airwallex.rpn.calculator.helper;

import com.airwallex.rpn.calculator.impl.CalculatorException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;

public class CalculatorHelperTest {
    @Test
    public void testParseInput() {
        assertEquals(3, CalculatorHelper.parseInput("1 2 3", " ").size());
    }

    @Test
    public void testParseInput1() {
        assertEquals(6, CalculatorHelper.parseInput("1 2 3 * / +", " ").size());
    }

    @Test
    public void testIsNumeric() {
        assertTrue(CalculatorHelper.isNumeric("5"));
    }

    @Test
    public void testIsNumeric1() {
        assertFalse(CalculatorHelper.isNumeric("/"));
    }

    @Test(expected = CalculatorException.class)
    public void testCheckStack() throws CalculatorException {
        CalculatorHelper.checkStack(new ArrayDeque<>(), 0, "");
    }

    @Test(expected = CalculatorException.class)
    public void testCheckStack1() throws CalculatorException {
        Deque<BigDecimal> deque = new ArrayDeque<>();
        deque.add(new BigDecimal(10));
        CalculatorHelper.checkStack(deque, 0, "/");
    }

    @Test
    public void testCheckStack2() throws CalculatorException {
        Deque<BigDecimal> deque = new ArrayDeque<>();
        deque.add(new BigDecimal(10));
        CalculatorHelper.checkStack(deque, 0, "SQRT");
    }
}
