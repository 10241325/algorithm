package com.cy.sort;


import com.cy.util.PrintUtil;
import com.cy.util.RandomArrUtil;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author changyuan
 */
public class MergeSort {
    /**
     * 非递归
     *
     * @param arr
     * @return
     * @author changyuan
     * @date 2025-02-21 14:24:01
     */
    public static void mergeSort2(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        // 步长 从1开始 为2的幂次方 1 2 4 8 16
        // 例子 5 4 3 2 1
        // 步长 为 1：  5|4 , 3|2 , 1  -> 4 5 2 3 1
        // 步长 为 2：  45|23, 1       -> 2 3 4 5 1
        // 步长 为 4：  2345 | 1       -> 1 2 3 4 5
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    // 表示只有左边 没有右边 不用merge
                    break;
                }
                // 右边不够mergeSize个就是N-1
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                // 下一组从R+1开始
                L = R + 1;
            }
            // 防止步长溢出 假如int最大值为10 现在有9个数 当步长为8的时候 合并前8个和后一个
            // 这时候mergeSize<<=1就变成16了 就会越界 导致最开始的while停不下来
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 递归
     *
     * @param arr
     * @return
     * @author changyuan
     * @date 2025-02-21 14:23:37
     */
    public static void mergeSort1(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] helper = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int index = 0;
        while (p1 <= M && p2 <= R) {
            helper[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            helper[index++] = arr[p1++];
        }
        while (p2 <= R) {
            helper[index++] = arr[p2++];
        }
        for (index = 0; index < helper.length; index++) {
            arr[L + index] = helper[index];
        }
    }

    public static void main(String[] args) {

        int testTimes = 10000000;
        int maxValue = 10000;
        int maxSize = 10;

        boolean seccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = RandomArrUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!checkResult(arr1, arr2)) {
                seccess = false;
                break;
            }
        }
        System.out.println(seccess ? "Nice!" : "Fucking fucked!");
    }

    static boolean checkResult(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        Arrays.sort(arr1);
        return true;
    }
}
