import structs.collections.*;
import structs.graph.*;
import structs.unionfind.UnionFind;

public class Demo {
    public static void main(String[] args){
        //graphs();
        //unionFind();
        //testLinkedLists();
        //stacks();
        queues();
    }

    public static void queues() {
        Double[] doubleList = {1.0, 2.2, 3.3, 4.1, 5.3};
        Queue<Double> q1 = new Queue<>(doubleList);
        System.out.println(q1);

        for (Double s : q1)
            System.out.println("Iterator: " + s);

        System.out.println(q1.size());
        System.out.println(q1.isEmpty());
        System.out.println(q1.dequeue());
        System.out.println(q1.peek());
        System.out.println(q1);
        q1.enqueue(12.7);
        System.out.println(q1);
        q1.dequeue();
        q1.dequeue();
        q1.dequeue();
        q1.dequeue();
        q1.dequeue();
        System.out.println(q1);
        q1.enqueue(-0.9);
        System.out.println(q1);
        for (double i = 2; i < 65; i *= 2)
            q1.enqueue(i);
        q1.enqueue(null);
        System.out.println(q1);
        System.out.println(q1.size());
    }

    public static void stacks() {
        Integer[] intList = {1, 2, 3, 4, 5};
        Stack<Integer> s1 = new Stack<>(intList);
        System.out.println(s1);

        for (Integer s : s1)
            System.out.println("Iterator: " + s);

        System.out.println(s1.size());
        System.out.println(s1.isEmpty());
        System.out.println(s1.pop());
        System.out.println(s1.peek());
        System.out.println(s1);
        s1.push(12);
        System.out.println(s1);
        s1.pop();
        s1.pop();
        s1.pop();
        s1.pop();
        s1.pop();
        System.out.println(s1);
        s1.push(1);
        System.out.println(s1);
    }

    public static void testLinkedLists() {
        ListNode<String> n = new ListNode<>("Hello");
        System.out.println(n);

        String[] strList = {"Hello", "Java", "World", "My", "Name", "Is", "Mateo"};
        LinkedList<String> l1 = new LinkedList<>(strList);
        System.out.println(l1);

        for (String s : l1)
            System.out.println("Iterator: " + s);

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

    public static void unionFind() {
        System.out.println("UnionFind w/Quick-Find (eager)");
        UnionFind uf1 = new UnionFind(10, 'f');
        uf1.union(4, 3);
        uf1.union(3, 8);
        uf1.union(6, 5);
        uf1.union(9, 4);
        uf1.union(2, 1);
        uf1.print();
        System.out.println(uf1.connected(8, 9));
        uf1.union(5, 0);
        uf1.union(7, 2);
        uf1.union(6, 1);
        uf1.union(6, 1);
        uf1.print();
        System.out.println("Root of 2: " + uf1.find(2));

        System.out.println();

        System.out.println("UnionFind w/Quick-Union (lazy)");
        UnionFind uf2 = new UnionFind(10, 'u');
        uf2.union(4, 3);
        uf2.union(3, 8);
        uf2.union(6, 5);
        uf2.union(9, 4);
        uf2.union(2, 1);
        uf2.print();
        System.out.println("Root of 4: " + uf2.find(4));
        System.out.println(uf2.connected(8, 9));
        System.out.println(uf2.connected(5, 4));
        uf2.union(5, 0);
        uf2.union(7, 2);
        uf2.union(6, 1);
        uf2.union(7, 3);
        uf2.print();
        System.out.println(uf2.connected(3, 1));
        //test path compression
        uf2.print();
    }

    public static void graphs() {
        UndirectedGraph g1 = new UndirectedGraph("src/structs/graph/graph1.csv");
        g1.print('e');
        System.out.println();
        System.out.println(g1.addEdgeIfAcyclic(7, 5,7, 0));
        g1.print('m');
        g1.print('a');
        g1.print('e');
        System.out.println("g1 num nodes: " + g1.getNumNodes() + " num edges: " + g1.getNumEdges() + "\n");

        UndirectedGraph mst = g1.generateMinSpanTree();
        mst.print('m');
        mst.print('a');
        mst.print('e');
        System.out.println("mst num nodes: " + mst.getNumNodes() + " num edges: " + mst.getNumEdges() + "\n");

        System.out.println("g1 contains cycle: " + g1.containsCycle());
        System.out.println("mst contains cycle: " + mst.containsCycle());
    }
}