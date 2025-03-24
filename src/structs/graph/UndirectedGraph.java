package structs.graph;

public class UndirectedGraph extends Graph{
    public UndirectedGraph(String pathToCSV)
    {
        super(pathToCSV);
    }

    public UndirectedGraph(int numNodes){
        super(numNodes);
    }

    public UndirectedGraph generateMinSpanTree() {
        return generateMinSpanTreeKruskal();
    }

    public UndirectedGraph generateMinSpanTreeKruskal(){
        this.sortEdgeList();

        UndirectedGraph tree = new UndirectedGraph(getNumNodes());
        tree.nodeSet = nodeSet;

        for(int[] edge : edgeList){
            tree.addEdgeIfAcyclic(edge[edgeListHeadIndex], edge[edgeListTaiIndex], edge[edgeListWeightIndex], edge[edgeListBidirectionalIndex]);
            System.out.println(tree.adjMap + " | " + tree.nodeSet);
        }
        return tree;
    }

    // Adds an edge to this graph if there are no cycles once added to the graph
    // Returns true if edge was added, false otherwise
    // May resize adjMatrix if needed. If edge is not added, adjMap will remain resized, but numNodes will reflect the # of nodes accordingly
    public boolean addEdgeIfAcyclic(int head, int tail, int weight, int bidirectional){
        if(!nodeSet.contains(head) || !nodeSet.contains(tail)){
            addEdge(head, tail, weight, bidirectional);
            return true;
        }

        addEdge(head, tail, weight, bidirectional);
        if(containsCycle(head, head, new boolean[getNumNodes()], head)){
            removeEdge(head, tail);

            adjMap.get(head).remove(tail);
            if(adjMap.get(head) == null || adjMap.get(head).isEmpty())
                adjMap.remove(head);

            return false;
        }

        return true;
    }

    // Detects cycles in this graph
    // Starts from the head node of the first edge in edgeList
        // containsCycle(int origin, int node, boolean[] visited, int prevNode) detects a cycle for a connected graph starting from any arbitrary node
        // as long as the graph is connected
    // Does not support forests i.e. must be connected
    public boolean containsCycle(){
        return containsCycle(edgeList.getFirst()[edgeListHeadIndex], edgeList.getFirst()[edgeListHeadIndex], new boolean[nodeSet.size()], edgeList.getFirst()[edgeListHeadIndex]);
    }

    // Checks to see if this graph contains a cycle. Begins checking from node node
    // DFS with matrix
    // O(V^2) - matrix representation -> Iterates over V nodes for each vertex. Worst case: graph is entirely connected
    public boolean containsCycle(int origin, int node, boolean[] visited, int prevNode){
        visited[node] = true;
        // Optimized solutions use a disjoint-set (union-find) to reduce space & time complexity
        for(int i = 0; i < getNumNodes(); i++){
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