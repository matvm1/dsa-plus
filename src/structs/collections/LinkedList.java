package structs.collections;

import java.util.Arrays;

public class LinkedList<T> implements List<T> {
    private int size;
    private ListNode<T> head;
    private ListNode<T> tail;

    public LinkedList(T[] arr) {
        for (T data: arr) {
            if (!add(data))
                throw new IllegalStateException("Failed to add the following data: " + data);
        }
    }

    @Override
    public boolean add(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        size++;

        if (head == null) {
            head = newNode;
            head.next = tail;
            tail = head;
            return true;
        }

        assert tail != null;
        tail.next = newNode;
        tail = newNode;

        return true;
    }

    @Override
    public boolean remove(T data) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        ListNode<T> tmp = head;
        while (tmp.next != null) {
            sb.append(tmp.data.toString()).append(", ");
            tmp = tmp.next;
        }
        sb.append(tmp.data.toString()).append("]");

        return sb.toString();
    }

    public ListNode<T> getHead() {
        return head;
    }

    public ListNode<T> getTail() {
        return tail;
    }
}
