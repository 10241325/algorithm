package com.cy.sort;

import com.cy.linked.queue.Stack;
import com.cy.util.PrintUtil;
import com.cy.util.SwapUtil;

public class QuickSort {
    public static void quickSort1(int[] nums) {
        process1(nums, 0, nums.length - 1);
    }

    public static void process1(int[] nums, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = NetherlandsFlag.netherlandsFlag(nums, L, R);
        process1(nums, L, equalArea[0] - 1);
        process1(nums, equalArea[1] + 1, R);
    }

    public static void quickSort2(int[] nums) {
        process2(nums, 0, nums.length - 1);
    }

    public static void process2(int[] nums, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机选一个划分值
        SwapUtil.swapOfTmp(nums, L + (int) (Math.random() * (R - L + 1)), R);

        int[] equalArea = NetherlandsFlag.netherlandsFlag(nums, L, R);
        process2(nums, L, equalArea[0] - 1);
        process2(nums, equalArea[1] + 1, R);
    }

    static class Op {
        public int l;
        public int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void quickSort3(int[] nums) {
        if (null == nums || nums.length < 2) {
            return;
        }
        int N = nums.length;
        SwapUtil.swapOfTmp(nums, (int) (Math.random() * N), N - 1);
        int[] equalArea = NetherlandsFlag.netherlandsFlag(nums, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, N - 1));

        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                SwapUtil.swapOfTmp(nums, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = NetherlandsFlag.netherlandsFlag(nums, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 3, 8, 3, 1, 9, 0, 3};
        quickSort1(nums);
        PrintUtil.toArray(nums);
        nums = new int[]{2, 4, 3, 8, 3, 1, 9, 0, 3};
        quickSort2(nums);
        PrintUtil.toArray(nums);
        nums = new int[]{2, 4, 3, 8, 3, 1, 9, 0, 3};
        quickSort3(nums);
        PrintUtil.toArray(nums);
    }
}
