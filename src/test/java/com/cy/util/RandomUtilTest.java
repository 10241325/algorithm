package com.cy.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RandomUtilTest {
    @Test
    public void testRandom() {
        // 测试随机数的概率
        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (RandomUtil.random() < 0.35) {
                count++;
            }
        }
        System.out.println(count / (double) testTimes);
        System.out.println("=================");
        // [0,1)->[0,8)
        double ans = RandomUtil.random() * 8;
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (RandomUtil.random() * 8 < 5) {
                count++;
            }
        }
        count = 0;
        double x = 0.7;
        for (int i = 0; i < testTimes; i++) {
            if (RandomUtil.xToPower2() < x) {
                count++;
            }
        }
        System.out.println(Math.pow(x, 2) + " | " + (count / (double) testTimes));
        System.out.println("=================");
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (RandomUtil.f3() == 6) {
                count++;
            }
        }
        System.out.println((1 / (double) 7) + "|" + (count / (double) testTimes));

    }
}
