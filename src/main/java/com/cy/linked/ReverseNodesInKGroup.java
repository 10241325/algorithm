package com.cy.linked;

/**
 * k个节点的组内逆序调整
 *
 * @author changyuan
 */
public class ReverseNodesInKGroup {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    /**
     * k个节点的组内逆序调整
     *
     * @param head 头节点
     * @param k    k个元素
     * @return {@link ListNode}
     * @author changyuan
     * @date 2025-02-19 12:56:20
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        // 获取k个节点
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            // 不够k个直接返回
            return head;
        }
        // 够k个 是第一个反转的尾节点 这个尾节点一定是处理好数据后的头节点
        head = end;
        // 反转k个节点 反转后start指向下一个需要开始的节点
        reverse(start, end);
        // 上一组的结尾节点
        ListNode lastEnd = start;
        // 上一组后面有节点
        while (lastEnd.next != null) {
            // start从下一个节点开始
            start = lastEnd.next;
            // 继续获取k个节点
            end = getKGroupEnd(start, k);
            if (end == null) {
                // 不够返回head 不需要反转 并且上次反转已经把反转后的start连到最后一个节点了
                return head;
            }
            // 够就反转
            reverse(start, end);
            // 上一个start节点连的是下一组的start节点 需要把上一个最后一个节点连到end上
            lastEnd.next = end;
            // lastEnd为本次的开始节点
            lastEnd = start;
        }
        return head;
    }

    /**
     * 从start开始数够k个节点 返回最后一个节点 不够返回null
     *
     * @param start 开始节点
     * @param k     k个节点
     * @return {@link ListNode}
     * @author changyuan
     * @date 2025-02-19 12:26:14
     */
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end) {
        // 记录end后面一个节点
        end = end.next;
        ListNode prev = null;
        ListNode curr = start;
        ListNode next;
        // 当前节点不等于end后一个节点，循环走完刚好把start到end反转
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // start没有动，一直指向start节点，但此时start节点是最后一个节点 最后一个节点指向end后面一个节点
        start.next = end;
    }
}
