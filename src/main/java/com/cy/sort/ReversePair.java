package com.cy.sort;

import com.cy.util.RandomArrUtil;

import java.util.Arrays;

/**
 * 逆序对问题
 * 假设 A[ 1…n ] 是一个有 n 个不同数的数组。若 i < j 且 A[ i ] > A [ j ] ，则对偶 (i, j) 称为 A 的一个逆序对。
 * <p>
 * 给出一个确定在 n 个元素的任何排列中逆序对数量的算法，时间复杂度 O(n * lg n)。
 *
 * @author changyuan
 * @date 2025-02-24 10:42:42
 */
public class ReversePair {
    public static int reversePair1(int[] arr) {
        int ans = 0;
        int L = 0;
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                ans += merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > (N >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }
        return ans;
    }

    public static int reversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return reversePair(arr, 0, arr.length - 1);
    }

    public static int reversePair(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return reversePair(arr, l, mid) + reversePair(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int[] helper = new int[r - l + 1];
        int p1 = mid;
        int p2 = r;
        int index = helper.length - 1;
        int ans = 0;
        while (p1 >= l && p2 >= mid + 1) {
            ans += arr[p1] > arr[p2] ? p2 - mid : 0;
            helper[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            helper[index--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            helper[index--] = arr[p2--];
        }
        for (index = 0; index < helper.length; index++) {
            arr[l + index] = helper[index];
        }
        return ans;
    }

    public static int reversePairForSolution(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 50;
        int maxValue = 10000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = RandomArrUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            int[] arr3 = Arrays.copyOf(arr1, arr1.length);
            int ans1 = reversePair(arr1);
            int ans2 = reversePairForSolution(arr2);
            int ans3 = reversePair1(arr3);

            if (ans1 != ans2
                    || ans2 != ans3) {
                System.out.println(ans1 + " " + ans2 + " " + ans3);
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
//
//        int[] arr2 = Arrays.copyOf(arr4, arr4.length);
//        int[] arr3 = Arrays.copyOf(arr4, arr4.length);
//        System.out.println(Arrays.toString(arr4));
//        System.out.println(Arrays.toString(arr2));
//        System.out.println(Arrays.toString(arr3));
//        int[] arr1 = new int[]{2, 4, 4, 2};
//        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
//        int[] arr3 = Arrays.copyOf(arr1, arr1.length);
////        int ans1 = reversePair(arr1);
////        int ans2 = reversePairForSolution(arr2);
//        int ans3 = reversePair1(arr3);
//        System.out.println( ans3);

    }
}
