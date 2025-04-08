package com.cy.linked;

import com.cy.linked.node.SinglyNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制链表
 *
 * @author changyuan
 * @date 2025-03-11 12:29:58
 */
public class CopyListWithRand {
    static class Node {
        public int value;
        public Node next;
        /**
         * rand随机指向链表中的某个节点
         */
        public Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用map复制
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-03-11 12:30:40
     */
    public static Node copyListWithRandWithMap(Node head) {
        Map<Node, Node> map = new HashMap<>();
        // 克隆节点
        Node cur = head;
        while (null != cur) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        // 将克隆节点串起来
        cur = head;
        while (null != cur) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return map.get(head);
    }

    /**
     * 不使用map 将克隆节点挂在节点后
     * 这样克隆节点就是cur.next
     * 1 2 3 4 5
     * 1 1 2 2 3 3 4 4 5 5
     * 遍历新链表设置克隆节点的rand节点
     * 到这里 老的链表rand没动 新的rand和老的一致
     * 然后在恢复next指针
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-03-11 12:37:39
     */
    public static Node copyListWithRand(Node head) {
        if (null == head) {
            return null;
        }
        // 克隆节点
        Node cur = head;
        Node next;
        while (cur != null) {
            // 下一个原始节点
            next = cur.next;
            // 当前节点指向克隆节点
            cur.next = new Node(cur.value);
            // 克隆节点指向下一个原始节点
            cur.next.next = next;

            cur = next;
        }
        // 设置克隆节点的rand
        cur = head;
        Node curCopy;
        while (null != cur) {
            // 下一个原始节点
            next = cur.next.next;
            // 当前节点的克隆节点
            curCopy = cur.next;
            // 设置克隆节点的rand
            curCopy.rand = null != cur.rand ? cur.rand.next : null;
            cur = next;
        }
        // 恢复节点
        cur = head;
        Node res = cur.next;
        while (null != cur) {
            // 下一个原始节点
            next = cur.next.next;
            // 克隆节点
            curCopy = cur.next;
            // 当前原始节点指向下一个原始节点
            cur.next = next;
            // 当前克隆节点指向下一个克隆节点，如果没有下一个原始节点了 肯定没有下一个克隆节点了
            curCopy.next = next != null ? next.next : null;

            cur = next;
        }
        return res;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        copyListWithRand(node1);
    }
}
