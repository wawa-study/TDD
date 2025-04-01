package com.w0nnie.tdd.ch02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {

    @Test
    void meetsAllCriteria_then_strong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);
        PasswordStrength result2 = meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    void meetsOtherCriteria_except_for_length_then_normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("abc!A");
        assertEquals(PasswordStrength.NORMAL, result);
        PasswordStrength result2 = meter.meter("aAb12!c");
        assertEquals(PasswordStrength.NORMAL, result2);
    }
    
    @DisplayName("숫자 미포함/나머지 통과")
    @Test
    void meetsOtherCriteria_except_for_number_then_normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("Abc!ABCD");
        assertEquals(PasswordStrength.NORMAL, result);
    }
}
