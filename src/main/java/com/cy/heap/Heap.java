package com.cy.heap;

import com.cy.util.SwapUtil;

/**
 * 完全二叉树
 * 数组实现
 * 左子节点  2*i=1
 * 右子节点  2*i+2
 * 父节点  (i-1)/2
 * 子节点满足i>=n/2都是叶子节点 没有子节点 第一个非叶子节点就是n/2-1 无须进行下沉操作
 *
 * @author changyuan
 * @date 2025-03-07 10:56:08
 */
public class Heap {
    /**
     * 大根堆
     *
     * @author changyuan
     * @date 2025-03-07 17:25:48
     */
    public static class BigRootHeap {
        /**
         * 上浮操作
         * 新添加节点curIndex往上看，直到父节点没有比自己大
         *
         * @author changyuan
         * @date 2025-03-07 17:33:27
         */
        public static void swim(int[] arr, int curIndex) {
            int parentIndex;
            // 当cur = 0时 ，(cur-1)/2也等于0，所以当cur=0时就会终止循环
            while (arr[curIndex] > arr[parentIndex = (curIndex - 1) / 2]) {
                SwapUtil.swapOfTmp(arr, curIndex, parentIndex);
                curIndex = parentIndex;
            }
        }

        /**
         * 下沉操作
         * 从curIndex位置往下看，不断地下沉
         * 停：我较大的孩子都不再比我大或者已经没有孩子了
         *
         * @author changyuan
         * @date 2025-03-07 11:14:22
         */
        public static void sink(int[] arr, int curIndex, int heapSize) {
            int left = curIndex * 2 + 1;
            // 完全二叉树，没有左，必然不会有右
            while (left < heapSize) {
                // left+1是右孩子
                // 若有右节点并且右节点比左节点大，返回右节点，否则返回左
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                // 孩子节点中最大值和当前值比较，若孩子节点大于当前节点，则交换，否则停止
                largest = arr[largest] > arr[curIndex] ? largest : curIndex;
                if (largest == curIndex) {
                    break;
                }
                SwapUtil.swapOfTmp(arr, largest, curIndex);
                // 当前节点下沉到largest
                curIndex = largest;
                // 下一个左子节点
                left = curIndex * 2 + 1;
            }
        }
    }

    /**
     * 小根堆
     *
     * @author changyuan
     * @date 2025-03-07 17:25:48
     */
    public static class SmallRootHeap {
        /**
         * 新添加节点curIndex往上看，直到父节点没有比自己小
         *
         * @author changyuan
         * @date 2025-03-07 17:33:27
         */
        public static void swim(int[] arr, int curIndex) {
            int parentIndex;
            // 当cur = 0时 ，(cur-1)/2也等于0，所以当cur=0时就会终止循环
            while (arr[curIndex] < arr[parentIndex = (curIndex - 1) / 2]) {
                SwapUtil.swapOfTmp(arr, curIndex, parentIndex);
                curIndex = parentIndex;
            }
        }

        /**
         * 从curIndex位置往下看，不断地下沉
         * 停：我较小的孩子都不再比我小或者已经没有孩子了
         *
         * @author changyuan
         * @date 2025-03-07 11:14:22
         */
        public static void sink(int[] arr, int curIndex, int heapSize) {
            int left = curIndex * 2 + 1;
            // 完全二叉树，没有左，必然不会有右
            while (left < heapSize) {
                // left+1是右孩子
                // 若有右节点并且右节点比左节点小，返回右节点，否则返回左
                int largest = left + 1 < heapSize && arr[left + 1] < arr[left] ? left + 1 : left;
                // 孩子节点中最小值和当前值比较，若孩子节点小于当前节点，则交换，否则停止
                largest = arr[largest] < arr[curIndex] ? largest : curIndex;
                if (largest == curIndex) {
                    break;
                }
                SwapUtil.swapOfTmp(arr, largest, curIndex);
                // 当前节点下沉到largest
                curIndex = largest;
                // 下一个左子节点
                left = curIndex * 2 + 1;
            }
        }
    }

    public static int a = 0;
    public static Object lock = new Object();
    public static boolean flag = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                if(!flag){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName() + (a++));
                lock.notify();
            }

        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                if(!flag){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName() + (a++));
                lock.notify();
            }

        });
        thread1.start();
        thread2.start();
    }
}
