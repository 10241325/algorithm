package com.cy.linked;

/**
 * 换链表
 *
 * @author changyuan
 * @date 2025-03-11 15:05:43
 */
public class RingList {
    static class Node {
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
        Node fast = head;
        Node slow = head;
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
     * 若链表head1和head2都无环 判断head1和head2是否相交 不想交返回null 相交返回相交节点
     *
     * @return {@link Node}
     * @author changyuan
     * @date 2025-03-11 15:58:16
     */
    public static Node getLoopNode(Node headA, Node headB) {
        if (null == headA || null == headB) {
            return null;
        }
        Node p1 = headA;
        Node p2 = headB;
        while (p1 != p2) {
            p1 = null == p1 ? headB : p1.next;
            p2 = null == p2 ? headA : p2.next;
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

    static class ListNode {
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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head, second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (null != first) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        removeNthFromEnd(listNode, 1);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        Node node15 = new Node(15);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        node15.next = node7;
        Node ring = getLoopNode(node1);
        Node head1 = new Node(1);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head4 = new Node(4);
        Node head5 = new Node(5);
        Node head6 = new Node(6);
        Node head7 = new Node(7);

        head1.next = head2;
        head2.next = head3;
        head3.next = head6;
        head6.next = head7;
        head4.next = head5;
//        head5.next = head7;

        Node loopNode = getLoopNode(head1, head4);
        System.out.println();
    }
}
