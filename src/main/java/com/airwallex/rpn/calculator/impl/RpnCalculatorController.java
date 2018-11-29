package com.airwallex.rpn.calculator.impl;

import com.airwallex.rpn.calculator.factory.OperatorFactory;
import com.airwallex.rpn.calculator.helper.CalculatorHelper;
import com.airwallex.rpn.calculator.opeartions.OperationTracker;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * This class defines run implementation for Calculator
 */
public class RpnCalculatorController implements Calculator {

    private static final String DELIMITER = " ";
    private Deque<BigDecimal> numberStack = new ArrayDeque<>();
    private List<OperationTracker> operationTrackerList = new LinkedList<>();

    public Deque<BigDecimal> getNumberStack() {
        return numberStack;
    }

    @Override
    public void run(String inputString) throws CalculatorException {
        if (null == inputString || inputString.isEmpty())
            throw new CalculatorException("Input can't be blank");
        int count = 0;
        for (String input : CalculatorHelper.parseInput(inputString, DELIMITER)) {
            count++;
            OperatorFactory.getOperationInstance(input.toUpperCase().trim()).executeCommand(numberStack, operationTrackerList, count);
        }
    }

    @Override
    public void display() {
        System.out.print("Stack: ");
        DecimalFormat format = new DecimalFormat("0.##########");
        List<String> displayList = new ArrayList<>();
        numberStack.stream().forEach(e -> displayList.add(format.format(e.doubleValue())));
        Collections.reverse(displayList);
        System.out.println(displayList);
    }


}
