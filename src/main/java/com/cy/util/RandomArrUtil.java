package com.cy.util;

/**
 * @author changyuan
 */
public class RandomArrUtil {
    public static int[] generateRandomSortArray(int maxSize, int maxValue) {

        // 长度 0~maxSize
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        if (arr.length == 0) {
            return arr;
        }
        arr[0] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        if (arr.length == 1) {
            return arr;
        }
        do {
            arr[1] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        } while (arr[1] < arr[0]);
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 2] + arr[i - 1];
        }
        return arr;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // 长度 0~maxSize
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        return arr;
    }

    /**
     * 相邻不相等随机数组
     *
     * @param maxLen   最大长度
     * @param maxValue 最大值
     * @return {@link int[]}
     * @author changyuan
     * @date 2025-02-19 10:34:42
     */
    public static int[] randomNotEqualNearArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }

        return arr;
    }
}
