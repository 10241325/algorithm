package com.cy.linked;

import com.cy.linked.node.SinglyNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并多个升序链表
 *
 * @author changyuan
 */
public class MergeKSortedLinked {
    public static SinglyNode merge(SinglyNode[] nodeList) {
        if (nodeList == null || nodeList.length == 0) {
            return null;
        }
        // 小根堆 优先级队列
        PriorityQueue<SinglyNode> heap
                = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        for (SinglyNode node : nodeList) {
            if (node != null) {
                heap.add(node);
            }
        }

        SinglyNode head, tail;
        // 弹出第一个节点
        head = tail = heap.poll();
        if (null != head.next) {
            heap.add(head.next);
        }
        SinglyNode cur;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            tail.next = cur;
            tail = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        SinglyNode node1 = new SinglyNode(1, new SinglyNode(2, new SinglyNode(3, null)));
        SinglyNode node2 = new SinglyNode(1, new SinglyNode(6, new SinglyNode(9, null)));
        SinglyNode node3 = new SinglyNode(1, new SinglyNode(2, new SinglyNode(3, null)));
        SinglyNode.print(merge(new SinglyNode[]{node1, node2, node3}));
    }
}
