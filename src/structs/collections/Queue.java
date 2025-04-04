package structs.collections;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private LinkedList<T> queue;

    public Queue(T[] data) {
        queue = new LinkedList<>();

        for (T datum : data) queue.add(datum);
    }

    public void enqueue(T data) {
        queue.append(data);
    }

    public T dequeue() {
        return queue.remove(0);
    }

    public T peek() {
        return queue.getHead().data;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public String toString() {
        return queue.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }
}
