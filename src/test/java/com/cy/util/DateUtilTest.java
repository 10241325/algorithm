package com.cy.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DateUtilTest {
    @Test
    public void isLeapYearTest() {
        System.out.println(DateUtil.isLeapYear(2020));
        byte a;
        for (a = 0; true; a++) {
            System.out.println(a);
        }
    }

    @Test
    public void getMonthDaysTest() {
//        System.out.println(DateUtil.getMonthDays(2020, 2));
        int i = 1234567890;
        int n = 1;
        while ((i = i / 10) > 0) {
            n++;
        }
        System.out.println(n);
    }
}
