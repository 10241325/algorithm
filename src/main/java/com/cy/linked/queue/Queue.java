package com.cy.linked.queue;

/**
 * @author changyuan
 */
public class Queue<V> {
    private Node<V> head;
    private Node<V> tail;
    private int size;

    public Queue() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void offer(V value) {
        Node<V> cur = new Node<>(value);
        if (null == head) {
            head = tail = cur;
        } else {
            tail.next = cur;
            tail = cur;
        }
        size++;
    }

    public V poll() {
        V ans = null;
        if (null != head) {
            ans = head.value;
            head = head.next;
            size--;
        }
        return ans;
    }

    public V peek() {
        return null != head ? head.value : null;
    }
}
