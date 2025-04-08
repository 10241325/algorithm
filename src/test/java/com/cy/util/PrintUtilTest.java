package com.cy.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PrintUtilTest {
    @Test
    public void testToBinary() {
        PrintUtil.toBinary('a');
        PrintUtil.toBinary('a');
        PrintUtil.toBinary('a' & 0b01000001);
        PrintUtil.toBinary('A');
        PrintUtil.toBinary('A' & 0b01000001);
    }

    @Test
    public void testSumOfOne() {
        PrintUtil.sumOfOne(Integer.MAX_VALUE);
        PrintUtil.sumOfOne(-1);
    }
}
