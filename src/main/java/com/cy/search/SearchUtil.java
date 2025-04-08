package com.cy.search;

/**
 * @author changyuan
 */
public class SearchUtil {
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                low = mid + 1;
            }
            if (arr[mid] > target) {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int lessThanEqualRight(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int ans = -1;
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (nums[mid] <= target) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return ans;
    }

    public static int moreThanEqualLeft(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int ans = -1;
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    public static int localMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums[0] < nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] < nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1]) {
                return mid;
            }
            if (nums[mid] > nums[mid + 1]) {
                L = mid + 1;
                continue;
            }
            if (nums[mid] > nums[mid - 1]) {
                R = mid - 1;
            }
        }

        return -1;
    }
}
