package structs.collections;

import java.util.NoSuchElementException;

public class BSTMap<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    public BSTMap() {
        root = null;
    }

    public int size() {
        return size(root);
    }

    public void insert(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException();

        Node<Key, Value> prev = null;
        Node<Key, Value> curr = root;

        while (curr != null) {
            curr.size++;
            prev = curr;
            int cmp = key.compareTo(curr.key);
            if (cmp < 0)
                curr = curr.left;
            else if (cmp > 0)
                curr = curr.right;
            else {
                curr.value = value;
                break;
            }
        }

        if (curr == null) {
            if (prev != null) {
                if (key.compareTo(prev.key) < 0)
                    prev.left = new Node<>(key, value);
                else
                    prev.right = new Node<>(key, value);
            }
            else
                root = new Node<>(key, value);
        }
        // no insertion, undo size increments
        else {
            curr = root;
            while (curr.key != key) {
                curr.size--;
                int cmp = key.compareTo(curr.key);
                if (cmp < 0)
                    curr = curr.left;
                else if (cmp > 0)
                    curr = curr.right;
            }
            curr.size--;
        }

        assert(isBST());
    }

    public int rank(Key key) {
        Node<Key, Value> curr = root;
        int rank = size(curr) - size(curr.right);
        int cmp = key.compareTo(curr.key);
        while (cmp != 0) {
            if (cmp < 0) {
                curr = curr.left;
                if (curr == null)
                    return -1;
                rank -= size(curr.right) + 1;
            }
            else {
                curr = curr.right;
                if (curr == null)
                    return -1;
                rank += size(curr.left) + 1;
            }
            cmp = key.compareTo(curr.key);
        }
        return rank - 1;
    }

    public Value select(int k) {
        if (k < 0)
            throw new IllegalArgumentException();
        if (k > size())
            throw new NoSuchElementException();

        k += 1;
        Node<Key, Value> curr = root;
        int rank = size(curr) - size(curr.right);
        while (rank != k) {
            if (rank > k) {
                curr = curr.left;
                rank -= size(curr.right) + 1;
            }
            else {
                curr = curr.right;
                rank += size(curr.left) + 1;
            }
        }
        return curr.value;
    }

    private static <Key extends Comparable<Key>, Value> int size(Node<Key, Value> node) {
        return (node == null) ? 0 : node.size;
    }

    public String toString() {
        return toStringBuilder(root, new StringBuilder(), 0).toString();
    }

    private StringBuilder toStringBuilder(Node<Key, Value> node, StringBuilder sb,
                            int recursionLevel) {
        if (node == null)
            return sb;

        toStringBuilder(node.left, sb, recursionLevel + 1);
        sb.append("-".repeat(recursionLevel))
                .append("[").append(node.key).append("](")
                .append(node.size).append(")")
                .append(node.value).append("\n");
        toStringBuilder(node.right, sb, recursionLevel + 1);

        return sb;
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node<Key, Value> node, Key min, Key max) {
        if (node == null)
            return true;
        if ((min != null && node.key.compareTo(min) <= 0) ||
                (max != null && node.key.compareTo(max) >= 0))
            return false;
        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    private static class Node<Key extends Comparable<Key>, Value> {
        private Node<Key, Value> left;
        private Node<Key, Value> right;
        private final Key key;
        private Value value;
        private int size;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
        }

        public String toString() {
            return "[" + key + "]" + value;
        }
    }
}
