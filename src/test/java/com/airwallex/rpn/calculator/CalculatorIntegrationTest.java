package com.airwallex.rpn.calculator;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CalculatorIntegrationTest {
    OutputStream os;

    @Before
    public void setup() {
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @Test
    public void testMain1() {
        String data = "5 4 3 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CalculatorMain.main(new String[]{});
        assertEquals("Please input data:\n" +
                "Stack: [5, 4, 3, 2]\n", os.toString());
    }

    @Test
    public void testMain2() {
        String data = "clear 2 sqrt";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CalculatorMain.main(new String[]{});
        assertEquals("Please input data:\n" +
                "Stack: [1.4142135624]\n", os.toString());
    }

    @Test
    public void testClear() {
        String data = "5 2 -\n3 -\nclear";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CalculatorMain.main(new String[]{});
        assertEquals("Please input data:\n" +
                "Stack: [3]\n" +
                "Stack: [0]\n" +
                "Stack: []\n", os.toString());
    }

    @Test
    public void testInsufficientParams() {
        String data = "clear 1 2 3 * 5 + * * 6 5";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CalculatorMain.main(new String[]{});
        assertEquals("Please input data:\n" +
                "operator * (position: 9): insufficient parameters\n" +
                "Stack: [11]\n", os.toString());
    }

    @Test
    public void testUndo() {
        String data = "clear 5 4 3 2\nundo undo *\n5 *\nundo";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CalculatorMain.main(new String[]{});
        assertEquals("Please input data:\n" +
                "Stack: [5, 4, 3, 2]\n" +
                "Stack: [20]\n" +
                "Stack: [100]\n" +
                "Stack: [20, 5]\n", os.toString());
    }

    @Test
    public void testDivideByZeroError() {
        String data = "clear 1 0 /";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        CalculatorMain.main(new String[]{});
        assertEquals("Please input data:\n" +
                "Divide by Zero Error\n" +
                "Stack: [1]\n", os.toString());
    }
}
