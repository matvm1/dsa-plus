package structs.collections;

public class Main {
    public static void main(String[] args) {
        ListNode<String> n = new ListNode<>("Hello");
        System.out.println(n);

        String[] strList = {"Hello", "Java", "World", "My", "Name", "Is", "Mateo"};
        LinkedList<String> l1 = new LinkedList<>(strList);
        System.out.println(l1);
        System.out.println(l1.getHead());
        System.out.println(l1.getTail());
        System.out.println(l1.size());
        System.out.println(l1.isEmpty());
        l1.remove(2);
        System.out.println(l1);
        l1.remove(0);
        System.out.println(l1);
        l1.remove(l1.size() - 1);
        System.out.println(l1);
    }
}
