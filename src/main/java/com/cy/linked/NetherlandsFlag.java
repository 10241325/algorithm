package com.cy.linked;

import com.cy.linked.node.SinglyNode;

/**
 * 链表荷兰国旗问题
 *
 * @author changyuan
 * @date 2025-03-11 11:32:44
 */
public class NetherlandsFlag {
    public static SinglyNode netherlandsFlag(SinglyNode head, int k) {
        SinglyNode smallHead = null, smallTail = null,
                midHead = null, midTail = null,
                greaterHead = null, greaterTail = null;
        SinglyNode next;
        while (null != head) {
            next = head.next;
            head.next = null;
            if (head.value < k) {
                if (null == smallHead) {
                    smallHead = smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            }
            if (head.value == k) {
                if (null == midHead) {
                    midHead = midTail = head;
                } else {
                    midTail.next = head;
                    midTail = head;
                }
            }
            if (head.value > k) {
                if (null == greaterHead) {
                    greaterHead = greaterTail = head;
                } else {
                    greaterTail.next = head;
                    greaterTail = head;
                }
            }
            head = next;
        }
//        SinglyNode ansHead, ansTail;
//        ansHead = ansTail = new SinglyNode(-1, null);
//        if (null != smallHead) {
//            ansTail.next = smallHead;
//            ansTail = smallTail;
//        }
//        if (null != midHead) {
//            ansTail.next = midHead;
//            ansTail = midTail;
//        }
//        if (null != greaterHead) {
//            ansTail.next = greaterHead;
//        }
//        return ansHead.next;
        if (null != smallTail) {
            // 有小于区
            // 不用管是否有没有等于区
            smallTail.next = midHead;
            // 没有等于区 则尾节点为小于区尾节点 否则为等于区尾节点
            midTail = null == midTail ? smallTail : midTail;
        }
        // 上一步中如果midTail为null 则表示既没有小于区也没有等于区
        if (null != midTail) {
            midTail.next = greaterHead;
        }

        return null != smallHead ? smallHead : (null != midHead ? midHead : greaterHead);
    }

    public static void main(String[] args) {
        SinglyNode head1 = new SinglyNode(4, new SinglyNode(5, new SinglyNode(3, new SinglyNode(2, new SinglyNode(1, null)))));
        SinglyNode.print(netherlandsFlag(head1, 3));

    }
}
