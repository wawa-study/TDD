package com.w0nnie.tdd.ch02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean containsNum = meetsContainingNumberCriteria(s);
        if(!containsNum) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        for (char a : s.toCharArray()) {
            if (a >= '0' && a <= '9') {
                return true;
            }
        }
        return false;
    }
}
