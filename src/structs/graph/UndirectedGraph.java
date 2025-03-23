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
        return containsCycle(head, head, new boolean[numNodes], head);
    }

    // DFS with matrix
    // O(V^2) - matrix representation -> Iterates over V nodes for each vertex. Worst case: graph is entirely connected
    private boolean containsCycle(int origin, int node, boolean[] visited, int prevNode){
        visited[node] = true;
        // Optimized solutions use a disjoint-set (union-find) to reduce space & time complexity
        for(int i = 0; i < numNodes; i++){
            if(adjMatrix[node][i] != 0) {
                if(!visited[i]) {
                    if (containsCycle(origin, i, visited, node))
                        return true;
                }
                else if(i != prevNode) {
                    return true;
                }
            }
        }

        return false;
    }
}