package com.example.nextstep;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(3,4));
        System.out.println(calculator.subtract(3,4));
        System.out.println(calculator.multiply(3,4));
        System.out.println(calculator.divide(8,4));
    }
}
