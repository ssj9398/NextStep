package com.example.nextstep;

import org.junit.jupiter.api.DisplayName;
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
        String[] actual = "1,2".split(",");
        System.out.println(Arrays.toString(actual));

        assertThat(actual).contains("1");
        assertThat(actual).contains("2");
    }

    @Test
    void containsExactly(){
        String[] actual = "1".split(",");
        System.out.println(Arrays.toString(actual));

        assertThat(actual).containsExactly("1");
    }

    @Test
    @DisplayName("substring() 메소드를 활용해 ()을 제거하고 1,2을 반환하도록 구현")
    void subString(){
        String actual =  "(1,2)";
        int idx = actual.indexOf(')');
        String substring = actual.substring(1, idx);
        System.out.println("substring = " + substring);

        assertThat(substring).isEqualTo("1,2");
    }

    @Test
    @DisplayName("String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오기")
    void charAt(){
        String actual = "abc";

        assertThat(actual.charAt(0)).isEqualTo('a');
        assertThat(actual.charAt(1)).isEqualTo('b');
        assertThat(actual.charAt(2)).isEqualTo('c');

        assertThatThrownBy(() ->{
            actual.charAt(4);
        }).isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: 4");
    }
}
