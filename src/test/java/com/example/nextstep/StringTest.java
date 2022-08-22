package com.example.nextstep;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class StringTest {

    @Test
    void replace(){
        String actual = "abc".replace("b","d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void contains(){
        String[] actual2 = "1,2".split(",");
        System.out.println(Arrays.toString(actual2));

        assertThat(actual2).contains("1");
        assertThat(actual2).contains("2");
    }

    @Test
    void containsExactly(){
        String[] actual3 = "1".split(",");
        System.out.println(Arrays.toString(actual3));

        assertThat(actual3).containsExactly("1");
    }
}
