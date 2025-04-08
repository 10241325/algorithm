package com.cy.linked;

import com.cy.linked.node.SinglyNode;

import java.util.Stack;

/**
 * 是否为回文链表
 * 1 2 3 2 1
 * 1 2 2 1
 *
 * @author changyuan
 * @date 2025-03-10 15:55:15
 */
public class IsPalindromeList {
    /**
     * 通过栈实现
     *
     * @param head
     * @return {@link boolean}
     * @author changyuan
     * @date 2025-03-10 15:55:49
     */
    public static boolean isPalindromeForStack(SinglyNode head) {
        Stack<SinglyNode> stack = new Stack<>();
        SinglyNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (null != head) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome(SinglyNode head) {
        if (null == head || head.next == null) {
            return true;
        }
        // 找到中点  若是偶数节点 返回上中点
        SinglyNode fast = head;
        SinglyNode slow = head;
        while (null != fast.next && null != fast.next.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        SinglyNode pre = slow;
        // midNode后节点逆序指向midNode节点 midNode指向空节点
        SinglyNode cur = pre.next, next;
        pre.next = null;
        while (null != cur) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 判断是否为回文链表
        boolean ans = true;
        SinglyNode left = head, right = pre;
        while (null != left && null != right) {
            if (left.value != right.value) {
                ans = false;
                break;
            }
            left = left.next;
            right = right.next;
        }
        // 恢复链表
        cur = pre;
        pre = null;
        while (null != cur) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
//        if (null == head || head.next == null) {
//            return true;
//        }
//        // 快慢指针获取中间节点 偶数返回上一个中点
//        SinglyNode prev = MidNode.getLastMidNode(head);
//        // midNode后面节点开始逆序
//        SinglyNode cur = prev.next;
//        prev.next = null;
//        SinglyNode next;
//        while (null != cur) {
//            next = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = next;
//        }
//        // 校验是否为回文链表
//        SinglyNode right = prev, left = head;
//        boolean ans = true;
//        // 因为偶数时中点是上一个 所以left必然比right短 left会先到null
//        while (null != left) {
//            if (right.value != left.value) {
//                ans = false;
//                break;
//            }
//            right = right.next;
//            left = left.next;
//        }
//        cur = prev;
//        prev = null;
//        while (null != cur) {
//            next = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = next;
//        }
//        return ans;
//        if (null == head || head.next == null) {
//            return true;
//        }
//        SinglyNode slow = head;
//        SinglyNode fast = head;
//        while (null != fast && null != fast.next) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        // slow 就是中点 下一个节点开始逆序
//        // 1——>2——>3<——2<——1
//        //         |
//        //        null
//        // 这里的3就是slow,从后面的2开始逆序
//        fast = slow.next;
//        slow.next = null;
//        SinglyNode next;
//        while (null != fast) {
//            next = fast.next;
//            // 2先指向3
//            fast.next = slow;
//            slow = fast;
//            fast = next;
//        }
//        boolean res = true;
//        // 此时的slow是最后一个节点 fast是第一个节点
//        next = slow;
//        fast = head;
//        while (null != slow && null != fast) {
//            if (slow.value != fast.value) {
//                res = false;
//                break;
//            }
//            slow = slow.next;
//            fast = fast.next;
//        }
//        // 恢复指针
//        // next指针是最后一个节点
//        slow = next.next;
//        next.next = null;
//        while (null != slow) {
//            fast = slow.next;
//            slow.next = next;
//            next = slow;
//            slow = fast;
//        }
//        return res;
    }

    public static void main(String[] args) {
        SinglyNode head1 = new SinglyNode(1, new SinglyNode(2, new SinglyNode(3, new SinglyNode(2, new SinglyNode(1, null)))));
        SinglyNode head2 = new SinglyNode(1, new SinglyNode(2, new SinglyNode(2, new SinglyNode(1, null))));
        SinglyNode head3 = new SinglyNode(1, new SinglyNode(4, new SinglyNode(2, new SinglyNode(1, null))));
        System.out.println(isPalindromeForStack(head1));
        System.out.println(isPalindromeForStack(head2));
        System.out.println(isPalindromeForStack(head3));
        System.out.println(isPalindrome(head1));
        System.out.println(isPalindrome(head2));
        System.out.println(isPalindrome(head3));
    }
}
