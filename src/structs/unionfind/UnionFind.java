package structs.unionfind;

import java.util.Arrays;

public class UnionFind {
    private int[] components;

    // mode:
        // "e" - eager, pre-compute components structure
    public UnionFind(int numNodes, char mode) {
        if(mode == 'e') {
            components = new int[numNodes];
            for (int i = 0; i < numNodes; ++i)
                components[i] = i;
        }
    }

    public void union(int p, int q) {
        int mergedComponent = components[p];
        for (int i = 0; i < components.length; i++)
        {
            if (components[i] == mergedComponent)
                components[i] = components[q];
        }
    }

    public boolean connected(int p, int q) {
        return components[p] == components[q];
    }

    public void print() {
        System.out.println(Arrays.toString(components));
    }
}
