package com.airwallex.rpn.calculator.impl;

/**
 * This interface declares base method required for Calculator
 */
public interface Calculator {
    /**
     * Run method for calculator
     * @param input
     * @throws CalculatorException
     */
    void run(String input) throws CalculatorException;

    /**
     * Method to display content of stack
     */
    void display();
}
