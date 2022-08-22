package com.example.nextstep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    void 덧셈() {
        Assertions.assertEquals(7,calculator.add(3,4));
    }

    @Test
    void 뺄셈() {
        Assertions.assertEquals(1,calculator.subtract(4,3));
    }

    @Test
    void 곱셈() {
        Assertions.assertEquals(12,calculator.multiply(3,4));
    }

    @Test
    void 나눗셈() {
        Assertions.assertEquals(2,calculator.divide(8,4));
    }

    @AfterEach
    public void tearDwon(){
        calculator = null;
    }
}