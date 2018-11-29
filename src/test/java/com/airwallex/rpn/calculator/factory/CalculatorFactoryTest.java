package com.airwallex.rpn.calculator.factory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CalculatorFactoryTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testGetRobot() {
        assertNotNull(CalculatorFactory.getCalculator());
    }

}
