package structs.collections;

import java.util.Arrays;

public class BinaryHeap<T extends Comparable<? super T>> {
    private T[] heap;
    private int size;
    public enum HeapOrder {MIN, MAX};
    private final HeapOrder order;

    public BinaryHeap(HeapOrder order) {
        heap = (T[]) new Comparable[2];
        size = 0;
        this.order = order;
    }

    public BinaryHeap(T[] items, HeapOrder order) {
        this.order = order;
        if (items[0] != null) {
            heap = (T[]) new Comparable[items.length + 1];
            for (int i = 1; i <= items.length; ++i)
                heap[i] = items[i - 1];
            size = items.length;
        }
        else {
            heap = items.clone();
            size = items.length - 1;
        }

        for (int i = size; i > 1; --i) {
            if (needsHeapify(i / 2, i))
                sink(i / 2);
        }
        assert(isHeapOrdered());
    }

    public int size() {return size;}

    public boolean isEmpty() {return size == 0;}

    public T peek() {
        return (isEmpty()) ? null : heap[1];
    }

    public void insert(T item) {
        if (size + 1 == heap.length)
            resize((size + 1) * 2);

        heap[++size] = item;
        swim(size);
        assert(isHeapOrdered());
    }

    // Dequeues top node
    public T extract() {
        T item = heap[1];
        heap[1] = heap[size--];
        heap[size + 1] = null;
        sink(1);

        final double minCapacity = 0.25;
        if ((double) size / heap.length <= minCapacity)
            resize(heap.length / 2);

        assert(isHeapOrdered());

        return item;
    }

    //public static <U extends Comparable<? super U>> BinaryHeap<U> heapify(U[] items,
    //
    //                                                                      HeapOrder o) {
    //}

    private void swim(int index) {
        while (index > 1 && needsHeapify(index / 2, index)) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    // Sinks node at index down
    private void sink(int index) {
        while (index * 2 <= size) {
            int promoted = promote(index);
            if (!needsHeapify(index, promoted))
                break;
            swap(index, promoted);
            index = promoted;
        }
    }

    // returns the index of the child to promote when intending to promote
    private int promote(int index) {
        if (index * 2 + 1 > size)
            return index * 2;

        if (order == HeapOrder.MAX)
            return (heap[index * 2].compareTo(heap[index * 2 + 1])) > 0 ?
                            index * 2 : index * 2 + 1;

        return (heap[index * 2].compareTo(heap[index * 2 + 1])) < 0 ?
                index * 2 : index * 2 + 1;
    }

    private boolean needsHeapify(int parent, int child) {
        return (order == HeapOrder.MAX && heap[parent].compareTo(heap[child]) < 0)
                || (order == HeapOrder.MIN && heap[parent].compareTo(heap[child]) > 0);
    }

    private void resize(int n) {
        if (n < 1 || n < size)
            throw new IllegalArgumentException();

        T[] cpy = (T[]) new Comparable[n];
        for (int i = 0; i <= size; ++i)
            cpy[i] = heap[i];

        heap = cpy;
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private boolean isHeapOrdered() {
            for (int i = 2; i <= size; ++i)
                if (needsHeapify(i / 2, i)) {
                    System.err.println(this);
                    System.err.println("Heap ordered violated. Parent: " + heap[i / 2] + " at " + i / 2 + "; Child: " + heap[i] + " at " + i + ";");
                    return false;
                }
        return true;
    }

    // pretty toString() provided by Claude Sonnet
    // TODO: adjust level widths based on width of values
    // TODO: fix right-side connectors
    public String toString() {
        if (heap == null || size <= 0 || heap.length <= 1) {
            return "Empty heap";
        }

        StringBuilder result = new StringBuilder();

        // Calculate the height of the heap
        int height = (int) Math.ceil(Math.log(size + 1) / Math.log(2));

        // Calculate the maximum width needed for printing
        int maxWidth = (int) Math.pow(2, height) - 1;

        // Build the heap level by level
        int level = 0;
        int nodesInCurrentLevel = 1;
        int nodesToPrint = Math.min(nodesInCurrentLevel, size);
        int index = 1; // Start from index 1 since heap[0] is null

        while (index <= size) {
            // Calculate spacing between nodes at this level
            int nodeSeparation = maxWidth / (int) Math.pow(2, level);

            // Add leading spaces for first node at this level
            int leadingSpaces = (nodeSeparation - 1) / 2;
            appendSpaces(result, leadingSpaces);

            // Add nodes at this level
            for (int i = 0; i < nodesToPrint; i++) {
                // Convert the object to string with null check
                String value = heap[index] == null ? "null" : heap[index].toString();
                result.append(value);

                // Add spaces between nodes at this level
                if (i < nodesToPrint - 1) {
                    appendSpaces(result, nodeSeparation);
                }

                index++;
                // Stop if we've printed all nodes
                if (index > size) {
                    break;
                }
            }
            result.append("\n");

            // Add connecting lines if not the last level
            if (level < height - 1 && index <= size) {
                int nextLevelNodes = Math.min((int) Math.pow(2, level + 1), size - ((int) Math.pow(2, level) - 1));
                appendConnectors(result, level, nodesToPrint, nodeSeparation, maxWidth, nextLevelNodes);
            }

            // Update for the next level
            level++;
            nodesInCurrentLevel *= 2;
            nodesToPrint = Math.min(nodesInCurrentLevel, size - (nodesInCurrentLevel - 1));
        }

        result.append(Arrays.toString(heap));

        return result.toString();
    }

    /**
     * Helper method to append spaces to the StringBuilder
     */
    private void appendSpaces(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
    }

    /**
     * Appends connector lines between levels to show parent-child relationships
     */
    private void appendConnectors(StringBuilder sb, int level, int levelNodes, int nodeSeparation, int maxWidth, int nodesNextLevel) {
        StringBuilder connectorLine = new StringBuilder();

        // Leading spaces for the first node
        int leadingSpaces = (nodeSeparation - 1) / 2;
        appendSpaces(connectorLine, leadingSpaces - 1);

        for (int i = 0; i < levelNodes; i++) {
            int leftChildIndex = 2 * (i + 1);
            int rightChildIndex = 2 * (i + 1) + 1;

            // Left connector
            if (leftChildIndex <= nodesNextLevel) {
                connectorLine.append("/");
            } else {
                connectorLine.append(" ");
            }

            // Spaces between left and right connectors
            int middleSpace = nodeSeparation - 2; // -2 for the / and \ characters
            appendSpaces(connectorLine, middleSpace);

            // Right connector - explicitly use backslash
            if (rightChildIndex <= nodesNextLevel) {
                connectorLine.append("\\"); // Explicit backslash character
            } else {
                connectorLine.append(" ");
            }

            // Spaces between node groups
            if (i < levelNodes - 1) {
                appendSpaces(connectorLine, nodeSeparation - middleSpace - 2);
            }
        }

        sb.append(connectorLine).append("\n");
    }
}
