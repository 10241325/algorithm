package com.cy.sort;

import com.cy.util.PrintUtil;
import com.cy.util.RandomArrUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSortWithQueue {
    public static void radixSort(int[] arr) {
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    public static void radixSort(int[] arr, int start, int end, int maxBits) {
        // 创建10个桶 代表0~9
        Queue<Integer>[] buckets = new Queue[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (int times = 1; times <= maxBits; times++) {
            // 入桶
            for (int i = start; i <= end; i++) {
                int digit = getDigit(arr[i], times);
                buckets[digit].offer(arr[i]);
            }
            // 出桶
            int idx = 0;
            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[idx++] = bucket.poll();
                }
            }
        }

    }

    public static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static int getDigit(int num, int bit) {
        while ((--bit) != 0 && num != 0) {
            num /= 10;
        }
        return num % 10;
    }


    public static void main1(String[] args) {
        int[] arr = RandomArrUtil.generateRandomArray(10000000, Integer.MAX_VALUE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = -arr[i];
            }
        }
        int[] arr1 = Arrays.copyOf(arr, arr.length);

//        PrintUtil.toArray(arr);
        long start = System.currentTimeMillis();
        radixSort(arr);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        RadixSort.radixSort(arr1);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
//        for (int i = 1; i < arr.length; i++) {
//            if (arr[i] < arr[i - 1]) {
//                System.out.println("出错了");
//                return;
//            }
//        }
//        PrintUtil.toArray(arr);
    }

}
