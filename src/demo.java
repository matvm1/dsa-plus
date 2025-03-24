import structs.graph.*;

public class demo{
    public static void main(String[] args){
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