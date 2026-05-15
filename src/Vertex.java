import java.util.HashMap;
import java.util.Map;

// Vertex holds some data (like a city name) and knows its neighbors + distances
public class Vertex<V> {

    private V data;                                      // the value stored in this vertex (e.g. "A")
    private Map<Vertex<V>, Double> adjacentVertices;     // neighbor vertex -> weight/distance

    // Constructor
    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    // Add a neighbor with a weight
    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    // Getters
    public V getData() {
        return data;
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    // Makes printing easier (shows the data value)
    @Override
    public String toString() {
        return data.toString();
    }
}