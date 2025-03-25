import structs.graph.*;
import structs.unionfind.UnionFind;

public class demo{
    public static void main(String[] args){
        //graphs();
        unionFind();
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
        System.out.println(uf2.connected(6, 0));
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