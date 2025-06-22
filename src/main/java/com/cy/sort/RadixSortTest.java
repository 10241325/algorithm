package com.cy.sort;

import com.cy.util.PrintUtil;

public class RadixSortTest {
    public static void radixSort(int[] arr) {
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    public static void radixSort(int[] arr, int start, int end, int maxBits) {
        // 辅助数组
        int[] helper = new int[end - start + 1];

        for (int times = 1; times <= maxBits; times++) {
            // 创建10个桶
            int[] buckets = new int[10];
            // 入桶
            for (int i = start; i <= end; i++) {
                buckets[getDigit(arr[i], times)]++;
            }
            // 前缀和
            for (int i = 1; i < buckets.length; i++) {
                buckets[i] += buckets[i - 1];
            }
            // 出桶
            for (int i = end; i >= start; i--) {
                int digit = getDigit(arr[i], times);
                helper[buckets[digit] - 1] = arr[i];
                buckets[digit]--;
            }
            // 辅助数组拷贝到原数组
            for (int i = start, j = 0; i <= end; i++, j++) {
                arr[i] = helper[j];
            }
        }
    }

    public static int getDigit(int num, int digit) {
        while ((--digit) != 0) {
            num /= 10;
        }
        return num % 10;
    }

    public static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(max, j);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9999, 88888, 777, 66666, 555, 4444, 3333, 2222222, 1};
        radixSort(arr);
        PrintUtil.toArray(arr);
    }
}
