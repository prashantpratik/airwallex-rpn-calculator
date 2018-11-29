package com.airwallex.rpn.calculator.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RpnCalculatorControllerTest {
    RpnCalculatorController calculator = new RpnCalculatorController();

    @Test
    public void testOperators() throws Exception {
        calculator.run("5 2");
        assertEquals(2, calculator.getNumberStack().peek().intValue());
        assertEquals(2, calculator.getNumberStack().size(), 0);

        // subtraction
        calculator.run("clear");
        calculator.run("5 2 -");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(3, calculator.getNumberStack().peek().intValue());
        calculator.run("3 -");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(0, calculator.getNumberStack().peek().intValue());

        // negative
        calculator.run("clear");
        calculator.run("1 2 3 4 5 *");
        assertEquals(4, calculator.getNumberStack().size());
        calculator.run("clear 3 4 -");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(-1, calculator.getNumberStack().peek().intValue());


        // division
        calculator.run("clear");
        calculator.run("7 12 2 /");
        assertEquals(6, calculator.getNumberStack().peek().intValue());
        calculator.run("*");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(42, calculator.getNumberStack().peek().intValue());
        calculator.run("4 /");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(10.5, calculator.getNumberStack().peek().doubleValue(), 1);

        //multiplication
        calculator.run("clear");
        calculator.run("1 2 3 4 5");
        calculator.run("* * * *");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(120, calculator.getNumberStack().peek().intValue());

    }

    @Test
    public void testSqrt() throws Exception {
        calculator.run("2 sqrt");
        calculator.run("clear 9 sqrt");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(3, calculator.getNumberStack().peek().intValue());
    }

    @Test
    public void testInsufficientParams() {
        try {
            calculator.run("1 2 3 * 5 + * * 6 5");
        } catch (CalculatorException ex) {
            assertEquals("operator * (position: 8): insufficient parameters", ex.getMessage());
        }
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(11, calculator.getNumberStack().peek().intValue(), 0);
    }

    @Test
    public void testUndo() throws CalculatorException {
        calculator.run("5 4 3 2");
        assertEquals(4, calculator.getNumberStack().size());
        calculator.run("undo undo *");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(20, calculator.getNumberStack().peek().intValue(), 0);
        calculator.run("5 *");
        assertEquals(1, calculator.getNumberStack().size());
        assertEquals(100, calculator.getNumberStack().peek().intValue(), 0);
        calculator.run("undo");
        assertEquals(2, calculator.getNumberStack().size());
        assertEquals(5, calculator.getNumberStack().peek().intValue(), 0);
        calculator.run("+ undo - undo / undo * undo sqrt undo sqrt undo");
        assertEquals(2, calculator.getNumberStack().size());
        assertEquals(5, calculator.getNumberStack().peek().intValue(), 1);
    }

    @Test(expected = CalculatorException.class)
    public void testOnlyOperators() throws CalculatorException {
        calculator.run("+ +");
    }

    @Test(expected = CalculatorException.class)
    public void testNoSpaces() throws CalculatorException {
        calculator.run("22+");
    }

    @Test(expected = CalculatorException.class)
    public void testNoSpaces2() throws CalculatorException {
        calculator.run("2 2+ 3");
    }

    @Test(expected = CalculatorException.class)
    public void testDivideByZero() throws CalculatorException {
        calculator.run("1 0 /");
    }

    @Test(expected = CalculatorException.class)
    public void testNullInput() throws CalculatorException {
        calculator.run(null);
    }
}
