package structs.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Graph{
    private int[][] adjMatrix;

    public static void main(String[] args){
        Graph g1 = new Graph("graph1.csv");
        g1.print();
    }

    public Graph(int[][] adjMatrix){
        this.adjMatrix = adjMatrix;
    }

    public Graph(String pathToCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToCSV))
        ){
            int headerLen = 0;
            int csvFields = 1;
            int c = -1;
            while((c = br.read()) != '\n') {
                if (c == ',')
                    csvFields++;
                headerLen++;
            }
            c = -1;
            br.mark(100000);

            HashSet<Integer> nodes = new HashSet<Integer>();
            int numEdges = 0;
            int numNodes = 0;
            while((c = br.read()) != -1){
                nodes.add(c - '0');
                br.skip(1);
                nodes.add(br.read() - '0');
                while(br.read() != '\n');
                numEdges++;
            }
            numNodes = nodes.size();

            adjMatrix = new int[numNodes][numNodes];

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
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void print(){
        int nodes = adjMatrix.length;

        for(int i = 0; i < nodes; i++)
        {
            for(int j = 0; j < nodes; j++)
            {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
