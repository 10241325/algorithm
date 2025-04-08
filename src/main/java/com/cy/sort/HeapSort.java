package com.cy.sort;

import com.cy.heap.Heap;
import com.cy.util.PrintUtil;
import com.cy.util.RandomArrUtil;
import com.cy.util.SwapUtil;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author changyuan
 * @date 2025-03-07 17:36:26
 */
public class HeapSort {
    /**
     * 大根堆排序
     *
     * @author changyuan
     * @date 2025-03-07 17:37:07
     */
    public static void bigRootHeadSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        // 建立大根堆
        // 1、第一种从上往下建立 O(N*logN)
//        for (int i = 0; i < arr.length; i++) {
//            Heap.BigRootHeap.heapInsert(arr, i);
//        }
        // 2、第二种从下往上建立O(N)
        // 第一个非叶子节点为n/2-1 叶子节点下沉没有意义 只有他本身自己一个节点
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            Heap.BigRootHeap.sink(arr, i, arr.length);
        }
        // 第一个和最后一个交换，第一个必然是最大值
        int heapSize = arr.length;
        SwapUtil.swapOfTmp(arr, 0, --heapSize);

        while (heapSize > 0) {
            // 从第一个开始下沉 变成大根堆
            Heap.BigRootHeap.sink(arr, 0, heapSize);
            // 第一个必然是最大值，第一个和最后一个交换，循环知道堆深度等于0
            SwapUtil.swapOfTmp(arr, 0, --heapSize);
        }
    }

    /**
     * 小根堆排序
     *
     * @author changyuan
     * @date 2025-03-07 17:37:07
     */
    public static void smallRootHeadSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            Heap.SmallRootHeap.sink(arr, i, arr.length);
        }
        // 第一个和最后一个交换，第一个必然是最小值
        int heapSize = arr.length;
        SwapUtil.swapOfTmp(arr, 0, --heapSize);

        while (heapSize > 0) {
            // 从第一个开始下沉 变成小根堆
            Heap.SmallRootHeap.sink(arr, 0, heapSize);
            // 第一个必然是最小值，第一个和最后一个交换，循环知道堆深度等于0
            SwapUtil.swapOfTmp(arr, 0, --heapSize);
        }
    }

    public static boolean check(int[] arr1, int[] arr2) {
        if (null == arr1) {
            return arr2 == null;
        }
        if (null == arr2) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        return MergeSort.checkResult(arr1, arr2);
    }

    public static void main(String[] args) {
        int maxValue = 100;
        int maxSize = 5;
        int times = 100000;
        boolean success = true;
        for (int i = 0; i < times; i++) {
            int[] arr = RandomArrUtil.generateRandomArray(maxSize, maxValue);
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            SortUtil.insertionSort(arr1);
            bigRootHeadSort(arr2);
            if (!check(arr1, arr2)) {
                PrintUtil.toArray(arr);
                PrintUtil.toArray(arr1);
                PrintUtil.toArray(arr2);
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");

    }

}
