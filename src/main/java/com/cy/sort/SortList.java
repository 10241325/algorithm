package com.cy.sort;

import com.cy.linked.node.SinglyNode;

public class SortList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public static void print(ListNode head) {
            while (head != null) {
                System.out.print(head.val + " ");
                head = head.next;
            }
            System.out.println();
        }
    }

    public static ListNode sortList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        return sortList(head, null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode l1 = sortList(head, slow);
        ListNode l2 = sortList(slow, tail);
        return merge(l1, l2);
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode dummy = dummyHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                dummy.next = head1;
                head1 = head1.next;
            } else {
                dummy.next = head2;
                head2 = head2.next;
            }
            dummy = dummy.next;
        }
        while (head1 != null) {
            dummy.next = head1;
            head1 = head1.next;
            dummy = dummy.next;
        }
        while (head2 != null) {
            dummy.next = head2;
            head2 = head2.next;
            dummy = dummy.next;
        }
        return dummyHead.next;
    }

    public static int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(6,
                new ListNode(5
                        , new ListNode(4
                        , new ListNode(3
                        , new ListNode(2
                        , new ListNode(1, null))))));
        ListNode.print(node);
        ListNode.print(sortList(node));
    }
}
