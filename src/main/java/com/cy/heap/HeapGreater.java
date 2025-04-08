package com.cy.heap;

import java.util.*;

/**
 * 加强堆
 *
 * @author changyuan
 * @date 2025-03-09 14:02:19
 */
public class HeapGreater<T> {
    /**
     * 堆
     */
    private final List<T> heap;
    /**
     * 反向索引表
     * 记录对象和对象在堆中的索引位置
     */
    private final Map<T, Integer> indexMap;
    /**
     * 堆大小
     */
    private int heapSize;
    /**
     * 比较器
     */
    private final Comparator<T> comparator;

    public HeapGreater(Comparator<T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T t) {
        if (indexMap.containsKey(t)) {
            return;
        }
        heap.add(t);
        indexMap.put(t, heapSize);
        swim(heapSize++);
    }

    public T pop() {
        T t = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(t);
        heap.remove(--heapSize);
        sink(0);
        return t;
    }

    private void swim(int index) {
        int parentIndex;
        while (comparator.compare(heap.get((parentIndex = (index - 1) / 2)), heap.get(index)) > 0) {
            swap(parentIndex, index);
            index = parentIndex;
        }
    }

    private void sink(int index) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            int rightIndex = leftIndex + 1;
            int minIndex = rightIndex < heapSize
                    && comparator.compare(heap.get(rightIndex), heap.get(leftIndex)) < 0
                    ? rightIndex : leftIndex;
            minIndex = comparator.compare(heap.get(minIndex), heap.get(index)) < 0 ? minIndex : index;
            if (minIndex == index) {
                // 不需要改位置了
                break;
            }
            swap(minIndex, index);
            index = minIndex;
            leftIndex = index * 2 + 1;
        }
    }

    public void remove(T t) {
        if (!indexMap.containsKey(t)) {
            return;
        }
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(t);
        indexMap.remove(t);
        heap.remove(--heapSize);
        if (t != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public List<T> getAllElements() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < heapSize; i++) {
            list.add(heap.get(i));
        }
        return list;
    }

    public void resign(T t) {
        int index = indexMap.get(t);
        swim(index);
        if (indexMap.get(t) == index) {
            // 做了上浮就不会下沉
            sink(indexMap.get(t));
        }
    }

    private void swap(int i, int j) {
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        heap.set(i, t2);
        heap.set(j, t1);
        indexMap.put(t1, j);
        indexMap.put(t2, i);
    }

    public static void main(String[] args) {
        HeapGreater<Integer> heapGreater = new HeapGreater<>(Comparator.comparingInt(o -> o));
        heapGreater.push(4);
        heapGreater.push(3);
        heapGreater.push(1);
        heapGreater.push(2);
        heapGreater.remove(1);
        System.out.println();
    }
}
