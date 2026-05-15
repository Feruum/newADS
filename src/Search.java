import java.util.List;

// Base class for search algorithms.
// Both BFS and Dijkstra extend this.
public abstract class Search<V> {

    protected Vertex<V> start;   // the starting vertex

    // Constructor
    public Search(Vertex<V> start) {
        this.start = start;
    }

    // Every search algorithm must implement this method.
    // It returns the path (list of vertices) from start to the given destination.
    public abstract List<Vertex<V>> findPath(Vertex<V> destination);
}