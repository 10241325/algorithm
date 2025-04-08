package com.cy.linked.queue;

/**
 * @author changyuan
 */
public class Node<V> {
    public V value;
    public Node<V> prev;
    public Node<V> next;

    public Node(V value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}
