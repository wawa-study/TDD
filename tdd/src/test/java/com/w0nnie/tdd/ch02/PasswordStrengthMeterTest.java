package com.w0nnie.tdd.ch02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(PasswordStrength passwordStrength, String expectString) {
        PasswordStrength result = meter.meter(expectString);
        assertEquals(passwordStrength, result);
    }
    
    @DisplayName("모든 경우 충족")
    @Test
    void meetsAllCriteria_then_strong() {
        assertStrength(PasswordStrength.STRONG, "ab12!@AB");
        assertStrength(PasswordStrength.STRONG, "abc1!Add");
    }
    
    @DisplayName("8글자 이하/나머지 충족")
    @Test
    void meetsOtherCriteria_except_for_length_then_normal() {
        assertStrength(PasswordStrength.NORMAL, "abc!A1");
        assertStrength(PasswordStrength.NORMAL, "aAb12!c");
    }
    
    @DisplayName("숫자 미포함/나머지 충족")
    @Test
    void meetsOtherCriteria_except_for_number_then_normal() {
        assertStrength(PasswordStrength.NORMAL, "Abc!ABCD");
        assertStrength(PasswordStrength.NORMAL, "Abc!ABCp@");
    }
    @DisplayName("대문자 미포함/나머지 충족")
    @Test
    void test5() {
        assertStrength(PasswordStrength.NORMAL, "abc!234!@");
        assertStrength(PasswordStrength.NORMAL, "abc!234!@");
    }

    @DisplayName("값이 없는 경우")
    @Test
    void empty() {
        assertStrength(PasswordStrength.INVALID, "");
        assertStrength(PasswordStrength.INVALID, null);
    }


    @DisplayName("8글자이상/나머지 미충족")
    @Test
    void test6() {
        assertStrength(PasswordStrength.WEAK, "adadadad");
    }

    @DisplayName("숫자포함/나머지 미충족")
    @Test
    void test7() {
        assertStrength(PasswordStrength.WEAK, "1234");
    }

    @DisplayName("대문자포함/나머지 미충족")
    @Test
    void test8() {
        assertStrength(PasswordStrength.WEAK, "AA");
    }

    @DisplayName("조건 미충족")
    @Test
    void test9() {
        assertStrength(PasswordStrength.WEAK, "abc");
    }
}
