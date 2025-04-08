package com.cy.sort;

import com.cy.util.RandomArrUtil;

import java.util.Arrays;

/**
 * 乘以2问题
 * 假设 A[ 1…n ] 是一个有 n 个不同数的数组。若 i < j 且 A[ i ] > A [ j ] ，则对偶 (i, j) 称为 A 的一个逆序对。
 * <p>
 * 给出一个确定在 n 个元素的任何排列中逆序对数量的算法，时间复杂度 O(n * lg n)。
 *
 * @author changyuan
 * @date 2025-02-24 14:58:35
 */
public class BiggerThanRightTwice {
    public static int biggerThanRightTwice1(int[] arr) {
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

    public static int biggerThanRightTwice(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        return biggerThanRightTwice(arr, 0, arr.length - 1);
    }

    public static int biggerThanRightTwice(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return biggerThanRightTwice(arr, l, mid)
                + biggerThanRightTwice(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int ans = 0;
        int windowR = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (windowR <= r && arr[i] > arr[windowR] * 2) {
                windowR++;
            }
            ans += windowR - mid - 1;
        }
        int[] helper = new int[r - l + 1];
        int p1 = mid;
        int p2 = r;

        int index = helper.length - 1;
        while (p1 >= l && p2 >= mid + 1) {
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

    public static int biggerThanRightTwice3(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] * 2 < arr[i]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        int testTimes = 10000;
        int maxSize = 10000;
        int maxValue = 10000;
        boolean success = true;
        long times1 = 0;
        long times2 = 0;
        long times3 = 0;
        long begin;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = RandomArrUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            int[] arr3 = Arrays.copyOf(arr1, arr1.length);

            begin = System.currentTimeMillis();
            int ans1 = biggerThanRightTwice(arr1);
            times1 += System.currentTimeMillis() - begin;

            begin = System.currentTimeMillis();
            int ans2 = biggerThanRightTwice1(arr2);
            times2 += System.currentTimeMillis() - begin;

            begin = System.currentTimeMillis();
            int ans3 = biggerThanRightTwice3(arr3);
            times3 += System.currentTimeMillis() - begin;

            if (ans1 != ans2 || ans1 != ans3) {
                success = false;
                break;
            }
        }
        System.out.println("递归耗时：" + times1 + " 非递归耗时：" + times2 + " 暴力耗时：" + times3);
        System.out.println(success ? "Nice!" : "Fucking fucked");
    }
}
