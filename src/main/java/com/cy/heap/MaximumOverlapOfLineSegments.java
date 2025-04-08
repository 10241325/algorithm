package com.cy.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 线段最大重合问题
 * <p>
 * 给定很多线段，每个线段都有两个数[start,end]
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定:
 * 1、线段的开始和结束位置一定都是整数值
 * 2、线段的重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 *
 * @author changyuan
 * @date 2025-03-09 13:26:14
 */
public class MaximumOverlapOfLineSegments {
    /**
     * 暴力解法
     *
     * @return {@link int}
     * @author changyuan
     * @date 2025-03-09 13:39:02
     */
    public int maxOverlap_1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] ints : lines) {
            min = Math.min(min, ints[0]);
            max = Math.max(max, ints[0]);
        }

        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int[] line : lines) {
                if (line[0] < p && line[1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cur, cover);
        }
        return cover;
    }

    /**
     * 使用堆 O(N*logN)
     *
     * @return {@link int}
     * @author changyuan
     * @date 2025-03-09 13:39:20
     */
    public int maxOverlap_2(int[][] lines) {
        Line[] linesArr = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            linesArr[i] = new Line(lines[i][0], lines[i][1]);
        }
        // 根据start排序 这样做的目的是保证堆中的结尾数据都是大于等于下一条线段的开始
        Arrays.sort(linesArr, new StartComparator());
        // 小跟堆 放每一条线段的结尾数值
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (Line line : linesArr) {
            // 将之前结尾线段没有穿过当前线段的数据移除
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            // 将当前线段结尾加进去
            heap.add(line.end);
            // 此时该堆中的数据都是穿过当前线段的数据
            // 也即当前数据重合的数量
            max = Math.max(max, heap.size());
        }
        return max;
    }

    static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line a, Line b) {
            Collections.shuffle(Arrays.asList(a.start, a.end));
            return a.start - b.start;
        }
    }
}
