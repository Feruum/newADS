import java.util.List;

// Main class: builds a graph and runs BFS and Dijkstra on it.
public class Main {

    public static void main(String[] args) {


        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 1);
        graph.addEdge("B", "E", 3);
        graph.addEdge("C", "E", 1);
        graph.addEdge("E", "D", 2);

        // Get vertex objects so we can pass them to the search algorithms
        Vertex<String> vertexA = graph.getVertex("A");
        Vertex<String> vertexD = graph.getVertex("D");

        // -------------------------------------------------------
        // BFS: fewest hops from A to D
        // -------------------------------------------------------
        System.out.println("===== BFS (fewest hops) =====");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(vertexA);
        List<Vertex<String>> bfsPath = bfs.findPath(vertexD);
        printPath(bfsPath);

        // -------------------------------------------------------
        // Dijkstra: shortest weighted path from A to D
        // -------------------------------------------------------
        System.out.println("\n===== Dijkstra (shortest distance) =====");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(vertexA);
        List<Vertex<String>> dijkstraPath = dijkstra.findPath(vertexD);
        printPath(dijkstraPath);
    }

    // Helper: print the path nicely, e.g.  A -> C -> E -> D
    private static <V> void printPath(List<Vertex<V>> path) {
        if (path.isEmpty()) {
            System.out.println("No path found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));           // prints vertex data (e.g. "A")
            if (i < path.size() - 1) {
                sb.append(" -> ");
            }
        }
        System.out.println(sb.toString());
    }
}