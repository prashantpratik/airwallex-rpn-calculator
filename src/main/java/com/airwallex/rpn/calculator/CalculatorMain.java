package com.airwallex.rpn.calculator;

import com.airwallex.rpn.calculator.factory.CalculatorFactory;
import com.airwallex.rpn.calculator.impl.CalculatorException;

import java.util.Scanner;

public class CalculatorMain {
    /**
     * Main method which accepts input from command line
     * Commands are passed to RpnCalculatorController for processing
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine;
        System.out.println("Please input data:");
        while (scanner.hasNextLine()) {
            inputLine = scanner.nextLine();
            try {
                CalculatorFactory.getCalculator().run(inputLine);
            } catch (CalculatorException ex) {
                System.out.println(ex.getMessage());
            }
            CalculatorFactory.getCalculator().display();
        }
    }

}
