package com.cy.search;

import com.cy.util.PrintUtil;
import com.cy.util.RandomArrUtil;

/**
 * 二分
 *
 * @author changyuan
 */
public class BinarySearch {
    /**
     * 在一个有序数组中找到num
     *
     * @param arr 有序数组
     * @param num 目标值
     * @return 目标值下标
     * @author changyuan
     * @date 2025-02-22 14:14:08
     */
    public static int binarySearch(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == num) {
                return mid;
            }
            if (arr[mid] < num) {
                L = mid + 1;
                continue;
            }
            R = mid - 1;
        }
        return -1;
    }

    /**
     * 在一个有序数组中，找>=某个数最左侧位置
     *
     * @param arr 有序数组
     * @param num 目标值
     * @return >=目标值最左侧位置
     * @author changyuan
     * @date 2025-02-22 14:33:34
     */
    public static int binarySearchLastLeft(int[] arr, int num) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= num) {
                ans = mid;
                R = mid - 1;
                continue;
            }
            L = mid + 1;
        }
        return ans;

    }

    /**
     * 在一个有序数组中，找<=某个数最右侧位置
     *
     * @param arr 有序数组
     * @param num 目标值
     * @return <=目标值最右侧位置
     * @author changyuan
     * @date 2025-02-22 14:33:34
     */
    public static int binarySearchLastRight(int[] arr, int num) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;

        while (L <= R) {
            int mid = L + ((R - L) >> 2);
            if (arr[mid] <= num) {
                ans = mid;
                L = mid + 1;
                continue;
            }
            R = mid - 1;
        }
        return ans;

    }

    public static int localMin(int[] arr) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return -1;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);

            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
                continue;
            }
            if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
                continue;
            }
            return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxLen = 10;
        int maxValue = 10000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = RandomArrUtil.randomNotEqualNearArr(maxLen, maxValue);
            if (0 == arr.length) {
                continue;
            }
            int index = localMin(arr);
            if (-1 == index) {
                continue;
            }
            if (0 == index) {
                if (arr[1] < arr[0]) {
                    PrintUtil.toArray(arr);
                    System.out.println(index);
                    success = false;
                    break;
                }
            } else if (index == arr.length - 1) {
                if (arr[arr.length - 2] < arr[arr.length - 1]) {
                    PrintUtil.toArray(arr);
                    System.out.println(index);
                    success = false;
                    break;
                }
            } else if (arr[index] > arr[index + 1] || arr[index] > arr[index - 1]) {
                PrintUtil.toArray(arr);
                System.out.println(index);
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice !" : "Fucking fucked !");

    }
}
