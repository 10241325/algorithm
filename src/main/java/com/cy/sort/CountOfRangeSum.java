package com.cy.sort;

/**
 * 给定一个数组arr,两个整数lower和upper
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 * <p>
 * 假设0~i整体的累加和是x
 * 求必须以i位置结尾的子数组，有多少个在[lower,upper]范围上，
 * 就等价于去求i之前的所有前缀和中有多少个前缀和在[x-upper,x-lower]上
 * 假设以i结尾的前缀和为100 目标为[10,40]
 * 则只要能在i之前的前缀和中找到[60,90]的位置
 * 则i的前缀和到该位置的和就为[10,40]
 * 假设0~j的前缀和在[60,90]
 * 则i-j 就 = [100-90,100-60] 就在[10,40]之间
 *
 * @author changyuan
 * @date 2025-02-24 16:38:29
 */
public class CountOfRangeSum {

    public static int countOfRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 前缀和数组
        long[] prefixArr = new long[nums.length];
        prefixArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixArr[i] = prefixArr[i - 1] + nums[i];
        }

        return countOfRangeSum(prefixArr, 0, nums.length - 1, lower, upper);
    }

    public static int countOfRangeSum(long[] prefixArr, int L, int R, int lower, int upper) {
        if (L == R) {
            // 表示0到L上前缀和刚好在lower和upper上
            return prefixArr[L] <= upper && prefixArr[L] >= lower ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        return countOfRangeSum(prefixArr, L, mid, lower, upper)
                + countOfRangeSum(prefixArr, mid + 1, R, lower, upper)
                + merge(prefixArr, L, mid, R, lower, upper);
    }

    public static int merge(long[] prefixArr, int L, int M, int R, int lower, int upper) {
        // 遍历右侧 只要发现左侧有该区间的数据 则该区间有几个数则表示到右侧该数有符合条件的数据
        // 假设右边是index位置是9 lower = 1 upper = 2
        // 则左侧 在[7,8]上面的都是符合条件的，应为此时的9-[7,8]范围就落在[1,2]上
        // 使用归并排序的好处就是 右侧有序 导致windowL和windowR指针不会回退
        int windowL = L;
        int windowR = L;
        int ans = 0;
        for (int i = M + 1; i <= R; i++) {
            long min = prefixArr[i] - upper;
            long max = prefixArr[i] - lower;
            while (windowR <= M && prefixArr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && prefixArr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        // 归并排序
        long[] helper = new long[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int index = 0;
        while (p1 <= M && p2 <= R) {
            helper[index++] = prefixArr[p1] <= prefixArr[p2] ? prefixArr[p1++] : prefixArr[p2++];
        }
        while (p1 <= M) {
            helper[index++] = prefixArr[p1++];
        }
        while (p2 <= R) {
            helper[index++] = prefixArr[p2++];
        }
        for (index = 0; index < helper.length; index++) {
            prefixArr[L + index] = helper[index];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0, -1, -2, -3, 0, 2};
        System.out.println(countOfRangeSum(arr, 6, 5));
    }
}
