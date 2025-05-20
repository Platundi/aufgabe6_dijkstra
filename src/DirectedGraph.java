/* Quellen:
 * https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
 * claude.ai
 * Folien
 * https://www.programiz.com/dsa/graph-bfs
 * https://www.w3schools.com/java/java_linkedlist.asp */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DirectedGraph {
    public static final double INFINITY =
            Double.MAX_VALUE;
    private Map<String, Node> nodes =
            new HashMap<String, Node>();
    private MinPQ pq;

    public void dijkstra(String start) {
        pq = new MinPQ(nodes.size());
        for (Node node : nodes.values()) {
            node.visited = false;
            node.prev = null;
            node.dist = INFINITY;
            if (node.name == start) {
                node.dist = 0;
            }
            pq.insert(node.name, node.dist);
        }
        while (!pq.isEmpty()) {
            Node u = pq.extractElement();
            u.visited = true;
            for (Edge edge : u.neighbors) {
                Node neighbor = edge.dest;
                if (neighbor.visited == false && neighbor.dist > u.dist + edge.weight) {
                    neighbor.dist = u.dist + edge.weight;
                    neighbor.prev = u;
                    pq.update(neighbor.name, neighbor.dist);
                }
            }
        }
    }

    public DirectedGraph readGraph(String file) {
        DirectedGraph graph = new DirectedGraph();

        // claude.ai (Prompt: datei einlesen java)
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // claude.ai (Prompt: regex für leerzeichen und whitespace)
                String[] parts = line.trim().split("\\s+"); // trim -> entfernt leerzeichen, split("\\s+") -> trennt bei leerzeichen oder andere whitespace-zeichen
                // wenn die drei elemente in os_map gefunden sind -> zuordnen
                if (parts.length == 3) {
                    String sourceNodeName = parts[0];
                    String destNodeName = parts[1];
                    double weight = Double.parseDouble(parts[2]);

                    // knoten erzeugen/abrufen mit hilfsmethode
                    Node sourceNode = graph.getOrCreateNode(sourceNodeName, weight);
                    Node destNode = graph.getOrCreateNode(destNodeName, weight);

                    // add kante
                    sourceNode.neighbors.add(new Edge(destNode, weight));
                }
            }
        } catch (IOException e) {
            System.err.println("fehler beim lesen der datei: " + e.getMessage());
            return null;
        }

        return graph;
    }

    // hilfsmethode um knoten hinzuzufügen
    private Node getOrCreateNode(String name, double weight) {
        if (!nodes.containsKey(name)) {
            nodes.put(name, new Node(name, weight));
        }
        return nodes.get(name);
    }

    public boolean BFS(String start, String dest, int max) {
        // existieren start und zielknoten?
        if (!nodes.containsKey(start) || !nodes.containsKey(dest)) {
            return false;
        }

     /*   // alle knoten resetten
        for (Node node : nodes.values()) {
            node.prev = null;
            node.dist = INFINITY;
            node.visited = false;
        }*/

        dijkstra(start);

        Node startNode = nodes.get(start);
/*        startNode.prev = null;
        startNode.visited = true;
        startNode.dist = 0;*/

        Node destNode = nodes.get(dest);

        // queue durch linkedList -> brauchen für queue ueberwiegend hinzufügen am ende (add) der queue
        // und entfernen am anfang (poll) der queue -> linkedList eignet sich durch die methoden
/*        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);*/

        while (!pq.isEmpty()) {
            Node u = pq.extractElement();

            // Ziel gefunden
            if (u == destNode) {
                return true;
            }

            // max pfadlänge überschritten -> mit nächstem knoten weitermachen
            if (u.dist >= max) {
                return false;
            }

            // alle nachbarn durchgehen
  /*          for (Edge edge : u.neighbors) {
                Node neighbor = edge.dest;
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    neighbor.dist = u.dist + 1;
                    neighbor.prev = u;
                    queue.add(neighbor);
                }
            }*/
        }

        // kein pfad gefunden zum ziel
        return false;
    }

    public void printPath(String dest) {
        if (!nodes.containsKey(dest)) {
            System.out.println("zielknoten existiert nicht.");
            return;
        }

        Node destNode = nodes.get(dest);

        if (destNode.dist == INFINITY) {
            System.out.println("es existiert kein pfad zum zielknoten.");
            return;
        }

        List<String> path = new ArrayList<>();
        for (Node current = destNode; current != null; current = current.prev) {
            path.add(current.name);
        }

        // Pfad in richtiger Reihenfolge ausgeben (vom Start zum Ziel)
        System.out.println("pfad vom start zum ziel mit laenge " + destNode.dist + ":");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i));
            if (i > 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
