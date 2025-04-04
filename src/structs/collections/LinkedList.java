package structs.collections;

import java.util.Arrays;

public class LinkedList<T> implements List<T> {
    private int size;
    private ListNode<T> head;
    private ListNode<T> tail;

    public LinkedList() {
        size = 0;

        head = null;
        tail = null;
    }

    public LinkedList(T[] arr) {
        size = 0;

        for (T data: arr)
            add(data);
    }

    @Override
    public void add(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        size++;

        if (head == null) {
            head = newNode;
            head.next = tail;
            tail = head;
        }

        assert tail != null;
        tail.next = newNode;
        tail = newNode;
    }

    public void prepend(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void append(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        size++;

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    @Override
    public T remove(int position) {
        if (position >= size)
            throw new IllegalArgumentException("Position " + position + " exceeds size of LinkedList " + size);
        if (position < 0)
            throw new IllegalArgumentException("Illegal LinkedList position: " + position);

        if (position == 0) {
            if (size == 1)
                tail = head.next;
            T res = head.data;
            head = head.next;
            size--;
            return res;
        }

        ListNode<T> tmp = head;
        while (position > 1) {
            tmp = tmp.next;
            position--;
        }
        T res = tmp.next.data;
        tmp.next = tmp.next.next;

        size--;
        return res;
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
        if (head == null)
            return null;

        StringBuilder sb = new StringBuilder("[");

        ListNode<T> tmp = head;
        while (tmp.next != null) {
            if (tmp.data == null)
                sb.append("null").append(", ");
            else
                sb.append(tmp.data.toString()).append(", ");
            tmp = tmp.next;
        }
        if (tmp.data == null)
            sb.append("null");
        else
            sb.append(tmp.data.toString());
        sb.append("]");

        return sb.toString();
    }

    public ListNode<T> getHead() {
        return head;
    }

    public ListNode<T> getTail() {
        return tail;
    }
}
