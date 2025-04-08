package com.cy.sort;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SortUtilTest {
    @Test
    public void testBubbleSort() {
        int[] arr = new int[]{899, 4, 5, 6, 77, 8, 99, 10, 11111, 12, 13, 14, 1, 189};
        SortUtil.bubbleSort(arr);
    }

    @Test
    public void testSelectionSort() {
        int[] arr = new int[]{899, 4, 5, 6, 77, 8, 99, 10, 11111, 12, 13, 14, 1, 189};
        SortUtil.selectionSort(arr);
    }

    @Test
    public void testInsertionSort() {
        int[] arr = new int[]{899, 4, 5, 6, 77, 8, 99, 10, 11111, 12, 13, 14, 1, 189};
        SortUtil.insertionSort(arr);
    }
}
