package structs.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class Graph{
    private int[][] adjMatrix;
    private Map<Integer, Map<Integer, Integer>> adjMap;
    private int maxWeightCharLen = 0;

    public static void main(String[] args){
        Graph g1 = new Graph("graph1.csv");
        g1.print();
    }

    // Create a Graph from CSV located at path pathToCSV
    // The CSV file should contain 4 columns: head, tail, weight, bidirectional
    // Adjacency matrices contain weights at [head][tail] (regardless of directionality), negative weights at [tail][head] if not a bidirectional edge
    // Adjacency maps map heads to a map of their neighbors and respective weights. Bidirectionality is represented by negative weighting
    public Graph(String pathToCSV){
        try (BufferedReader br = new BufferedReader(new FileReader(pathToCSV))
        ){
            while(br.read() != '\n');
            br.mark(100000);

            HashSet<Integer> nodes = new HashSet<Integer>();
            int numEdges = 0;
            int numNodes = 0;
            int c = -1;
            while((c = br.read()) != -1){
                nodes.add(c - '0');
                br.skip(1);
                nodes.add(br.read() - '0');
                while(br.read() != '\n');
                numEdges++;
            }
            numNodes = nodes.size();

            adjMatrix = new int[numNodes][numNodes];
            adjMap = new HashMap<>();

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

                adjMatrix[head][tail] = weight;
                adjMatrix[tail][head] = weight * (bidirectional == 1 ? 1 : -1);

                if(!adjMap.containsKey(head))
                    adjMap.put(head, new HashMap<>());
                adjMap.get(head).put(tail, weight * (bidirectional == 1 ? -1 : 1));

                maxWeightCharLen = Math.max(maxWeightCharLen, countDigits(adjMatrix[tail][head]));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Pretty print a graph in the form of an adjacency matrix
    public void print(){
        int nodes = adjMatrix.length;
        for (int[] matrix : adjMatrix) {
            for (int weight: matrix) {
                int currWeightCharLen = countDigits(weight);
                for (int k = 0; k < maxWeightCharLen - currWeightCharLen; k++)
                    System.out.print(" ");
                System.out.print(weight + " ");
            }
            System.out.println();
        }
    }

    // Calculate the number of digits in a number
    public static int countDigits(int num){
        return (num == 0) ? 1 : (num < 0) ? (int) (Math.log10(num * -1) + 2) : (int) (Math.log10(num) + 1);
    }
}
