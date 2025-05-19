import java.util.*;

public class MinPQ {
    private Node[] heap;
    private int maxsize;
    private int currentsize;

    // Grundlegendes Verständnis für Implementation von HeapSort: https://www.geeksforgeeks.org/heap-sort/?ref=gcse_outind (08.04.2025)
    // Grundlegendes Verständnis für Min-Heap Datenstruktur: https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/?ref=gcse_outind (08.04.2025)
    // Konstruktor
    public MinPQ(int maxsize) {
        this.maxsize = maxsize;
        this.currentsize = 0;
        this.heap = new Node[maxsize];
    }

    // Idee für minHeapify: https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/?ref=gcse_outind (08.04.2025)
    public void minHeapify(int i, int n) {
        // Assume the root is the smallest element initially
        int smallest = i;
        // Calculate the indices of the left and right child of the current node
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // Compare the left child with the current smallest
        if (l < n && this.heap[l].dist < this.heap[smallest].dist) smallest = l;

        // Compare the right child with the current smallest
        if (r < n && this.heap[r].dist < this.heap[smallest].dist) smallest = r;

        // If the current node is not the smallest, swap it with the smallest child
        if (smallest != i) {
            Node tmp = this.heap[i];
            this.heap[i] = this.heap[smallest];
            this.heap[smallest] = tmp;
            // Recursively heapify the subtree rooted at the smallest child
            minHeapify(smallest, n);
        }
    }

    public boolean isEmpty() {
        return this.currentsize == 0;
    }

    // Referenz: https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/?ref=gcse_outind (08.04.2025)
    public boolean insert(String d, double p) {
        Node e = new Node(d, p);
        if (this.currentsize < this.maxsize) {
            this.heap[this.currentsize] = e;
            int index = this.currentsize;
            this.currentsize++;

            // Compare the new element with its parent and swap if necessary
            while (index > 0 && this.heap[(index - 1) / 2].dist > heap[index].dist) {
                tauschen(index, (index - 1) / 2);
                // Move up the tree to the parent of the current element
                index = (index - 1) / 2;
            }

            return true;
        }
        System.out.println("Queue ist voll!");
        return false;
    }

    public Node extractElement() {
        if (isEmpty()) {
            return null;
        }
        // Wurzel (erstes Element (geringste Prio) aus dem MinHeap) speichern
        Node min = this.heap[0];
        this.heap[0] = this.heap[this.currentsize - 1]; // Element mit höchster Prio an Wurzelstelle schieben
        this.heap[this.currentsize - 1] = null;
        currentsize--;

        minHeapify(0, this.currentsize);
        return min;
    }

    public String extractData() {
        if (isEmpty()) {
            return null;
        }
        Node data = extractElement();
        return data.name;
    }

    public void update(String s, double n) {
        for (int i = 0; i < this.currentsize; i++) {
            if (this.heap[i].name.equals(s)) {
                if (this.heap[i].dist > n) {
                    this.heap[i].dist = n;
                }
                // Bubble Up - Element mit neuer Priorität muss Richtung Wurzel wandern
                int index = i;
                while (index > 0 && this.heap[(index - 1) / 2].dist > heap[index].dist) {
                    tauschen(index, (index - 1) / 2);
                    // Move up the tree to the parent of the current element
                    index = (index - 1) / 2;
                }
            }
        }
    }

    private void tauschen(int i, int j) {
        Node tmp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = tmp;
    }

}
