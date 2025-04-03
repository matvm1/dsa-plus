package structs.collections;

public class ListNode<T> {
    T data;
    ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
