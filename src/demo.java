import structs.graph.*;

public class demo{
    public static void main(String[] args){
        UndirectedGraph g1 = new UndirectedGraph("src/structs/graph/graph1.csv");
        g1.print('e');
        System.out.println();
        g1.sortEdgeList();
        g1.print('e');
    }
}