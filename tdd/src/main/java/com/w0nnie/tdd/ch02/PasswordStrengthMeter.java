package com.w0nnie.tdd.ch02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int metCount = getMetCriteriaCounts(s);
        if(metCount <= 1) return PasswordStrength.WEAK;
        if(metCount == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private static int getMetCriteriaCounts(String s) {
        int metCount = 0;
        if(s.length() >= 8) metCount++;
        if(meetsContainingNumberCriteria(s)) metCount++;
        if(meetsContainsUpperCaseCriteria(s)) metCount++;
        return metCount;
    }

    private static boolean meetsContainsUpperCaseCriteria(String s) {
        for (char a : s.toCharArray()) {
            if (Character.isUpperCase(a)) {
                return true;
            }
        }
        return false;
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
