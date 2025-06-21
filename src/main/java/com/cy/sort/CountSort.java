package com.cy.sort;

import com.cy.util.PrintUtil;

/**
 * 计数排序
 * 给定一个数组arr  里面存放的是员工的年龄
 * 因为是年龄 所以我们可以估计出年龄不会超过0~200
 * 准备一个长度为201的数组help
 * 遍历arr从help开始计数
 * 然后遍历arr讲计数的年龄放到arr中即可
 *
 * @author changyuan
 * @date 2025-03-10 11:16:39
 */
public class CountSort {
    public static void countSort(int[] arr, int max) {
        int[] helper = new int[max];
        for (int value : arr) {
            helper[value]++;
        }
        int j = 0;
        for (int i = 0; i < max; i++) {
            // 当前下标的个数未出现过 直接跳过
            if (helper[i] == 0) {
                continue;
            }
            // helper[i] 是当前数字出现的个数
            for (int k = 0; k < helper[i]; k++) {
                arr[j++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 4, 2, 3, 2, 1};
        countSort(arr, 7);
        PrintUtil.toArray(arr);
    }
}
