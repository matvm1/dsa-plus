package structs.graph;

public class UndirectedGraph extends Graph{
    public UndirectedGraph(String pathToCSV)
    {
        super(pathToCSV);
    }

    public Graph generateMinSpanTree(char mode) {
        if(mode == 'k')
            return generateMinSpanTreeKruskal();
        //if(mode == 'p')
        //    return generateMinSpanTreePrim();
        return null;
    }

    private Graph generateMinSpanTreeKruskal(){
        return null;
    }

    public boolean addEdgeIfAcyclic(int head, int tail, int weight, int bidirectional){
        this.addEdge(head, tail, weight, bidirectional);
        return containsCycle(head, head, new boolean[numNodes], 0);
    }

    // O(V)
    private boolean containsCycle(int origin, int node, boolean[] visited, int edgeCount){
        visited[node] = true;
        // Optimized solutions use a disjoint-set (union-find) to reduce space complexity
        for(int i = 0; i < numNodes; i++){
            if(adjMatrix[node][i] != 0) {
                System.out.println("origin: " + origin + " head: " + node + " tail: " + i);
                if(!visited[i]) {
                    if (containsCycle(origin, i, visited, edgeCount + 1))
                        return true;
                }
                else if(i == origin && edgeCount > 0)
                    return true;
            }
        }

        return false;
    }
}