package com.cy.sort;

import com.cy.util.RandomArrUtil;

import java.util.*;

public class CountSmaller {
    public static int[] indexArr;
    public static int[] ansArr;

    public static List<Integer> countSmaller(int[] nums) {
        if (null == nums || nums.length == 0) {
            return Collections.emptyList();
        }
        indexArr = new int[nums.length];
        ansArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexArr[i] = i;
        }
        countSmaller(nums, 0, nums.length - 1);
        ArrayList<Integer> result = new ArrayList<>();
        for (int num : ansArr) {
            result.add(num);
        }
        return result;
    }

    public static List<Integer> countSmaller1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return Collections.emptyList();
        }
        if (1 == nums.length) {
            return Collections.singletonList(0);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                result.add(0);
                break;
            }
            int ans = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    ans++;
                }
            }
            result.add(ans);
        }
        return result;
    }

    public static void countSmaller(int[] nums, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        countSmaller(nums, L, mid);
        countSmaller(nums, mid + 1, R);
        merge(nums, L, mid, R);
    }

    public static void merge(int[] nums, int L, int mid, int R) {
        int[] helper = new int[R - L + 1];
        int[] helperIndex = new int[helper.length];
        int p1 = mid;
        int p2 = R;
        int index = helper.length - 1;
        while (p1 >= L && p2 >= mid + 1) {
            if (nums[p2] < nums[p1]) {
                // 该索引位置的数据ans加加
                ansArr[indexArr[p1]] += p2 - mid;
                helper[index] = nums[p1];
                // 索引数组跟着排序
                helperIndex[index--] = indexArr[p1--];
            } else {
                helper[index] = nums[p2];
                // 索引数组跟着排序
                helperIndex[index--] = indexArr[p2--];
            }

        }
        while (p1 >= L) {
            helper[index] = nums[p1];
            helperIndex[index--] = indexArr[p1--];
        }
        while (p2 >= mid + 1) {
            helper[index] = nums[p2];
            helperIndex[index--] = indexArr[p2--];
        }
        for (index = 0; index < helper.length; index++) {
            indexArr[L + index] = helperIndex[index];
            nums[L + index] = helper[index];
        }
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 10;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = RandomArrUtil.generateRandomArray(maxSize, maxValue);
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            List<Integer> res1 = countSmaller(arr);
            List<Integer> res2 = countSmaller1(arr1);
            if (!check(res1, res2)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
    }

    public static boolean check(List<Integer> arr1, List<Integer> arr2) {
        if (arr1.size() != arr2.size()) {
            return false;
        }
        for (int i = 0; i < arr1.size(); i++) {
            if (!arr1.get(i).equals(arr2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
