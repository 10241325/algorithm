package com.cy.sort;

import com.cy.util.SwapUtil;
import com.cy.validator.ArrayValidator;

import java.util.function.Consumer;

/**
 * @author changyuan
 */
public class SortUtil {
    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     *
     * @param arr 待排序数组
     * @author changyuan
     * @since 2025-01-17 21:13:07
     */
    public static void selectionSort(int[] arr) {
        execute(arr, (int[] array) -> {
            // 从0到n-1上选择一个最小值，放在0位置
            // 从1到n-1上选择一个最小值，放在1位置
            // 从2到n-1上选择一个最小值，放在2位置
            for (int i = 0; i < arr.length - 1; i++) {
                // 默认i位置最小
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    // 从i+1往后找到最小值
                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }
                // 如果i位置不是最小值，交换
                if (minIndex != i) {
                    SwapUtil.swapOfXor(arr, i, minIndex);
                }
            }
            // i位置就是最小值，i+1位置就是次小值，以此类推
        });
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     *
     * @param arr 待排序数组
     * @author changyuan
     * @since 2025-01-17 21:13:07
     */
    public static void bubbleSort(int[] arr) {
        execute(arr, (int[] array) -> {
            // 0~n-1
            // 0~n-2
            // 0~n-3
            // 0~end
            for (int end = arr.length - 1; end > 0; end--) {
                // 0到end上比较
                for (int i = 0; i < end; i++) {
                    if (arr[i] > arr[i + 1]) {
                        SwapUtil.swapOfXor(arr, i, i + 1);
                    }
                }
            }
        });
    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     *
     * @param arr 待排序数组
     * @author changyuan
     * @since 2025-01-17 21:13:07
     */
    public static void insertionSort(int[] arr) {
        execute(arr, (int[] array) -> {
            // 0~0有序
            // 0~1有序
            // 0~2有序
            // 0~n-1有序
            for (int curr = 1; curr < arr.length; curr++) {
                // 0~curr-1有序
                // 0~curr有序
                for (int pre = curr - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                    SwapUtil.swapOfTmp(arr, pre, pre + 1);
                }
            }
        });
    }

    public static void execute(int[] arr, Consumer<int[]> consumer) {
        if (!ArrayValidator.validateSortArray(arr)) {
            return;
        }
        consumer.accept(arr);
    }
}
