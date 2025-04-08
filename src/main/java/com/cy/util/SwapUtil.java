package com.cy.util;

/**
 * @author changyuan
 */
public class SwapUtil {
    /**
     * 交换数组中两个元素
     *
     * @param arr 待交换数组
     * @param i   待交换元素索引
     * @param j   待交换元素索引
     */
    public static void swapOfTmp(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 交换数组中两个元素
     *
     * @param arr 待交换数组
     * @param i   待交换元素索引
     * @param j   待交换元素索引
     */
    public static void swapOfXor(int[] arr, int i, int j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

}
