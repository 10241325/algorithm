package com.cy.linked;

/**
 * 判断两个链表是否相交
 * 1、A有环 B无环  必然不会相交
 * 2、A有环 B有环  入环节点相同  环外相交  环内相交
 * 3、A无环 B有环  必然不会相交
 * 4、A无环 B无环  无环链表相交问题
 *
 * @author changyuan
 */
public class GetIntersectionNode {
    public static RingList.Node getIntersectionNode(RingList.Node head1, RingList.Node head2) {
        RingList.Node loopNode1 = RingList.getLoopNode(head1);
        RingList.Node loopNode2 = RingList.getLoopNode(head2);
        if (null == loopNode1 && null == loopNode2) {
            // A无环 B无环 无环链表相交问题
            return RingList.getNoLoopIntersectionNode(head1, head2);
        }
        if (null != loopNode1 && null != loopNode2) {
            // A有环 B有环  入环节点相同  环外相交  环内相交
            if (loopNode1 == loopNode2) {
                // 入环节点相同  环外相交 （包括相交节点就是入环节点）
                return RingList.getLoopNode(head1, head2, loopNode1);
            } else {
                // 环内相交:自己转一圈发现没有遇到则不相交
                RingList.Node cur = loopNode1.next;
                while (loopNode1 != cur) {
                    if (cur == loopNode2) {
                        // 环内相交 返回任意一个
                        return loopNode1;
                    }
                    cur = cur.next;
                }
                return null;
            }
        }
        // 一个无环 一个有环 必然不会相交
        return null;
    }
}
