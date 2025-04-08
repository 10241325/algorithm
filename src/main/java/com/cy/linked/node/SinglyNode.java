package com.cy.linked.node;

/**
 * @author changyuan
 */
public class SinglyNode {
    public SinglyNode next;
    public int value;

    public SinglyNode(int value, SinglyNode next) {
        this.value = value;
        this.next = next;
    }

    public static void print(SinglyNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }


}
