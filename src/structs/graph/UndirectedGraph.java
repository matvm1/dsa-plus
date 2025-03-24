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

    // Adds an edge to this graph if there are no cycles once added to the graph
    // Returns true if edge was added, false otherwise
    // May resize adjMatrix if needed. If edge is not added, adjMap will remain resized, but numNodes will reflect the # of nodes accordingly
    public boolean addEdgeIfAcyclic(int head, int tail, int weight, int bidirectional){
        this.addEdge(head, tail, weight, bidirectional);
        if(containsCycle(head, head, new boolean[numNodes], head)){
            this.removeEdge(head, tail);

            adjMap.get(head).remove(tail);
            if(adjMap.get(head) == null || adjMap.get(head).isEmpty()) {
                System.out.println("fail");
                numNodes--;
                adjMap.remove(head);
            }

            return false;
        }

        return true;
    }

    // DFS with matrix
    // O(V^2) - matrix representation -> Iterates over V nodes for each vertex. Worst case: graph is entirely connected
    private boolean containsCycle(int origin, int node, boolean[] visited, int prevNode){
        visited[node] = true;
        // Optimized solutions use a disjoint-set (union-find) to reduce space & time complexity
        for(int i = 0; i < adjMatrix.length; i++){
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