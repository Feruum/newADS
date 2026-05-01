public class BSTMain {
    public static void main(String[] args) {

        BST<Integer, String> tree = new BST<>();

        // Add some elements
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(9, "nine");

        System.out.println("Size: " + tree.size()); // should print 7

        System.out.println("\nGet key 3: " + tree.get(3));  // "three"
        System.out.println("Get key 99: " + tree.get(99)); // null

        // In-order traversal (should print keys in sorted order: 1,3,4,5,6,7,9)
        System.out.println("\n--- In-order traversal ---");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        // Delete a node
        tree.delete(3);
        System.out.println("\nAfter deleting key 3, size: " + tree.size());

        System.out.println("\n--- After deletion ---");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}