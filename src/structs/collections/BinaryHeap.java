package structs.collections;

import java.util.Arrays;

public class BinaryHeap<T extends Comparable<? super T>> {
    private T[] heap;
    private int size;
    public enum HeapOrder {MIN, MAX};
    private HeapOrder order;

    public BinaryHeap(HeapOrder order) {
        heap = (T[]) new Comparable[2];
        size = 0;
        this.order = order;
    }

    public void insert(T item) {
        if (size + 1 == heap.length)
            resize((size + 1) * 2);

        heap[++size] = item;
        swim(size);
    }

    // TODO: support minHeap
    private void swim(int index) {
        while (index > 1 && heap[index].compareTo(heap[index / 2]) > 0) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    private void resize(int n) {
        if (n < 1 || n < size)
            throw new IllegalArgumentException();

        T[] cpy = (T[]) new Comparable[n];
        for (int i = 0; i <= size; ++i)
            cpy[i] = heap[i];

        heap = cpy;
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public String toString() {
        return Arrays.toString(heap);
    }
}
