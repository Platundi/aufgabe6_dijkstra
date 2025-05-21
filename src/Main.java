public class Main {
    public static void main(String[] args) {

        // Graph aus der Datei einlesen
        DirectedGraph drg = new DirectedGraph();
        DirectedGraph graph = drg.readGraph("OS_Map.txt");

        if (graph == null) {
            System.out.println("fehler beim einlesen des graphen.");
            return;
        }

        System.out.println("graph wurde erfolgreich eingelesen.");

        // Test der BFS-Methode mit verschiedenen Start- und Zielknoten und max-Werten
        testBFS(graph, "Osnabrueck", "Melle", 3);
        testBFS(graph, "Osnabrueck", "Melle", 10);
        testBFS(graph, "Wallenhorst", "Bissendorf", 5);
        testBFS(graph, "Osnabrueck", "Bissendorf", 4);
        testBFS(graph, "Ladbergen", "Osnabrueck", 8);

        System.out.println("\nDijkstra-Tests");
        testDijkstra(graph, "Osnabrueck", "Osterkappeln");
        testDijkstra(graph, "Glandorf", "Bramsche");
}

    private static void testBFS(DirectedGraph graph, String start, String dest, int max) {
        System.out.println("\nsuche pfad von " + start + " nach " + dest + " mit maximal " + max + " kanten:");
        boolean foundPath = graph.BFS(start, dest, max);

        if (foundPath) {
            System.out.println("pfad gefunden!");
            graph.printPath(dest);
        } else {
            System.out.println("kein pfad gefunden mit maximal " + max + " kanten.");
        }
    }
    public static void testDijkstra(DirectedGraph graph, String start, String ziel) {
        graph.dijkstra(start);
        Node zielNode = graph.getOrCreateNode(ziel);
        if (zielNode != null && zielNode.dist < DirectedGraph.INFINITY) {
            graph.printPath(ziel);
            System.out.print(" (Distanz:" + zielNode.dist + ")");
            System.out.println();
        } else {
            System.out.println("Kein Pfad gefunden.");
        }
    }
}