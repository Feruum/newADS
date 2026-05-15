import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The graph holds all vertices and lets you add directed edges between them
public class WeightedGraph<V> {

    // Each vertex maps to a list of its neighbors (stored inside Vertex itself,
    // but we also keep a list of all vertices here so we can iterate them)
    private Map<V, Vertex<V>> vertices;

    // Constructor
    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    // Add a new vertex to the graph (if it doesn't exist yet)
    public void addVertex(V data) {
        if (!vertices.containsKey(data)) {
            vertices.put(data, new Vertex<>(data));
        }
    }

    // Add a directed edge from source -> dest with a given weight
    public void addEdge(V sourceData, V destData, double weight) {
        // Make sure both vertices exist
        addVertex(sourceData);
        addVertex(destData);

        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest   = vertices.get(destData);

        source.addAdjacentVertex(dest, weight);
    }

    // Get a vertex object by its data value
    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    // Get all vertices in the graph
    public List<Vertex<V>> getAllVertices() {
        return new ArrayList<>(vertices.values());
    }
}