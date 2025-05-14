import structs.array.Array;
import structs.collections.*;
import structs.graph.*;
import structs.unionfind.UnionFind;

import java.util.Arrays;
import java.util.Random;

public class Demo {
    public static void main(String[] args){
        //graphs();
        //unionFind();
        //testLinkedLists();
        //stacks();
        //queues();
        //binaryHeaps();
        //arrays();
        BSTMaps();
    }

    public static void binaryHeaps() {
        BinaryHeap<Integer> bh1 = new BinaryHeap<>(BinaryHeap.HeapOrder.MAX);
        Integer[] testMaxHeap = {8, 9, 10, 4, 3, 7, 8, 5, 3, 9, 1, 0, 6, 2, -1};
        for (Integer i : testMaxHeap)
            bh1.insert(i);
        System.out.println(bh1);
        System.out.println(bh1.extract());
        System.out.println(bh1);

        BinaryHeap<Double> bh2 = new BinaryHeap<>(BinaryHeap.HeapOrder.MAX);
        Random rand = new Random();
        double min = -50000.0;
        double max = 50000.0;
        for (int i = 0; i < 100; ++i)
            bh2.insert(Math.round(min + (max - min) * rand.nextDouble() * 10.0) /
            10.0);
        while (!bh2.isEmpty())
            bh2.extract();
        for (int i = 0; i < 100; ++i)
            bh2.insert(Math.round(min + (max - min) * rand.nextDouble() * 10.0) /
                    10.0);

        BinaryHeap<Integer> bh3 = new BinaryHeap<>(BinaryHeap.HeapOrder.MIN);
        Integer[] testMinHeap = {8, 9, 10, 4, 3, 7, 8, 5, 3, 9, 1, 0, 6, 2, -1};
        for (Integer i : testMinHeap)
            bh3.insert(i);
        System.out.println(bh3);
        System.out.println(bh3.extract());
        System.out.println(bh3);
        System.out.println(bh3.size());

        BinaryHeap<Double> bh4 = new BinaryHeap<>(BinaryHeap.HeapOrder.MIN);
        for (int i = 0; i < 1000; ++i)
            bh4.insert(Math.round(min + (max - min) * rand.nextDouble() * 10.0) /
                    10.0);
        System.out.println(bh4.size());
        while (!bh4.isEmpty())
            bh4.extract();
        System.out.println(bh4.size());
        for (int i = 0; i < 1000; ++i)
            bh4.insert(Math.round(min + (max - min) * rand.nextDouble() * 10.0) /
                    10.0);
        System.out.println(bh4.peek());
        System.out.println(bh4.size());

        String str = "SORTEXAMPLE";
        Character[] testBinHeapConstruction = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            testBinHeapConstruction[i] = str.charAt(i); // Autoboxing from char to Character
        }
        BinaryHeap<Character> bh5 = new BinaryHeap<>(testBinHeapConstruction,
                BinaryHeap.HeapOrder.MAX);
        System.out.println(bh5);

        int test6Size = 100000;
        Double[] testBinHeapConstruction2 = new Double[test6Size];
        for (int i = 0; i < test6Size; ++i)
            testBinHeapConstruction2[i] =
                    Math.round(min + (max - min) * rand.nextDouble() * 10.0) /
                    10.0;
        BinaryHeap<Double> bh6 = new BinaryHeap<>(testBinHeapConstruction2,
                BinaryHeap.HeapOrder.MIN);
        System.out.println(bh6.size());
        while(!bh6.isEmpty())
            bh6.extract();
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

