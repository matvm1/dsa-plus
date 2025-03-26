package structs.unionfind;

import java.util.Arrays;

public class UnionFind {
    private int[] components;
    private int[] sz;
    private char mode;

    // mode:
        // 'f' - quick-find, eager (pre-compute components structure w/find during
        //                          union)
        // 'u' - quick-union, lazy (components trees are flattened only when a tree
        //                          becomes too large)
    public UnionFind(int numNodes, char mode) {
        if (mode == 'f') {
            this.mode = mode;
            components = new int[numNodes];
            for (int i = 0; i < numNodes; ++i)
                components[i] = i;
            return;
        }

        if (mode == 'u') {
            this.mode = mode;
            components = new int[numNodes];
            sz = new int[numNodes];
            for (int i = 0; i < numNodes; ++i) {
                components[i] = i;
                sz[i] = 1;
            }

            return;
        }

        throw new IllegalArgumentException("Mode '" + mode + "' is invalid for " +
                "UnionFind");
    }

    // Quick-Find: Theta(n)
        // O(n^2) to process n union commands
    // Quick-Union:
        // O(n)
            // O(n^2) in worst case (no path compression, linear tree generated w/ all
                // elements connected) to process n union commands
        // Omega(1) when doing union of two root nodes
            // O(n) for n elements if always doing the union of 2 nodes
    // Weighted Quick-Union using sz[]:
        //  O(log n)
            // O(n log n) for n union commands
        // Omega(1) when doing union of two nodes
            // O(n) if always doing union of two nodes
    // Weighted + Path Compression Quick-Union: O(1)
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
            int proot = p;
            while (components[proot] != proot && components[proot] != q)
                proot = components[proot];

            // check if they aren't connected already
            if(components[proot] != q) {
                int qroot = find(q);
                compressPath(q, qroot);
                if (sz[p] >= sz[qroot]) {
                    components[qroot] = proot;
                    sz[proot] += sz[qroot];
                }
                else {
                    compressPath(p, proot);
                    components[proot] = qroot;
                    sz[qroot] += sz[proot];
                }
                return;
            }

            return;
        }

        throw new RuntimeException("UnionFind mode not set for object " + System.identityHashCode(this));
    }

    // Quick-Find: O(1)
    // Quick-Union: O(n)
    // Quick-Union w/ Weights and Path Compression: nearly O(1)
    public boolean connected(int p, int q) {
        if (mode == 'f')
            return components[p] == components[q];

        if (mode == 'u') {
            int proot = p;
            while (components[proot] != proot && components[proot] != q)
                proot = components[proot];

            // check if they are connected before the root
            if(components[proot] == q)
                return true;

            compressPath(p, proot);

            return p == find(q);
        }

        throw new RuntimeException("UnionFind mode not set for object " + System.identityHashCode(this));
    }

    // Returns the root of p
    // O(n) if p is the leaf node in a tree where all components are connected linearly
    public int find(int p) {
        if (mode == 'f')
            return components[p];

        if(mode == 'u') {
            int proot = p;
            while (components[proot] != proot)
                proot = components[proot];

            // two-pass path compression
            compressPath(p, proot);

            return proot;
        }

        throw new RuntimeException("UnionFind mode not set for object " + System.identityHashCode(this));
    }

    // Compresses the path up p
    // Does not resize pointers in sz[]
    private void compressPath(int node, int root) {
        while (components[node] != root) {
            int tmp = components[node];
            components[node] = root;
            node = tmp;
        }
    }

    public void print() {
        System.out.println(Arrays.toString(components));
    }
}
