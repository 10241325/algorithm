package com.cy.util;

import java.util.*;

/**
 * @author changyuan
 */
public class DateUtil {
    /**
     * 判断是否是闰年
     *
     * @param year 待判断年份
     * @return 是否是闰年
     */
    public static boolean isLeapYear(int year) {
        // 能被4整除，不能被100整除，或者能被400整除的年份是闰年
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private static final int[] MONTH_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 获取某年某月的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    public static int getMonthDays(int year, int month) {
        int days = MONTH_DAYS[month];
        if (isLeapYear(year) && month == 2) {
            days++;
        }
        return days;
    }

    public static void main(String[] args) {

        // ["4","13","5","/","+"]
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+","11","/"};
        Arrays.sort(tokens);
        Set<String> symbolTable = new HashSet<>();
        symbolTable.add("*");
        symbolTable.add("/");
        symbolTable.add("+");
        symbolTable.add("-");
        Stack<Integer> numStack = new Stack();

        for (String token : tokens) {
            if (!symbolTable.contains(token)) {
                numStack.push(Integer.valueOf(token));
            } else {
                int ans = 0;
                int a = numStack.pop();
                int b = numStack.pop();
                switch (token) {
                    case "*":
                        ans = (b * a);
                        break;
                    case "/":
                        ans = (b / a);
                        break;
                    case "+":
                        ans = (b + a);
                        break;
                    case "-":
                        ans = (b - a);
                        break;
                    default:
                }
                numStack.push(ans);
            }
        }
        System.out.println(numStack.pop());
    }
}
