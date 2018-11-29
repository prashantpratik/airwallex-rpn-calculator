package com.airwallex.rpn.calculator.factory;

import com.airwallex.rpn.calculator.helper.CalculatorHelper;
import com.airwallex.rpn.calculator.impl.CalculatorException;
import com.airwallex.rpn.calculator.opeartions.BaseOperator;

import java.math.BigDecimal;

/**
 * This is a factory class to get instances of operators
 * Lambda expression are used to implement functional interface
 */
public class OperatorFactory {
    /**
     * This method returns the Operator instance
     * Instances are implemented using Lambda expression
     *
     * @param operation
     * @return
     */
    public static BaseOperator getOperationInstance(String operation) {
        if (CalculatorHelper.isNumeric(operation)) {
            return ((numberStack, operationList, count) -> {
                numberStack.push(new BigDecimal(operation).setScale(15, BigDecimal.ROUND_HALF_UP));
                CalculatorHelper.updateTracker(numberStack, operationList);
            });
        }
        switch (operation) {
            case "SQRT":
                return ((stack, operationList, count) -> {
                    CalculatorHelper.checkStack(stack, count, "SQRT");
                    stack.push(BigDecimal.valueOf(Math.sqrt(stack.pop().doubleValue())));
                    CalculatorHelper.updateTracker(stack, operationList);
                });
            case "UNDO":
                return ((stack, operationList, count) -> {
                    if (operationList.isEmpty())
                        throw new CalculatorException("Can't Undo");
                    stack.clear();
                    operationList.remove(operationList.size() - 1);
                    if (!operationList.isEmpty())
                        operationList.get(operationList.size() - 1).getTracker().stream().forEach(element -> stack.push(element));
                });
            case "CLEAR":
                return ((stack, operationList, count) -> {
                    stack.clear();
                    CalculatorHelper.updateTracker(stack, operationList);
                });
            case "+":
                return ((stack, operationList, count) -> {
                    CalculatorHelper.checkStack(stack, count, "+");
                    stack.push(stack.pop().add(stack.pop()));
                    CalculatorHelper.updateTracker(stack, operationList);
                });
            case "-":
                return ((stack, operationList, count) -> {
                    CalculatorHelper.checkStack(stack, count, "-");
                    BigDecimal first = stack.pop();
                    stack.push(stack.pop().subtract(first));
                    CalculatorHelper.updateTracker(stack, operationList);
                });
            case "*":
                return ((stack, operationList, count) -> {
                    CalculatorHelper.checkStack(stack, count, "*");
                    stack.push(stack.pop().multiply(stack.pop()));
                    CalculatorHelper.updateTracker(stack, operationList);
                });
            case "/":
                return ((stack, operationList, count) -> {
                    CalculatorHelper.checkStack(stack, count, "/");
                    BigDecimal first = stack.pop();
                    if (first.compareTo(BigDecimal.ZERO) == 0)
                        throw new CalculatorException("Divide by Zero Error");
                    stack.push(new BigDecimal(stack.pop().doubleValue() / first.doubleValue()));
                    CalculatorHelper.updateTracker(stack, operationList);
                });
            default:
                return ((stack, operationList, count) -> {
                    throw new CalculatorException("Operator not defined");
                });
        }

    }


}
