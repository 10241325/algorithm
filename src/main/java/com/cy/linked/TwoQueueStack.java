package com.cy.linked;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈
 *
 * @author changyuan
 */
public class TwoQueueStack {
    public Queue<Integer> queue;
    public Queue<Integer> help;
    public int size;

    public TwoQueueStack() {
        queue = new LinkedList<>();
        help = new LinkedList<>();
        size = 0;
    }

    public void push(int x) {
        queue.offer(x);
        size++;
    }

    public int pop() {
        // left不为空 将left中元素添加到right中 保留最后一个返回
        // right不为空 将right中元素添加到left中 保留最后一个返回
        if (size == 0) {
            throw new RuntimeException("Queue is empty!");
        }
        while (queue.size() != 1) {
            // 保留最后一个数据返回
            help.offer(queue.poll());
        }
        int ans = queue.poll();
        // 交换 queue 和 help
        Queue<Integer> tmp = help;
        help = queue;
        queue = tmp;

        size--;

        return ans;
    }

    public static void main(String[] args) {
        TwoQueueStack stack = new TwoQueueStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while (stack.size != 0) {
            System.out.println(stack.pop());
        }
        stack.push(5);
        stack.push(6);
        while (stack.size != 0) {
            System.out.println(stack.pop());
        }
    }
}
