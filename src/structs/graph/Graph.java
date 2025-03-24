package structs.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Graph{
    protected int[][] adjMatrix;
    protected Map<Integer, Map<Integer, Integer>> adjMap;
    protected List<int[]> edgeList;
    protected Set<Integer> nodeSet;
    protected final int edgeListHeadIndex = 0;
    protected final int edgeListTaiIndex = 1;
    protected final int edgeListWeightIndex = 2;
    protected final int edgeListBidirectionalIndex = 3;
    protected int maxWeightCharLen = 0;
    protected int numEdges = 0;

    // Create a Graph from CSV located at path pathToCSV
    // The CSV file should contain 4 columns: head, tail, weight, bidirectional
    // Adjacency matrices contain weights at [head][tail] (regardless of directionality), negative weights at [tail][head] if not a bidirectional edge
    // Adjacency maps map heads to a map of their neighbors and respective weights. Bidirectionality is represented by negative weighting
    public Graph(String pathToCSV){
        try (BufferedReader br = new BufferedReader(new FileReader(pathToCSV))
        ){
            while(br.read() != '\n');
            br.mark(100000);

            nodeSet = new HashSet<Integer>();
            int c = -1;
            while((c = br.read()) != -1){
                nodeSet.add(c - '0');
                br.skip(1);
                nodeSet.add(br.read() - '0');
                while(br.read() != '\n');
            }

            adjMatrix = new int[nodeSet.size()][nodeSet.size()];
            adjMap = new HashMap<>();
            edgeList = new ArrayList<>(numEdges);

            br.reset();

            int head = -1;
            int tail = -1;
            int weight = -1;
            int bidirectional = -1;
            while((head = br.read()) != -1){
                head -= '0';
                br.skip(1);
                tail = br.read() - '0';
                br.skip(1);
                weight = br.read() - '0';
                br.skip(1);
                bidirectional = br.read() - '0';
                br.skip(1);

                this.addEdge(head, tail, weight, bidirectional);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Creates an empty Graph with space allocation enough for numNodes nodes in the adjMatrix
    public Graph(int numNodes){
        adjMatrix = new int[numNodes][numNodes];
        adjMap = new HashMap<>();
        edgeList = new ArrayList<>();
        nodeSet = new HashSet<>();
    }

    // Pretty print graph
    // Params:
    //      mode: 'm' - print adj matrix, 'a' - print adj list, 'e' - print edge list
    public void print(char mode){
        if(mode == 'm') {
            for (int[] matrix : adjMatrix) {
                for (int weight : matrix) {
                    int currWeightCharLen = countDigits(weight);
                    for (int k = 0; k < maxWeightCharLen - currWeightCharLen; k++)
                        System.out.print(" ");
                    System.out.print(weight + " ");
                }
                System.out.println();
            }
        }
        else if(mode == 'a')
            System.out.println(adjMap);
        else if(mode == 'e') {
            for(int[] edge: edgeList)
                System.out.println(Arrays.toString(edge));
        }
    }

    // Adjaceny matrix only handles a set of nodes s.t. the set can be represented by a contiguous array indexed from 0
    // i.e. no gaps in the set, and must begin from 0
    // TODO: Support gaps, independence between adjMatrix indexes and node values
    public void addEdge(int head, int tail, int weight, int bidirectional){
        if(!nodeSet.contains(head)) {
            nodeSet.add(head);
        }
        if(!nodeSet.contains(tail)){
            nodeSet.add(tail);
        }
        numEdges++;

        if(getNumNodes() > adjMatrix.length)
        {
            int[][] adjMatrixTemp = new int[getNumNodes()][getNumNodes()];
            for(int i = 0; i < adjMatrix.length; i++)
                for(int j = 0; j < adjMatrix.length; j++)
                    adjMatrixTemp[i][j] = adjMatrix[i][j];

            adjMatrix = adjMatrixTemp;
        }
        adjMatrix[head][tail] = weight;
        adjMatrix[tail][head] = weight * (bidirectional == 1 ? 1 : -1);

        if(!adjMap.containsKey(head)) {
            adjMap.put(head, new HashMap<>());
        }
        adjMap.get(head).put(tail, weight * (bidirectional == 1 ? -1 : 1));

        edgeList.add(new int[]{head, tail, weight, bidirectional});

        maxWeightCharLen = Math.max(maxWeightCharLen, countDigits(adjMatrix[tail][head]));
    }

    // Removes an edge from this graph
    // Does not remove nodes from adjMatrix or nodeSet
    public void removeEdge(int head, int tail){
        if(head >= adjMatrix.length || tail >= adjMatrix.length){
            System.out.println("Adjacency matrix length exceeded");
            return;
        }

        adjMatrix[head][tail] = 0;
        adjMatrix[tail][head] = 0;

        adjMap.get(head).remove(tail);

        edgeList.removeIf(e -> e[edgeListHeadIndex] == head && e[edgeListTaiIndex] == tail);

        numEdges--;
    }

    public void sortEdgeList(){
        edgeList.sort((a, b) -> Integer.compare(a[edgeListWeightIndex], b[edgeListWeightIndex]));
    }

    // Calculate the number of digits in a number
    public static int countDigits(int num){
        return (num == 0) ? 1 : (num < 0) ? (int) (Math.log10(num * -1) + 2) : (int) (Math.log10(num) + 1);
    }

    public int getNumNodes(){
        return nodeSet.size();
    }

    public int getNumEdges(){
        return numEdges;
    }
}
