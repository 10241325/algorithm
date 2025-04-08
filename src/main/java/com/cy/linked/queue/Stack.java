package com.cy.linked.queue;

/**
 * @author changyuan
 */
public class Stack<V> {
    public Node<V> head;
    public int size;

    public Stack() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(V value) {
        Node<V> cur = new Node<>(value);
        cur.next = head;
        head = cur;
        size++;
    }

    public V pop() {
        if (null == head) {
            return null;
        }
        V ans = head.value;
        head = head.next;
        size--;
        return ans;
    }
}
