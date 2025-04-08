package com.cy.linked;

import com.cy.linked.node.DoublyNode;
import com.cy.linked.node.SinglyNode;
import com.cy.util.PrintUtil;

/**
 * @author changyuan
 */
public class LinkedUtil {
    /**
     * 反转单链表（o(n)）
     *
     * @param head 单链表头节点
     * @return {@link SinglyNode}
     * @author changyuan
     * @date 2025-02-18 09:51:39
     */
    public static SinglyNode reserve(SinglyNode head) {
        // 记录head下一个节点
        SinglyNode next;
        // 前一个节点
        SinglyNode pre = null;
        while (head != null) {
            // 下一个节点
            next = head.next;
            // 当前节点的下一个节点改成前一个节点
            head.next = pre;
            // 当前节点改成前一个节点
            pre = head;
            // 头结点移动到下一个节点
            head = next;
        }
        // 返回前一个节点 最后一步head指向了null 所以前一个节点就是原本链表的尾节点
        return pre;
    }

    public static DoublyNode reserve(DoublyNode head) {
        DoublyNode next;
        DoublyNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;

            pre = head;
            head = next;
        }
        return pre;
    }

    public static SinglyNode mergeTwoLists(SinglyNode head1, SinglyNode head2) {
        if (null == head1 || null == head2) {
            return null == head1 ? head2 : head1;
        }
        SinglyNode head = head1.value < head2.value ? head1 : head2;
        SinglyNode cur1 = head.next;
        SinglyNode cur2 = head == head1 ? head2 : head1;
        SinglyNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.value < cur2.value) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    public static SinglyNode addLinked(SinglyNode head1, SinglyNode head2) {
        if (head1 == null || head2 == null) {
            return head2 == null ? head1 : head2;
        }

        int len1 = sizeOfLinked(head1);
        int len2 = sizeOfLinked(head2);
        // 重定向 l指向常链表 s指向短链表
        SinglyNode l = len1 > len2 ? head1 : head2;
        SinglyNode s = l == head1 ? head2 : head1;
        SinglyNode ret = l;
        int carry = 0;
        SinglyNode last = null;
        // 短链表
        while (s != null) {
            int value = carry + s.value + l.value;
            l.value = value % 10;
            carry = value / 10;
            last = l;
            s = s.next;
            l = l.next;
        }
        // 常链表剩余
        while (l != null) {
            int value = carry + l.value;
            l.value = value % 10;
            carry = value / 10;
            last = l;
            l = l.next;
        }
        // 进位
        if (carry > 0) {
            last.next = new SinglyNode(1, null);
        }
        return ret;
    }

    public static SinglyNode removeNum(SinglyNode head, int num) {

        while (null != head && head.value == num) {
            // 找到第一个不是num的头节点
            head = head.next;
        }
        SinglyNode ans = head;
        SinglyNode pre = head;
        // 从下一个节点开始
        head = head.next;
        while (null != head) {
            if (head.value == num) {
                // 遇到num就跳过
                pre.next = head.next;
            } else {
                // 否则pre就是等于当前节点
                pre = head;
            }
            head = head.next;
        }
        return ans;
    }

    private static int sizeOfLinked(SinglyNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static SinglyNode getMidNode(SinglyNode head) {
        SinglyNode slow = head;
        SinglyNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        SinglyNode head = new SinglyNode(1, new SinglyNode(2, new SinglyNode(2, new SinglyNode(3, new SinglyNode(4, new SinglyNode(2, null))))));
        SinglyNode.print(removeNum(head, 2));
    }
}
