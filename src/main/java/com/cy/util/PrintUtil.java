package com.cy.util;

import java.util.Arrays;

/**
 * @author changyuan
 */
public class PrintUtil {
    /**
     * 打印整数二进制序列
     *
     * @param num 待打印整数
     * @author changyuan
     * @date 2025-01-17 22:29:13
     */
    public static void toBinary(long num) {
        int i = 64;
        while (i-- > 0) {
            System.out.print((1L << i & num) != 0 ? '1' : '0');
            if ((i) % 8 == 0 && i != 0) {
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    public static void sumOfOne(int num) {
        int sum = 0;
        while (num != 0) {
            sum++;
            num = num & (num - 1);
        }
        System.out.println(sum);
    }

    /**
     * 打印数组
     *
     * @param arr 待打印数组
     * @author changyuan
     * @date 2025-01-17 22:29:13
     */
    public static void toArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
