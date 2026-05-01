public class MyHashTable<K, V> {

    // Inner class to represent each node in the chain (linked list)
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next; // pointer to next node in the chain

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray; // array of linked lists
    private int M = 11;                  // default number of buckets/chains
    private int size;                    // total number of elements stored

    // Default constructor - uses M = 11 buckets
    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    // Constructor where you can choose number of buckets
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    // Simple hash function - converts key to an index
    private int hash(K key) {
        int hashCode = key.hashCode();   // get the key's hash code
        if (hashCode < 0) {
            hashCode = -hashCode;        // make it positive if negative
        }
        return hashCode % M;             // fit it into range [0, M-1]
    }

    // Add a key-value pair. If key already exists, update the value.
    public void put(K key, V value) {
        int index = hash(key);           // find which bucket to use
        HashNode<K, V> current = chainArray[index];

        // Walk through the chain to see if key already exists
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;   // update value if key found
                return;
            }
            current = current.next;
        }

        // Key not found - add new node at the beginning of the chain
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index]; // new node points to old head
        chainArray[index] = newNode;      // new node becomes the head
        size++;
    }

    // Get the value for a given key. Returns null if not found.
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;    // found it!
            }
            current = current.next;
        }
        return null; // key not found
    }

    // Remove a key-value pair. Returns the removed value or null.
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // Found the node to remove
                if (previous == null) {
                    chainArray[index] = current.next; // removing the head
                } else {
                    previous.next = current.next;     // skip over current
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null; // key not found
    }

    // Check if a certain VALUE exists anywhere in the table
    public boolean contains(V value) {
        // We need to check every bucket
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    // Find and return the KEY that maps to a given VALUE
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null; // value not found
    }

    // Returns how many elements are stored
    public int size() {
        return size;
    }

    // Print how many elements are in each bucket (chain)
    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}