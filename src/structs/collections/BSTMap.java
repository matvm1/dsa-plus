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

    public boolean isEmpty() {
        return root == null;
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
            while (!key.equals(curr.key)) {
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

    // TODO: update sizes
    public void delete(Key key) {
        if (root == null)
            return;

        Node<Key, Value> parent = null;
        Node<Key, Value> searchNode = root;

        int cmp = key.compareTo(searchNode.key);
        // search for the node to delete
        while (cmp != 0) {
            searchNode.size--;
            parent = searchNode;
            if (cmp < 0)
                searchNode = searchNode.left;
            else
                searchNode = searchNode.right;
            if (searchNode == null)
                break;
            cmp = key.compareTo(searchNode.key);
        }

        // readjust sizes if search node was not found
        if (searchNode == null) {
            searchNode = root;
            while (searchNode != null) {
                searchNode.size++;
                cmp = key.compareTo(searchNode.key);
                if (cmp < 0)
                    searchNode = searchNode.left;
                else
                    searchNode = searchNode.right;
            }
            return;
        }

        // check if the node being deleted is the root
        if (parent == null) {
            parent = new Node<>(null, null);
            parent.left = root;
        }

        // Hibbard deletion
        boolean isDeletingFromParentLeft =
                parent.left != null && key.equals(parent.left.key);
        if (searchNode.left == null && searchNode.right == null)
            if (isDeletingFromParentLeft)
                parent.left = null;
            else
                parent.right = null;
        else if (searchNode.left != null && searchNode.right == null)
            if (isDeletingFromParentLeft)
                parent.left = searchNode.left;
            else
                parent.right = searchNode.left;
        else if (searchNode.left == null)
            if (isDeletingFromParentLeft)
                parent.left = searchNode.right;
            else
                parent.right = searchNode.right;
        else {
            Node<Key, Value> successorParent = searchNode;
            Node<Key, Value> successor = searchNode.right;
            while (successor.left != null) {
                successorParent = successor;
                successor.size--;
                successor = successor.left;
            }
            if (isDeletingFromParentLeft)
                parent.left = successor;
            else
                parent.right = successor;

            successor.size = searchNode.size - 1;
            successor.left = searchNode.left;
            // check that the successor's parent isnt being deleted
            if (!successorParent.key.equals(searchNode.key)) {
                successorParent.left = successor.right;
                successor.right = searchNode.right;
            }

            // reassign root if it was the search node
            if (parent.key == null)
                root = successor;
        }
        if (parent.key == null)
            if (root.left == null && root.right == null)
                root = null;
            else if (root.left != null && root.right == null)
                root = root.left;
            else if (root.left == null)
                root = root.right;
        assert(isBST());
    }

    private static <Key extends Comparable<Key>, Value> int size(Node<Key, Value> node) {
        return (node == null) ? 0 : node.size;
    }

    public String toString() {
        if (root == null)
            return "null";
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

    // checks that BST follows symmetric ordering and sizes of nodes are correct
    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node<Key, Value> node, Key min, Key max) {
        if (node == null)
            return true;
        if ((min != null && node.key.compareTo(min) <= 0) ||
                (max != null && node.key.compareTo(max) >= 0))
            return false;
        if (node.size != 1 + size(node.left) + size(node.right)) {
            System.err.println("Node: <" + node.key + ", " + node.value + "> size: " + node.size + ", " +
                    "left " +
                    "size: " + size(node.left) + ", right size: " + size(node.right));
            return false;
        }
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
