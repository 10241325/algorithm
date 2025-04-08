package com.cy;


import com.cy.util.PrintUtil;

import java.util.*;

/**
 * @author changyuan
 */
public class Main {

    public static int[] doubleColorBall() {
        int[] arr = new int[7];
        // 生成6个红球
        int i = 0;
        Set<Integer> set = new HashSet<>();
        int ball;
        for (; i < arr.length - 1; i++) {
            do {
                ball = randomValue(33) + 1;
            } while (set.contains(ball));
            set.add(ball);
            arr[i] = ball;
        }
        arr[i] = 34;
        Arrays.sort(arr);
        // 生成一个蓝球
        arr[i] = randomValue(16) + 1;
//        for (i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
        return arr;
    }

    public static int random(int max) {
        return (int) (Math.random() * (max)) + 1;
    }

    public static void main(String[] args) {
        int times = 19920208;
        int[] ans;
        for (int i = 0; i < times; i++) {
            ans = doubleColorBall();
            if (i == times - 1) {
                PrintUtil.toArray(ans);
            }
        }
    }

    public static int randomValue(int max) {
        return (int) (Math.random() * max);
    }

    public static int frogJump(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        return frogJump(n - 1) + frogJump(n - 2);
    }

    public static int frogJump2(int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        int i = 2;
        while (i <= n) {
            stack.push(stack.get(i - 1) + stack.get(i - 2));
            i++;
        }
        return stack.pop();
    }

    public static int frogJump3(int n) {
        if (n < 2) {
            return 1;
        }
        int start = 1;
        int end = 1;
        for (int i = 2; i <= n; i++) {
            int temp = end;
            end = start + end;
            start = temp;
        }
        return end;
    }
}