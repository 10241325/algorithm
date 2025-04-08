package com.cy.linked;

import com.cy.linked.node.SinglyNode;

/**
 * 使用快慢指针获取链表中点
 *
 * @author changyuan
 * @date 2025-03-10 18:36:22
 */
public class MidNode {
    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上一个中点
     *
     * @return {@link SinglyNode}
     * @author changyuan
     * @date 2025-03-10 18:37:12
     */
    public static SinglyNode getLastMidNode(SinglyNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        SinglyNode slow = head;
        SinglyNode fast = head;
        // 强制fast指针一定要移动两个节点  奇数时最后一个不移动  这样如果为偶数时慢指针就会在上一个中点节点停息下来
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上一个中点
     *
     * @return {@link SinglyNode}
     * @author changyuan
     * @date 2025-03-10 18:37:12
     */
    public static SinglyNode getLastMidPrevNode(SinglyNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        SinglyNode slow = head;
        SinglyNode fast = head;
        SinglyNode prev = null;
        // 强制fast指针一定要移动两个节点  奇数时最后一个不移动  这样如果为偶数时慢指针就会在上一个中点节点停息下来
        while (fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下一个中点
     *
     * @return {@link SinglyNode}
     * @author changyuan
     * @date 2025-03-10 18:37:12
     */
    public static SinglyNode getNextMidNode(SinglyNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        SinglyNode slow = head;
        SinglyNode fast = head;
        //  这样如果为偶数时慢指针就会在下一个中点节点停息下来
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下一个中点
     *
     * @return {@link SinglyNode}
     * @author changyuan
     * @date 2025-03-10 18:37:12
     */
    public static SinglyNode getNextMidPrevNode(SinglyNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        SinglyNode slow = head;
        SinglyNode fast = head;
        SinglyNode prev = head;
        //  这样如果为偶数时慢指针就会在下一个中点节点停息下来
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.get();
        SinglyNode head1 = new SinglyNode(1, new SinglyNode(2, new SinglyNode(3, new SinglyNode(2, new SinglyNode(1, null)))));
        SinglyNode head2 = new SinglyNode(1, new SinglyNode(2, new SinglyNode(3, new SinglyNode(4, null))));
        SinglyNode.print(head1);
        SinglyNode.print(head2);
        System.out.println("=============偶数长度返回上一个中点");
        SinglyNode.print(getLastMidNode(head1));
        SinglyNode.print(getLastMidNode(head2));
        System.out.println("=============上中点前一个");
        SinglyNode.print(getLastMidPrevNode(head1));
        SinglyNode.print(getLastMidPrevNode(head2));
        System.out.println("=============偶数长度返回下一个中点");
        SinglyNode.print(getNextMidNode(head1));
        SinglyNode.print(getNextMidNode(head2));
        System.out.println("=============下中点前一个");
        SinglyNode.print(getNextMidPrevNode(head1));
        SinglyNode.print(getNextMidPrevNode(head2));

    }
}
