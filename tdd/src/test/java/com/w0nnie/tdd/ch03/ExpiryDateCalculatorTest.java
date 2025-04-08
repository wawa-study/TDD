package com.w0nnie.tdd.ch03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ExpiryDateCalculatorTest {

    @DisplayName("만원 납부 -> 한달 뒤 만료일")
    @Test
    void test1() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 4, 1))
                .payAmount(10_000)
                .build(),
                LocalDate.of(2019, 5, 1)
        );
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2025,4,7))
                .payAmount(10_000)
                .build(),
                LocalDate.of(2025,5,7));
    }

    @DisplayName("첫 납부일과 만료일 일자가 다를때 만원 납부")
    @Test
    void test2() {
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build(),
                LocalDate.of(2019, 3, 31)
        );

        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,1,30))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 3, 30)
        );
    }

    @DisplayName("2만원 이상 납부")
    @Test
    void test3(){
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1));

    }

    @DisplayName("첫 납부일과 만료일 일자가 다를 대 2만원 이상 납부")
    @Test
    void test4(){
        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30));

        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2019, 6, 30));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(payData);
        Assertions.assertEquals(expectedExpiryDate, expiryDate);
    }

    @DisplayName("10개월이랑 납부하면 1년 제공")
    @Test
    void test5() {
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2025, 4, 8))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2026, 4, 8));
    }
}
