package structs.collections;

public interface List<T> {
    void add(T data);
    T remove(int index);
    int size();
    boolean isEmpty();
}
