import java.util.List;

public class Node {
    public String name;
    public List<Edge> neighbors;
    boolean visited;
    Node prev;
    double dist;

    public Node(String n) {
    }
}
