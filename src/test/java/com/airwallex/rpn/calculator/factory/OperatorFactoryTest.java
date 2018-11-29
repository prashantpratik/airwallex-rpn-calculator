package com.airwallex.rpn.calculator.factory;

import com.airwallex.rpn.calculator.impl.CalculatorException;
import com.airwallex.rpn.calculator.opeartions.BaseOperator;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.assertTrue;

public class OperatorFactoryTest {

    @Test
    public void testAddOperation() {
        assertTrue(OperatorFactory.getOperationInstance("+") instanceof BaseOperator);
    }

    @Test
    public void testSubtractOperation() {
        assertTrue(OperatorFactory.getOperationInstance("-") instanceof BaseOperator);
    }

    @Test
    public void testSqrtOperation() {
        assertTrue(OperatorFactory.getOperationInstance("SQRT") instanceof BaseOperator);
    }

    @Test
    public void testDivideOperation() {
        assertTrue(OperatorFactory.getOperationInstance("/") instanceof BaseOperator);
    }

    @Test
    public void testMultiplyOperation() {
        assertTrue(OperatorFactory.getOperationInstance("*") instanceof BaseOperator);
    }

    @Test(expected = CalculatorException.class)
    public void testDefaultOperation() throws CalculatorException {
        OperatorFactory.getOperationInstance("&&").executeCommand(Matchers.anyObject(), Matchers.anyObject(), Matchers.anyInt());
    }
}
