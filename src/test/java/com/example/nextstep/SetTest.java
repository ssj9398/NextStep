package com.example.nextstep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp(){
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
    }

    @Test
    @DisplayName("Set의 size() 메소드를 활용해 Set의 크기를 확인")
    void size(){
        assertThat(numbers.size()).isEqualTo(4);
    }

    @DisplayName("Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void contains(int number){
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(2)).isTrue();
        assertThat(numbers.contains(3)).isTrue();
        assertThat(numbers.contains(4)).isTrue();
        assertThat(numbers).contains(number);
    }

    @DisplayName("1, 2, 3 값은 contains 메소드 실행결과 true, 4, 5 값을 넣으면 false 가 반환")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:true", "5:false"}, delimiter = ':')
    void contains123(String input, String expected) {
        boolean actualNum = numbers.contains(Integer.parseInt(input));
        System.out.println(actualNum);
        assertEquals(Boolean.parseBoolean(expected), actualNum);
    }

    @Test
    @DisplayName("사칙연산 구현")
    void arithmeticOperation(){

        //given
        String actual = "2 + 3 * 4 / 2";

        //when
        String[] value = actual.replace(" ","").split("");
        double sum = Double.parseDouble(value[0]);
        for (int i=1; i<value.length; i+=2) {
            sum = calculateOnce(sum, value[i], Double.parseDouble(value[i+1]));
        }

        //then
        assertThat(sum).isEqualTo(10.0);
    }

    private double calculateOnce(double sum, String s, double parseDouble) {
        return switch (s) {
            case "+" -> sum + parseDouble;
            case "-" -> sum - parseDouble;
            case "*" -> sum * parseDouble;
            case "/" -> sum / parseDouble;
            default -> 0.0;
        };

    }
}
