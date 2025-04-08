package com.cy.sort;

import com.cy.util.RandomArrUtil;

import java.util.Arrays;

/**
 * 小和问题
 * 每一个数左边比他小的所有数都累加起来
 *
 * @author changyuan
 * @date 2025-02-23 16:28:02
 */
public class SmallSum {

    public static int smallSum(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        return smallSum(arr, 0, arr.length - 1);
    }

    public static int smallSum(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        int mid = start + ((end - start) >> 1);
        return smallSum(arr, start, mid)
                + smallSum(arr, mid + 1, end)
                + merge(arr, start, mid, end);
    }

    /**
     * 整体思路就是  合并的时候 右边和左边肯定是有序的 根据右边的当前数 算出来左边有几个数大于当前数
     * 则小和的结果中该数就被加个几次
     * 左边123  右边4567
     * 假如p1 = 1 p2 = 4
     * 则左边有个数大于1 则可以理解成1这个数 在4567中都被加了一次 则结果肯定是有4*1
     * 4 = end - p2 + 1
     * 当arr[p1] = arr[p2] 时 要先拷贝右侧 否则无法知道右侧有几个数大于arr[p1]
     *
     * @param arr
     * @param start
     * @param mid
     * @param end
     * @return {@link int}
     * @author changyuan
     * @date 2025-02-24 10:36:43
     */
    public static int merge(int[] arr, int start, int mid, int end) {
        int[] helper = new int[end - start + 1];
        int p1 = start;
        int p2 = mid + 1;
        int ans = 0;
        int index = 0;
        while (p1 <= mid && p2 <= end) {
            ans += arr[p1] < arr[p2] ? arr[p1] * (end - p2 + 1) : 0;
            // 相等要先拷贝右侧 这样才能根据end - p2 + 1算出来右侧有几个数大于p1
            helper[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            helper[index++] = arr[p1++];
        }
        while (p2 <= end) {
            helper[index++] = arr[p2++];
        }
        for (index = 0; index < helper.length; index++) {
            arr[start + index] = helper[index];
        }
        return ans;
    }

    public static int smallSumForSolution(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    ans += arr[j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxSize = 100;
        int maxValue = 10000;
        boolean success = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = RandomArrUtil.generateRandomArray(maxSize, maxValue);
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            if (smallSumForSolution(arr1) != smallSum(arr)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice !" : "Fucking fucked !");
    }
}
