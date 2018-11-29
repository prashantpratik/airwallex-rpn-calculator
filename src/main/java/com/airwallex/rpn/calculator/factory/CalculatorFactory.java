package com.airwallex.rpn.calculator.factory;

import com.airwallex.rpn.calculator.impl.Calculator;
import com.airwallex.rpn.calculator.impl.RpnCalculatorController;

/**
 * This is a factory class to get RpnCalculatorController
 */
public class CalculatorFactory {
    private static RpnCalculatorController rpnCalculatorController = null;

    /**
     * This method returns new instance of RpnCalculatorController
     * @return
     */
    public static Calculator getCalculator() {
        if (rpnCalculatorController == null)
            rpnCalculatorController = new RpnCalculatorController();
        return rpnCalculatorController;
    }
}
