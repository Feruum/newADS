import java.util.*;

// BFS explores the graph level by level (ignores weights).
// It finds the path with the fewest hops, not the shortest distance.
public class BreadthFirstSearch<V> extends Search<V> {

    // Constructor
    public BreadthFirstSearch(Vertex<V> start) {
        super(start);
    }

    // Returns the path from start to destination using BFS.
    // Returns an empty list if no path exists.
    @Override
    public List<Vertex<V>> findPath(Vertex<V> destination) {

        // visited: keeps track of which vertices we have already seen
        Set<Vertex<V>> visited = new HashSet<>();

        // cameFrom: for each vertex, remember which vertex we came from
        // This lets us rebuild the path at the end
        Map<Vertex<V>, Vertex<V>> cameFrom = new HashMap<>();

        // queue: the "to-do" list for BFS (first in, first out)
        Queue<Vertex<V>> queue = new LinkedList<>();

        // Start from the beginning
        queue.add(start);
        visited.add(start);
        cameFrom.put(start, null);  // start has no parent

        // Keep going until the queue is empty
        while (!queue.isEmpty()) {

            // Take the next vertex from the front of the queue
            Vertex<V> current = queue.poll();

            // If we reached the destination, stop
            if (current.equals(destination)) {
                break;
            }

            // Look at all neighbors of the current vertex
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {

                // Only visit neighbors we haven't seen yet
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);  // remember where we came from
                    queue.add(neighbor);              // add to the queue to explore later
                }
            }
        }

        // Rebuild the path by walking backwards from destination to start
        return buildPath(cameFrom, destination);
    }

    // Helper: rebuild the path from cameFrom map
    private List<Vertex<V>> buildPath(Map<Vertex<V>, Vertex<V>> cameFrom,
                                      Vertex<V> destination) {

        List<Vertex<V>> path = new ArrayList<>();

        // If destination was never reached, return empty list
        if (!cameFrom.containsKey(destination)) {
            return path;  // empty = no path found
        }

        // Walk backwards: destination -> ... -> start
        Vertex<V> step = destination;
        while (step != null) {
            path.add(step);
            step = cameFrom.get(step);
        }

        // Reverse so it goes start -> ... -> destination
        Collections.reverse(path);
        return path;
    }
}