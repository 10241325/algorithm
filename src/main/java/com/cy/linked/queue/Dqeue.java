package com.cy.linked.queue;

/**
 * @author changyuan
 */
public class Dqeue<V> {
    private Node<V> head;
    private Node<V> tail;
    private int size;

    public Dqeue() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushHead(V value) {
        Node<V> cur = init(value);
        if (null == head) {
            head = tail = cur;
        } else {
            cur.next = head;
            head.prev = cur;
            head = cur;
        }
        size++;
    }

    public void pushTail(V value) {
        Node<V> cur = init(value);
        if (null == tail) {
            head = tail = cur;
        } else {
            tail.next = cur;
            cur.prev = tail;
            tail = cur;
        }
        size++;
    }

    public V popHead() {
        if (null == head) {
            return null;
        }
        V ans = head.value;
        size--;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        return ans;
    }

    public V peekHead() {
        return null != head ? head.value : null;
    }

    public V popTail() {
        if (null == tail) {
            return null;
        }
        V ans = tail.value;
        size--;
        if (tail == head) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return ans;
    }

    public static <V> Node<V> init(V v) {
        return new Node<>(v);
    }
}
