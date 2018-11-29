package com.airwallex.rpn.calculator.opeartions;


import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * POJO class to track state of operation
 * This class will store all the states of operands considering default PUSH operation
 */
public class OperationTracker {
    Deque<BigDecimal> tracker = new ArrayDeque<>();

    public void addToTracker(BigDecimal number) {
        tracker.add(number);
    }

    public Deque<BigDecimal> getTracker() {
        return tracker;
    }
}
