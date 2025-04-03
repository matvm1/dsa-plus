package structs.collections;

public interface List<T> {
    boolean add(T data);
    boolean remove(T data);
    boolean remove(int index);
    int size();
    boolean isEmpty();
}
