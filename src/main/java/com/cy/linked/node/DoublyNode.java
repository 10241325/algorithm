package com.cy.linked.node;

/**
 * @author changyuan
 */
public class DoublyNode {
    public DoublyNode next;
    public DoublyNode prev;
    public int value;

    public DoublyNode(int value, DoublyNode next, DoublyNode prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public static void print(DoublyNode head) {
        DoublyNode tail = head;
        System.out.print("从前往后：> ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
            if (head != null) {
                tail = head;
            }
        }
        System.out.print("\n从后往前：> ");
        while (tail != null) {
            System.out.print(tail.value + " ");
            tail = tail.prev;
        }
        System.out.println();
    }
}
