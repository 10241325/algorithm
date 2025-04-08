package com.cy.linked;

import java.util.Stack;

/**
 * 两个栈实现队列
 *
 * @author changyuan
 * @date 2025-02-23 10:15:55
 */
public class TwoStackQueue {
    public Stack<Integer> push;
    public Stack<Integer> pop;
    public int size;

    public TwoStackQueue() {
        push = new Stack<>();
        pop = new Stack<>();
        size = 0;
    }

    private void pushToPop() {
        // 1、只有pop栈为空 才能才能够push导数据
        // 2、push需要一次性导完到pop
        if (pop.isEmpty()) {
            while (!push.isEmpty()) {
                pop.push(push.pop());
            }
        }
    }

    public void push(int value) {
        push.push(value);
        pushToPop();
        size++;
    }

    public int pop() {
        if (push.isEmpty() && pop.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        pushToPop();
        size--;
        return pop.pop();
    }

    public int peek() {
        if (push.isEmpty() && pop.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        return pop.peek();
    }

    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        while (queue.size != 0) {
            System.out.println(queue.pop());
        }
        queue.push(6);
        System.out.println(queue.pop());
    }
}
