package com.airwallex.rpn.calculator.helper;

import com.airwallex.rpn.calculator.impl.CalculatorException;
import com.airwallex.rpn.calculator.opeartions.OperationTracker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Helper class to perform various operations
 */
public class CalculatorHelper {

    private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";

    /**
     * This method parses the string input against a delimiter passed
     *
     * @param input
     * @param delimeter
     * @return
     */
    public static List<String> parseInput(String input, String delimeter) {
        StringTokenizer defaultTokenizer = new StringTokenizer(input, delimeter);
        List<String> tokenList = new ArrayList<>();
        while (defaultTokenizer.hasMoreTokens()) {
            tokenList.add(defaultTokenizer.nextToken());
        }
        return tokenList;
    }

    /**
     * This method checks if a String is a numeric value
     *
     * @param inputString
     * @return
     */
    public static boolean isNumeric(String inputString) {
        return inputString.matches(NUMBER_REGEX);
    }

    /**
     * This method updates tracker after each operation
     *
     * @param numberStack
     * @param operationTrackerList
     */
    public static void updateTracker(Deque<BigDecimal> numberStack, List<OperationTracker> operationTrackerList) {
        OperationTracker operationTracker = new OperationTracker();
        Deque<BigDecimal> trackerList = operationTracker.getTracker();
        numberStack.forEach(element -> trackerList.push(element));
        operationTrackerList.add(operationTracker);
    }

    /**
     * This method checks the stack for correct operation
     *
     * @param stack
     * @param count
     * @param operator
     * @throws CalculatorException
     */
    public static void checkStack(Deque<BigDecimal> stack, int count, String operator) throws CalculatorException {
        if (stack.isEmpty())
            throw new CalculatorException("Stack empty, operation not permitted");
        if (stack.size() == 1 && !operator.equalsIgnoreCase("SQRT"))
            throw new CalculatorException("operator " + operator + " (position: " + count + "): insufficient parameters");
    }
}
