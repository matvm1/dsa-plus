package structs.unionfind;

import java.util.Arrays;

public class UnionFind {
    public int[] components;
    private char mode;

    // mode:
        // 'f' - quick-find, eager (pre-compute components structure w/root during
        //                          union)
        // 'u' - quick-union, lazy (components trees are flattened only when a tree
        //                          becomes too large)
    public UnionFind(int numNodes, char mode) {
        if(mode == 'f' || mode == 'u')
            this.mode = mode;
        else
            throw new RuntimeException("Mode '" + mode + "' is invalid for " +
                    "UnionFind");

        components = new int[numNodes];
        for (int i = 0; i < numNodes; ++i)
            components[i] = i;
    }

    // Quick-Find: O(n)
        // O(n^2) to process n union operations
    public void union(int p, int q) {
        if (mode == 'f'){
            if(components[p] == components[q])
                return;

            int mergedComponent = components[p];
            for (int i = 0; i < components.length; i++)
            {
                if (components[i] == mergedComponent)
                    components[i] = components[q];
            }
        }
    }

    // Quick-Find: O(1)
    public boolean connected(int p, int q) {
        if(mode == 'f')
            return components[p] == components[q];

        // TODO
        else return false;
    }

    public int root(int p) {
        if (mode == 'f')
            return components[p];

        if(mode == 'u') {
            while (components[p] != p)
                p = components[p];

            return p;
        }

        throw new RuntimeException("UnionFind mode not set for object " + System.identityHashCode(this));
    }

    public void print() {
        System.out.println(Arrays.toString(components));
    }
}
