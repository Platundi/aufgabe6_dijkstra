import java.util.ArrayList;
import java.util.List;

public class Node {
    public String name;
    public List<Edge> neighbors;
    boolean visited;
    Node prev;
    double dist;

    public Node(String n) {
        name = n;
        neighbors = new ArrayList<Edge>();
        visited = false;
        prev = null;
        dist = Double.MAX_VALUE;
    }

    public Node(String n, double p, List<Edge> importNeighbors) {
        name = n;
        neighbors = importNeighbors;
        visited = false;
        prev = null;
        dist = p;
    }
}
