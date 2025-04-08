package com.w0nnie.tdd.ch03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addMonths = payData.getPayAmount() == 100_000 ? payData.getPayAmount() /  10_000 + 2 : payData.getPayAmount() / 10_000 ;
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addMonths);
        } else{
            return payData.getBillingDate().plusMonths(addMonths);
        }
    }

    private static LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addMonths);
        int firstBillingDay = payData.getFirstBillingDate().getDayOfMonth();

        if (isSameDay(candidateExp, firstBillingDay)) {
            return candidateExp;
        }

        return getAdjustedExpiryDate(candidateExp, firstBillingDay);
    }

    private static boolean isSameDay(LocalDate date, int dayOfMonth) {
        return date.getDayOfMonth() == dayOfMonth;
    }

    private static LocalDate getAdjustedExpiryDate(LocalDate candidateExp, int firstBillingDay) {
        int lastDayOfMonth = YearMonth.from(candidateExp).lengthOfMonth();
        int adjustedDay = Math.min(firstBillingDay, lastDayOfMonth);
        return candidateExp.withDayOfMonth(adjustedDay);
    }
}
