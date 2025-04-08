package com.cy.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SearchUtilTest {
    @Test
    public void testBinarySearch() {
        int[] arr = new int[]{1, 3, 5, 7, 9, 11};
        int target = 7;
        int result = SearchUtil.binarySearch(arr, target);
        System.out.println(result);
    }

    @Test
    public void testLessThanEqualRight() {
        int[] arr = new int[]{1, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 6};
        int target = 2;
        int result = SearchUtil.lessThanEqualRight(arr, target);
        System.out.println(result);
    }

    @Test
    public void testMoreThanEqualLeft() {
        int[] arr = new int[]{1, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 6};
        int target = 1;
        int result = SearchUtil.moreThanEqualLeft(arr, target);
        System.out.println(result);
    }

    @Test
    public void testLocalMin() {
        int[] arr = new int[]{9, 8, 3, 4, 5, 6};
        int result = SearchUtil.localMin(arr);
        System.out.println(result);
    }
}
