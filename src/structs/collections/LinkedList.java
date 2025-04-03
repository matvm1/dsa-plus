package structs.collections;

public class LinkedList<T> implements List<T> {
    private int size;

    public LinkedList(T[] arr) {
        size = arr.length;
        // TODO
    }

    @Override
    public boolean add(T data) {
        return false;
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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
