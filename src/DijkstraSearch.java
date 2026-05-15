import java.util.*;

// Dijkstra finds the SHORTEST weighted path from start to any destination.
// It always picks the unvisited vertex with the smallest known distance next.
public class DijkstraSearch<V> extends Search<V> {

    // Constructor
    public DijkstraSearch(Vertex<V> start) {
        super(start);
    }

    // Returns the shortest weighted path from start to destination.
    // Returns an empty list if no path exists.
    @Override
    public List<Vertex<V>> findPath(Vertex<V> destination) {

        // distances: best known distance from start to each vertex
        // We use infinity as the default (means "not yet reached")
        Map<Vertex<V>, Double> distances = new HashMap<>();

        // cameFrom: to rebuild the path at the end
        Map<Vertex<V>, Vertex<V>> cameFrom = new HashMap<>();

        // visited: vertices whose shortest path is already finalized
        Set<Vertex<V>> visited = new HashSet<>();

        // Priority queue: always process the vertex with the smallest distance first
        // Each entry is an array: [vertex, distance]
        PriorityQueue<double[]> pq = new PriorityQueue<>(
                Comparator.comparingDouble(entry -> entry[1])
        );

        // We also need a way to map the double[] back to a Vertex.
        // Simpler approach: store an index and keep a list of vertices.
        // -- Actually, let's use a Map<Vertex, Double> approach with a custom comparator.

        // Better approach for beginners: use a list to map indices to vertices
        List<Vertex<V>> vertexList = new ArrayList<>();
        Map<Vertex<V>, Integer> indexMap = new HashMap<>();

        // We'll collect all vertices we know about as we go
        // Start by registering the start vertex
        vertexList.add(start);
        indexMap.put(start, 0);
        distances.put(start, 0.0);
        cameFrom.put(start, null);

        // Add start to priority queue: [index, distance]
        pq.offer(new double[]{0, 0.0});

        // ----- Main Dijkstra loop -----
        while (!pq.isEmpty()) {

            // Pick the vertex with the smallest distance
            double[] entry    = pq.poll();
            int      idx      = (int) entry[0];
            double   dist     = entry[1];
            Vertex<V> current = vertexList.get(idx);

            // Skip if already finalized
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            // Stop early if we reached the destination
            if (current.equals(destination)) {
                break;
            }

            // Look at each neighbor
            for (Map.Entry<Vertex<V>, Double> neighborEntry
                    : current.getAdjacentVertices().entrySet()) {

                Vertex<V> neighbor     = neighborEntry.getKey();
                double    edgeWeight   = neighborEntry.getValue();
                double    newDist      = dist + edgeWeight;

                // Register neighbor if we haven't seen it yet
                if (!indexMap.containsKey(neighbor)) {
                    vertexList.add(neighbor);
                    indexMap.put(neighbor, vertexList.size() - 1);
                    distances.put(neighbor, Double.MAX_VALUE);
                }

                // Relax the edge: update if we found a shorter path
                if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, newDist);
                    cameFrom.put(neighbor, current);
                    pq.offer(new double[]{indexMap.get(neighbor), newDist});
                }
            }
        }

        // Rebuild the path
        return buildPath(cameFrom, destination);
    }

    // Helper: walk backwards from destination using cameFrom
    private List<Vertex<V>> buildPath(Map<Vertex<V>, Vertex<V>> cameFrom,
                                      Vertex<V> destination) {

        List<Vertex<V>> path = new ArrayList<>();

        if (!cameFrom.containsKey(destination)) {
            return path;  // no path found
        }

        Vertex<V> step = destination;
        while (step != null) {
            path.add(step);
            step = cameFrom.get(step);
        }

        Collections.reverse(path);
        return path;
    }
}