    public static void arrays() {
        Integer[] nums = {12, 4, 5, 6, 12, 1, 3, 9, 56, 0};
        System.out.println(Arrays.toString(nums));
        Array.sort(nums, Array::mergeSort);
        System.out.println(Arrays.toString(nums));

        String[] names = {"Charlie", "Abbie", "Ross", "Xander", "Zach", "Aaron", "Ross"};
        System.out.println(Arrays.toString(names));
        Array.sort(names, Array::mergeSort);
        System.out.println(Arrays.toString(names));

        Integer[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(nums2));
        Array.shuffle(nums2);
        System.out.println(Arrays.toString(nums2));

        Integer[] nums3 = {5, 3, 1, 5, 7, 1, 5, 9};
        System.out.println(Arrays.toString(nums3));

        Integer[] nums4 = {2, 4, 21, 1, 9, 0, -1, 9, 100, 101};

        for (int i = 1; i <= nums4.length; ++i)
            System.out.print(Array.getKthSmallest(nums4, i) + " ");
        System.out.println();
        for (int i = 1; i <= nums4.length; ++i)
            System.out.print(Array.getKthLargest(nums4, i) + " ");

        System.out.println("\n" + Arrays.toString(nums4));
        Array.sort(nums4, Array::quickSort);
        System.out.println(Arrays.toString(nums4));

        Random rand = new Random();
        int len = rand.nextInt(50000);
        Integer[] numsRand = new Integer[len];
        for (int i = 0; i < len; ++i)
            numsRand[i] = rand.nextInt();
        //System.out.println(Arrays.toString(numsRand));
        Array.sort(numsRand, Array::quickSort);
        //System.out.println(Arrays.toString(numsRand));

        Integer[] heapSortTest = {13, 33, 21, 14, 2, 28, 0, 38, 28, 26};
        System.out.println(Arrays.toString(heapSortTest));
        Array.sort(heapSortTest, Array::heapSort);
        System.out.println(Arrays.toString(heapSortTest));

        Integer[] numsRand2 = new Integer[100000000];
        for (int i = 0; i < numsRand2.length; ++i)
            numsRand2[i] = rand.nextInt(40);
        //System.out.println(Arrays.toString(numsRand2));
        Array.sort(numsRand2, Array::heapSort);
        //System.out.println(Arrays.toString(numsRand2));
    }

    public static void BSTMaps() {
        BSTMap<Integer, Integer> b1 = new BSTMap<>();
        System.out.println(b1);

        BSTMap<Integer, String> nbaRankings = new BSTMap<>();
        nbaRankings.insert(20, "Dallas Mavericks");
        nbaRankings.insert(1, "Oklahoma City Thunder");
        nbaRankings.insert(28, "New Orleans Pelicans");
        nbaRankings.insert(6, "Denver Nuggets");
        nbaRankings.insert(9, "New York Knicks");
        nbaRankings.insert(13, "Memphis Grizzlies");
        nbaRankings.insert(5, "Los Angeles Lakers");
        nbaRankings.insert(27, "Charlotte Hornets");
        nbaRankings.insert(17, "Sacramento Kings");
        nbaRankings.insert(11, "Milwaukee Bucks");
        nbaRankings.insert(10, "Indiana Pacers");
        nbaRankings.insert(25, "Brooklyn Nets");
        nbaRankings.insert(8, "Minnesota Timberwolves");
        nbaRankings.insert(2, "Cleveland Cavaliers");
        nbaRankings.insert(16, "Atlanta Hawks");
        nbaRankings.insert(24, "Toronto Raptors");
        nbaRankings.insert(3, "Boston Celtics");
        nbaRankings.insert(21, "Phoenix Suns");
        nbaRankings.insert(14, "Detroit Pistons");
        nbaRankings.insert(30, "Utah Jazz");
        nbaRankings.insert(7, "Los Angeles Clippers");
        nbaRankings.insert(4, "Houston Rockets");
        nbaRankings.insert(12, "Golden State Warriors");
        nbaRankings.insert(19, "Miami Heat");
        nbaRankings.insert(22, "Portland Trail Blazers");
        nbaRankings.insert(15, "Orlando Magic");
        nbaRankings.insert(18, "Chicago Bulls");
        nbaRankings.insert(23, "San Antonio Spurs");
        nbaRankings.insert(26, "Philadelphia 76ers");
        nbaRankings.insert(29, "Washington Wizards");
        nbaRankings.insert(29, "Some Other NBA Team");
        nbaRankings.insert(29, "Washington Wizards");
        nbaRankings.insert(20, "Dallas Cowboys");
        nbaRankings.insert(20, "Dallas Mavericks");
        nbaRankings.insert(15, "Seattle Supersonics");
        nbaRankings.insert(15, "Orlando Magic");
        System.out.println(nbaRankings);
        System.out.println(nbaRankings.size());
        System.out.println(nbaRankings.select(1));
        System.out.println(nbaRankings.select(14));
        System.out.println(nbaRankings.select(29));
        System.out.println(nbaRankings.select(12));
    }
}