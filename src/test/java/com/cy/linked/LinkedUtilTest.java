package com.cy.linked;

import com.cy.linked.node.DoublyNode;
import com.cy.linked.node.SinglyNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LinkedUtilTest {
    @Test
    public void testSinglyReserve() {
        SinglyNode node = new SinglyNode(1, new SinglyNode(2, new SinglyNode(3, null)));
        SinglyNode.print(node);
        System.out.println("反转后===============");
        SinglyNode.print(LinkedUtil.reserve(node));
    }

    @Test
    public void testDoublyReserve() {
        DoublyNode node1 = new DoublyNode(1, null, null);
        DoublyNode node2 = new DoublyNode(2, null, node1);
        node1.next = node2;
        node2.next = new DoublyNode(3, null, node2);
        DoublyNode.print(node1);
        System.out.println("反转后===============");
        DoublyNode.print(LinkedUtil.reserve(node1));
    }

    @Test
    public void testAddLinked() {
        SinglyNode node1 = new SinglyNode(9, new SinglyNode(9, new SinglyNode(9, new SinglyNode(9, null))));
        SinglyNode node2 = new SinglyNode(1, null);
        SinglyNode.print(LinkedUtil.addLinked(node1, node2));
    }

    @Test
    public void testMergeTwoLists() {
        SinglyNode node1 = new SinglyNode(2, new SinglyNode(4, new SinglyNode(6, new SinglyNode(8, null))));
        SinglyNode node2 = new SinglyNode(1, new SinglyNode(3, new SinglyNode(5, new SinglyNode(7, null))));
        SinglyNode.print(LinkedUtil.mergeTwoLists(node1, node2));
    }
}
