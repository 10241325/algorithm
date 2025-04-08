package com.cy.recursion;

import java.util.HashSet;

/**
 * 递归 所有的递归都可以改成非递归
 *
 * @author changyuan
 * @date 2025-02-23 10:54:23
 */
public class GetMax {
    public static int getMax(int[] arr) {
        return getMax(arr, 0, arr.length - 1);
    }

    /**
     * arr[start,end]范围上求最大值
     *
     * @param arr
     * @param start
     * @param end
     * @return {@link int}
     * @author changyuan
     * @date 2025-02-23 10:58:14
     */
    public static int getMax(int[] arr, int start, int end) {
        // arr[start,end] 范围上只有一个数，直接返回 base case
        if (start == end) {
            return arr[start];
        }
        // arr[start,end] 不止一个数 求左右最值 和 右边最值 返回最大值
        int mid = start + ((end - start) >> 1);

        int left = getMax(arr, start, mid);
        int right = getMax(arr, mid + 1, end);
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(getMax(arr));
    }
}
