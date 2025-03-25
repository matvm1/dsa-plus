package structs.unionfind;

import java.util.Arrays;

public class UnionFind {
    private int[] components;
    private char mode;

    // mode:
        // 'f' - quick-find, eager (pre-compute components structure w/find during
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

    // Quick-Find: Theta(n)
        // O(n^2) to process n union commands
    // Quick-Union:
        // O(n)
            // O(n^2) in worst case (no path compression, linear tree generated w/ all
                // elements connected) to process n union commands
        // Omega(log n)
            // O(n log n) in the best case (unions result in a complete binary tree)
                // to process n union commands
        // TODO: path compression
    public void union(int p, int q) {
        if (mode == 'f') {
            if(components[p] == components[q])
                return;

            int mergedComponent = components[p];
            for (int i = 0; i < components.length; i++)
            {
                if (components[i] == mergedComponent)
                    components[i] = components[q];
            }

            return;
        }

        if (mode == 'u') {
            while (components[p] != p && components[p] != q)
                p = components[p];

            // check if they aren't connected already
            if(components[p] != q)
                components[p] = find(q);

            return;
        }

        throw new RuntimeException("UnionFind mode not set for object " + System.identityHashCode(this));
    }

    // Quick-Find: O(1)
    // Quick-Union: O(n)
    public boolean connected(int p, int q) {
        if (mode == 'f')
            return components[p] == components[q];

        if (mode == 'u') {
            while (components[p] != p && components[p] != q)
                p = components[p];

            // check if they are connected before the root
            if(components[p] == q)
                return true;

            return p == find(q);
        }

        throw new RuntimeException("UnionFind mode not set for object " + System.identityHashCode(this));
    }

    // Returns the root of p
    // O(n) if p is the leaf node in a tree where all components are connected linearly
        // TODO: Path compression for faster search
    public int find(int p) {
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
