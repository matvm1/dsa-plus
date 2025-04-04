package structs.collections;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    LinkedList<T> stack;

    public Stack(T[] data) {
        stack = new LinkedList<>();

        for (int i = data.length - 1; i >= 0; i--)
            stack.add(data[i]);
    }

    public void push(T data) {
        stack.prepend(data);
    }

    public T pop() {
        return stack.remove(0);
    }

    public T peek() {
        return stack.getHead().data;
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }

    public String toString() {
        return stack.toString();
    }
}
