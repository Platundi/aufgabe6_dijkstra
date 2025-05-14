public class Main {
    public static void main(String[] args) {

        // Graph aus der Datei einlesen
        DirectedGraph graph = DirectedGraph.readGraph("OS_Map.txt");

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
}