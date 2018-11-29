package com.airwallex.rpn.calculator.opeartions;

import com.airwallex.rpn.calculator.impl.CalculatorException;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.List;

/**
 * Base command interface
 */
@FunctionalInterface
public interface BaseOperator {
    /**
     * @param numberStack
     * @param operationTrackerList
     * @param count
     * @throws CalculatorException
     */
    void executeCommand(Deque<BigDecimal> numberStack, List<OperationTracker> operationTrackerList, int count) throws CalculatorException;
}
