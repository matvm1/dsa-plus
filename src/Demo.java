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

        BSTMap<Integer, String> nbaTeamsbyRank = new BSTMap<>();
        nbaTeamsbyRank.insert(20, "Dallas Mavericks");
        nbaTeamsbyRank.insert(1, "Oklahoma City Thunder");
        nbaTeamsbyRank.insert(28, "New Orleans Pelicans");
        nbaTeamsbyRank.insert(6, "Denver Nuggets");
        nbaTeamsbyRank.insert(9, "New York Knicks");
        nbaTeamsbyRank.insert(13, "Memphis Grizzlies");
        nbaTeamsbyRank.insert(5, "Los Angeles Lakers");
        nbaTeamsbyRank.insert(27, "Charlotte Hornets");
        nbaTeamsbyRank.insert(17, "Sacramento Kings");
        nbaTeamsbyRank.insert(11, "Milwaukee Bucks");
        nbaTeamsbyRank.insert(10, "Indiana Pacers");
        nbaTeamsbyRank.insert(25, "Brooklyn Nets");
        nbaTeamsbyRank.insert(8, "Minnesota Timberwolves");
        nbaTeamsbyRank.insert(2, "Cleveland Cavaliers");
        nbaTeamsbyRank.insert(16, "Atlanta Hawks");
        nbaTeamsbyRank.insert(24, "Toronto Raptors");
        nbaTeamsbyRank.insert(3, "Boston Celtics");
        nbaTeamsbyRank.insert(21, "Phoenix Suns");
        nbaTeamsbyRank.insert(14, "Detroit Pistons");
        nbaTeamsbyRank.insert(30, "Utah Jazz");
        nbaTeamsbyRank.insert(7, "Los Angeles Clippers");
        nbaTeamsbyRank.insert(4, "Houston Rockets");
        nbaTeamsbyRank.insert(12, "Golden State Warriors");
        nbaTeamsbyRank.insert(19, "Miami Heat");
        nbaTeamsbyRank.insert(22, "Portland Trail Blazers");
        nbaTeamsbyRank.insert(15, "Orlando Magic");
        nbaTeamsbyRank.insert(18, "Chicago Bulls");
        nbaTeamsbyRank.insert(23, "San Antonio Spurs");
        nbaTeamsbyRank.insert(26, "Philadelphia 76ers");
        nbaTeamsbyRank.insert(29, "Washington Wizards");
        nbaTeamsbyRank.insert(29, "Some Other NBA Team");
        nbaTeamsbyRank.insert(29, "Washington Wizards");
        nbaTeamsbyRank.insert(20, "Dallas Cowboys");
        nbaTeamsbyRank.insert(20, "Dallas Mavericks");
        nbaTeamsbyRank.insert(15, "Seattle Supersonics");
        nbaTeamsbyRank.insert(15, "Orlando Magic");
        System.out.println(nbaTeamsbyRank);
        System.out.println(nbaTeamsbyRank.size());
        System.out.println("Test select():");
        System.out.println(nbaTeamsbyRank.select(1));
        for (int i = 0; i < nbaTeamsbyRank.size(); ++i)
            System.out.println(nbaTeamsbyRank.select(i));

        System.out.println("\nTest rank(): ");
        System.out.println(nbaTeamsbyRank.rank(0));
        System.out.println(nbaTeamsbyRank.rank(31));
        for (int i = 1; i <= nbaTeamsbyRank.size(); ++i)
            System.out.println(nbaTeamsbyRank.rank(i));

        System.out.println("Test delete(): ");
        nbaTeamsbyRank.delete(8);
        nbaTeamsbyRank.delete(4);
        nbaTeamsbyRank.delete(2);
        nbaTeamsbyRank.delete(15);
        nbaTeamsbyRank.delete(16);
        nbaTeamsbyRank.delete(17);
        nbaTeamsbyRank.delete(9);
        nbaTeamsbyRank.delete(20);
        nbaTeamsbyRank.delete(9);
        nbaTeamsbyRank.delete(-1);
        nbaTeamsbyRank.delete(31);

        /*nbaTeamsbyRank.delete(29);
        nbaTeamsbyRank.delete(0);
        nbaTeamsbyRank.delete(29);
        nbaTeamsbyRank.delete(9);
        nbaTeamsbyRank.delete(29);
        nbaTeamsbyRank.delete(18);*/

        /*nbaTeamsbyRank.delete(27);
        nbaTeamsbyRank.delete(20);
        nbaTeamsbyRank.delete(7);
        nbaTeamsbyRank.delete(30);
        nbaTeamsbyRank.delete(29);
        nbaTeamsbyRank.delete(5);
        nbaTeamsbyRank.delete(8);
        nbaTeamsbyRank.delete(29);
        nbaTeamsbyRank.delete(14);
        nbaTeamsbyRank.delete(5);
        nbaTeamsbyRank.delete(1);
        nbaTeamsbyRank.delete(17);
        nbaTeamsbyRank.delete(2);
        nbaTeamsbyRank.delete(4);
        nbaTeamsbyRank.delete(9);
        nbaTeamsbyRank.delete(4);
        nbaTeamsbyRank.delete(30);
        nbaTeamsbyRank.delete(16);
        nbaTeamsbyRank.delete(6);*/

        /*for (int i = 1; i <= 20; ++i)
            nbaTeamsbyRank.delete(i);
        for (int i = 22; i <= 30; ++i)
            nbaTeamsbyRank.delete(i);
        nbaTeamsbyRank.delete(21);
        System.out.println(nbaTeamsbyRank);*/

        Random rand = new Random();
        int min = -1;
        int max = 31;
        for (int i = 1; i <= 100; ++i) {
            int randInt = rand.nextInt((max - min) + 1) + min;
            System.out.println("nbaTeamsbyRank.delete(" + randInt + ");");
            nbaTeamsbyRank.delete(randInt);
        }
        System.out.println(nbaTeamsbyRank);

        /*int numRandTrials = 1000;
        for (int i = 0; i < numRandTrials; ++i) {
            BSTMap<Double, String> randTest = new BSTMap<>();
            min = -50000;
            max = min * -1;
            for (int j = 0; j < 1000; ++j)
                randTest.insert(rand.nextDouble((max - min) + 1) + min, "test");
            for (int j = 0; j < 10000; ++j)
                randTest.delete(rand.nextDouble((max - min) + 1) + min);
        }*/

        max = 500;
        min = max * -1;
        BSTMap <Integer, Integer> testDeleteUntilEmpty = new BSTMap<>();
        for (int j = 0; j < 1000000; ++j) {
            int randInt = rand.nextInt((max - min) + 1) + min;
            //System.out.println("testDeleteUntilEmpty.insert(" + randInt + ", 0);");
            testDeleteUntilEmpty.insert(randInt, 0);
        }
        while (!testDeleteUntilEmpty.isEmpty()) {
            int randInt = rand.nextInt((max - min) + 1) + min;
            max = Math.max(max, randInt);
            min = Math.min(min, randInt);
            testDeleteUntilEmpty.delete(randInt);
        }
        /*testDeleteUntilEmpty.insert(-478, 0);
        testDeleteUntilEmpty.insert(-315, 0);
        testDeleteUntilEmpty.insert(-33, 0);
        testDeleteUntilEmpty.insert(-70, 0);
        testDeleteUntilEmpty.insert(365, 0);
        testDeleteUntilEmpty.insert(261, 0);
        testDeleteUntilEmpty.insert(-307, 0);
        testDeleteUntilEmpty.insert(-373, 0);
        testDeleteUntilEmpty.insert(51, 0);
        testDeleteUntilEmpty.insert(-288, 0);
        testDeleteUntilEmpty.insert(-194, 0);
        testDeleteUntilEmpty.insert(365, 0);*/
        System.out.println(testDeleteUntilEmpty);
    }
}