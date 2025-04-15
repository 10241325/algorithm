package com.cy.linked;

import java.util.HashSet;

/**
 * 环链表
 *
 * @author changyuan
 * @date 2025-03-11 15:05:43
 */
public class RingList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 判断是否有环  有环返回第一个入环节点  无环返回null
     * 快慢指针 快指针一次走两步 慢指针一次走一步
     * 如果快指针走到null 没环
     * 如果有环  快指针一定在环内能追上慢指针
     * 追上的时候  慢指针不变  快指针指向head节点  慢指针和快指针一次走一步
     * 必然会在入环节点相遇
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-03-11 15:07:49
     */
    public static Node getLoopNode(Node head) {
        Node fast = head, slow = head;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * 容器实现找到第一个入环节点
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-04-15 09:16:42
     */
    public static Node getLoopNode1(Node head) {
        if (null == head) {
            return null;
        }
        HashSet<Node> set = new HashSet<>();
        while (null != head && !set.contains(head)) {
            set.add(head);
            head = head.next;
        }
        return head;
    }

    /**
     * 若链表head1和head2都无环 判断head1和head2是否相交 不想交返回null 相交返回相交节点
     * p1和p2一直往下一个节点走
     * 当p1!=p2时并且p1走到末尾 则p1指向p2头结点
     * 当p1!=p2时并且p2走到末尾 则p2指向p1头结点
     * 结果
     * 相交则p1==p2!=null
     * 不想交则p1==p2==null
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-03-11 15:58:16
     */
    public static Node getNoLoopIntersectionNode(Node headA, Node headB) {
        if (null == headA || null == headB) {
            return null;
        }
        Node p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = null == p1 ? headB : p1.next;
            p2 = null == p2 ? headA : p2.next;
        }
        return p1;
    }

    /**
     * 判断相交前长链表比短链表要多走n步 先让长链表走n步 然后一起走 则第一个相等节点为第一个相交节点
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-04-15 10:11:04
     */
    public static Node getNoLoopIntersectionNode1(Node headA, Node headB) {
        if (null == headA || null == headB) {
            return null;
        }
        Node p1 = headA, p2 = headB;
        int n = 0;
        while (null != p1.next) {
            n++;
            p1 = p1.next;
        }
        while (null != p2.next) {
            n--;
            p2 = p2.next;
        }
        // 此时p1和p2都走到了各自链表的尾节点 n的绝对值为相交前长链表需要多走的节点个数
        if (p1 != p2) {
            // 如果相交则尾节点必然相等
            return null;
        }
        // p1重定向为长链表
        p1 = n > 0 ? headA : headB;
        // p2重定向为短链表
        p2 = p1 == headA ? headB : headA;
        n = Math.abs(n);
        // 长链表先走n步
        while (n-- > 0) {
            p1 = p1.next;
        }
        // 一起走，相等时则为第一个相交节点
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 两个有环链表相交并且相交节点在入环节点前
     * 丫
     * ○
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-03-11 15:58:16
     */
    public static Node getLoopNode(Node headA, Node headB, Node loop) {
        if (null == headA || null == headB) {
            return null;
        }
        Node p1 = headA;
        Node p2 = headB;
        while (p1 != p2) {
            p1 = loop == p1 ? headB : p1.next;
            p2 = loop == p2 ? headA : p2.next;
        }
        return p1;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 删除链表中倒数第N个节点
     *
     * @return {@link ListNode}
     * @author changyuan
     * @date 2025-04-15 09:17:22
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode virtual = new ListNode(0, head);
        // p1指向head p2指向head的上一个虚拟节点
        ListNode p1 = head, p2 = virtual;
        while (n-- > 0) {
            // p1走n步
            p1 = p1.next;
        }
        // p1=null则表示删除的是第一个节点
        while (null != p1) {
            p1 = p1.next;
            // p2指向了需要删除的节点的上一个节点
            p2 = p2.next;
        }
        // 将下一个节点删除 就是把p2的next 指向next.next
        p2.next = p2.next.next;
        // 返回虚拟节点的下一个节点，为什么要虚拟节点 是因为有可能删除的是第一个head节点
        return virtual.next;
    }

    public static void main(String[] args) {
        // 入环
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node3;
        System.out.println((head = getLoopNode1(head)) != null ? head.value : null);
        System.out.println((head = getLoopNode(head)) != null ? head.value : null);
        // 删除倒数第n个节点
        ListNode head1 = new ListNode(1);
        ListNode node11 = new ListNode(2);
        ListNode node22 = new ListNode(3);
        ListNode node33 = new ListNode(4);
        head1.next = node11;
        node11.next = node22;
        node22.next = node33;
        ListNode head2 = removeNthFromEnd(head1, 2);
        System.out.println(head2);
        // 无环相交
        Node noLoopHead1 = new Node(1);
        Node noLoop1 = new Node(2);
        Node noLoop2 = new Node(3);
        Node noLoop3 = new Node(4);
        noLoopHead1.next = noLoop1;
        noLoop1.next = noLoop2;
        noLoop2.next = noLoop3;
        Node noLoopHead2 = new Node(1);
        Node noLoop11 = new Node(2);
        Node noLoop22 = new Node(3);
        Node noLoop33 = new Node(4);
        Node noLoop44 = new Node(-1);
        noLoopHead2.next = noLoop11;
        noLoop11.next = noLoop22;
        noLoop22.next = noLoop33;
        noLoop33.next = noLoop44;

        Node node = new Node(5);
        noLoop3.next = node;
        noLoop44.next = node;

        Node intersectionNode1 = getNoLoopIntersectionNode(noLoopHead1, noLoopHead2);
        Node intersectionNode2 = getNoLoopIntersectionNode1(noLoopHead1, noLoopHead2);
        System.out.println(intersectionNode1 == intersectionNode2);
    }
}
