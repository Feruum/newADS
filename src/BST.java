import java.util.ArrayList;
import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValuePair<K, V>> {

    private Node root;
    private int size; // Part 2.2 - added size

    // Inner node class
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // Part 2.2 - A simple class to hold key AND value together during iteration
    public static class KeyValuePair<K, V> {
        private K key;
        private V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    // Part 2.2 - size method
    public int size() {
        return size;
    }

    // Put a key-value pair into the BST
    public void put(K key, V val) {
        root = putHelper(root, key, val);
    }

    // Recursive helper for put
    private Node putHelper(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val); // create new node here
        }

        int compare = key.compareTo(node.key);

        if (compare < 0) {
            node.left = putHelper(node.left, key, val);   // go left
        } else if (compare > 0) {
            node.right = putHelper(node.right, key, val); // go right
        } else {
            node.val = val; // same key - just update the value
        }

        return node;
    }

    // Get value for a given key
    public V get(K key) {
        Node current = root;

        while (current != null) {
            int compare = key.compareTo(current.key);

            if (compare < 0) {
                current = current.left;  // go left
            } else if (compare > 0) {
                current = current.right; // go right
            } else {
                return current.val;      // found it!
            }
        }

        return null; // key not found
    }

    // Delete a node with the given key
    public void delete(K key) {
        root = deleteHelper(root, key);
    }

    // Recursive helper for delete
    private Node deleteHelper(Node node, K key) {
        if (node == null) return null; // key not found, do nothing

        int compare = key.compareTo(node.key);

        if (compare < 0) {
            node.left = deleteHelper(node.left, key);   // go left
        } else if (compare > 0) {
            node.right = deleteHelper(node.right, key); // go right
        } else {
            // Found the node to delete!
            size--;

            // Case 1: no right child - replace with left child
            if (node.right == null) return node.left;

            // Case 2: no left child - replace with right child
            if (node.left == null) return node.right;

            // Case 3: has two children
            // Find the smallest node in the right subtree (in-order successor)
            Node smallest = findMin(node.right);

            // Copy successor's key and value into this node
            node.key = smallest.key;
            node.val = smallest.val;

            // Delete the successor from the right subtree
            node.right = deleteHelper(node.right, smallest.key);
            size++; // undo the size-- from the recursive call above
        }

        return node;
    }

    // Helper to find the minimum node starting from a given node
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Part 2.2 - In-order traversal iterator
    // In-order means: left -> current -> right (gives sorted order)
    public Iterator<KeyValuePair<K, V>> iterator() {
        ArrayList<KeyValuePair<K, V>> list = new ArrayList<>();
        inOrder(root, list);         // fill list in sorted order
        return list.iterator();      // return a standard iterator
    }

    // Recursive in-order traversal
    private void inOrder(Node node, ArrayList<KeyValuePair<K, V>> list) {
        if (node == null) return;

        inOrder(node.left, list);                              // visit left
        list.add(new KeyValuePair<>(node.key, node.val));     // visit current
        inOrder(node.right, list);                            // visit right
    }
}