package com.cy.sort;

import com.cy.util.PrintUtil;
import com.cy.util.SwapUtil;

/**
 * 荷兰国旗
 * 给定一个数组 和一个数x
 * 1、要求<=x放左边 >x放右边
 * 2、要求<x放左边 =放右边 >x放右边
 *
 * @author changyuan
 * @date 2025-02-25 08:53:53
 */
public class NetherlandsFlag {
    /**
     * 要求<=x放左边 >x放右边
     *
     * @author changyuan
     * @date 2025-02-25 09:09:28
     */
    public static void netherlandsFlag1(int[] nums, int x) {
        if (null == nums || nums.length < 2) {
            return;
        }
        int lessThanEquals = -1;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] <= x) {
                // 若当前数小于等x
                // 则当前数和小于等于区域下一个数交换
                // 若当前数及时小于等于区域的下一个数 则自己和自己交换
                // 目的就是遍历nums发现一个符合的数就放到区域中
                SwapUtil.swapOfTmp(nums, cur, lessThanEquals + 1);
                // 小于等于区域向前扩
                lessThanEquals++;
            }
        }
    }

    /**
     * 要求<x放左边 =放右边 >x放右边
     *
     * @author changyuan
     * @date 2025-02-25 09:09:28
     */
    public static void netherlandsFlag2(int[] nums, int x) {
        if (null == nums || nums.length < 2) {
            return;
        }
        int lessThan = -1;
        int moreThan = nums.length;
        for (int cur = 0; cur < moreThan; cur++) {
            if (nums[cur] < x) {
                SwapUtil.swapOfTmp(nums, cur, lessThan + 1);
                lessThan++;
            } else if (nums[cur] > x) {
                SwapUtil.swapOfTmp(nums, cur, moreThan - 1);
                moreThan--;
            }
        }
    }


    /**
     * arr[L,R] 玩荷兰国旗问题的划分，以arr[R]做划分值
     * <arr[R] ==arr[R] >arr[R]<
     *
     * @return 相等区间的索引
     * @author changyuan
     * @date 2025-02-25 09:40:02
     */
    public static int[] netherlandsFlag(int[] nums, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            // 相等区间就是L,R nums[L] = nums[R]
            return new int[]{L, R};
        }
        int less = L - 1;
        // 先把R扩起来 最后把R和more交换
        int more = R;

        int cur = L;
        while (cur < more) {
            if (nums[cur] < nums[R]) {
                SwapUtil.swapOfTmp(nums, cur++, ++less);
            } else if (nums[cur] > nums[R]) {
                // cur和大于区域交换 cur不能加 还需要看一遍
                SwapUtil.swapOfTmp(nums, cur, --more);
            } else {
                cur++;
            }
        }
        SwapUtil.swapOfTmp(nums, R, more);

        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 3, 8, 3, 1, 9, 0, 3};
//        netherlandsFlag1(nums, 3);
//        PrintUtil.toArray(nums);
//        netherlandsFlag2(nums, 3);
//        PrintUtil.toArray(nums);
        System.out.println("=============");
        int[] ints = netherlandsFlag(nums, 0, nums.length - 1);
        PrintUtil.toArray(nums);
        PrintUtil.toArray(ints);

    }
}
