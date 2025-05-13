package structs.collections;

public class BSTMap<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    public BSTMap() {
        root = null;
    }

    public void insert(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException();

        Node<Key, Value> prev = null;
        Node<Key, Value> curr = root;

        while (curr != null) {
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
            if (prev != null)
                if (key.compareTo(prev.key) < 0)
                    prev.left = new Node<>(key, value);
                else
                    prev.right = new Node<>(key, value);
            else
                root = new Node<>(key, value);
        }

        assert(isBST());
    }

    public String toString() {
        return toString(root, new StringBuilder(), 0).toString();
    }

    private StringBuilder toString(Node<Key, Value> node, StringBuilder sb,
                            int recursionLevel) {
        if (node == null)
            return sb;

        toString(node.left, sb, recursionLevel + 1);
        sb.append("-".repeat(recursionLevel))
                .append("[")
                .append(node.key)
                .append("]")
                .append(node.value).append("\n");
        toString(node.right, sb, recursionLevel + 1);

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

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
