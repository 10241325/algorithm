package com.cy.sort;

import com.cy.util.PrintUtil;

/**
 * 基数排序
 * 只能适用于非负数
 * 如果需要负数 先把最小的负数找到 然后所有的数减去这个负数  最终结果加上这个负数即可 也可能有溢出的可能
 *
 * @author changyuan
 * @date 2025-03-10 11:23:13
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }


    /**
     * @param arr   数组
     * @param start 开始位置
     * @param end   结束位置
     * @param digit 开始位置到结束位置最大值的位数 比如9999 则位数为4
     * @author changyuan
     * @date 2025-03-10 11:27:37
     */
    private static void radixSort(int[] arr, int start, int end, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        int[] helper = new int[end - start + 1];
        // 有多少位就进出几次
        // 第一次进桶个位
        // 第二次进桶十位
        // 第三次进桶百位
        // ......
        for (int d = 1; d <= digit; d++) {
            // 10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个

            // count[0,...,9]
            int[] count = new int[radix];
            for (i = start; i <= end; i++) {
                // 103 1 3
                // 209 1 9
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 前缀和
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 出桶
            for (i = end; i >= start; i--) {
                j = getDigit(arr[i], d);
                helper[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = start, j = 0; i <= end; i++, j++) {
                arr[i] = helper[j];
            }
        }
    }

    private static int getDigit(int num, int digit) {
        while ((--digit) != 0) {
            num /= 10;
        }
        return num % 10;
    }

    /**
     * 计算最大的那个数的位数
     *
     * @param arr 数组
     * @return {@link int}
     * @author changyuan
     * @date 2025-03-10 11:30:20
     */
    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int k = 0; k < arr.length; k++) {
            max = Math.max(max, arr[k]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        radixSort(arr);
        PrintUtil.toArray(arr);
    }
}
