//Quellen:
// https://www.geeksforgeeks.org/binary-heap/
// https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/
// https://www.geeksforgeeks.org/implement-a-binary-heap-in-java/
// https://www.geeksforgeeks.org/priority-queue-using-binary-heap/


import java.util.ArrayList;
import java.util.List;

public class MinPQ {

    private Node[] heap;
    private int maxsize; // A.length
    private int currentsize; // A.heap-size

    public int getCurrentSize() {
        return currentsize;
    }

    public MinPQ(int max) {
        maxsize = max;
        heap = new Node[maxsize+1];
        currentsize = 0;
    }// Konstruktor

    public boolean isEmpty() {
        return currentsize == 0;
    }

public boolean insert(String d, double p, List<Edge> neighbors) {
        int pos = findPosition(d);
        if (pos != -1) {
            // Element existiert schon → update
            if (p < heap[pos].dist) {
                update(d, p);
            }
            return true; // oder false, je nach gewünschtem Verhalten
        }
        if (currentsize == maxsize){
            System.out.println("error: heap overflow");
            return false;
        }
        currentsize++;
        heap[currentsize] = new Node(d,p,neighbors);
        int i = currentsize;
        while(i > 1 && heap[i / 2].dist > heap[i].dist){
            vertausche(i , i / 2);
            i = i / 2;
        }
        return true;
    }

    private int findPosition(String d) {
        for (int i = 1; i <= currentsize; i++) {
            if (heap[i].name.equals(d)) {
                return i;
            }
        }
        return -1;
    }

    public Node extractElement() {
        if (currentsize < 1){
            System.out.println("error: heap underflow");
            return null;
        }
        Node min = heap[1];
        heap[1] = heap[currentsize];
        currentsize--;
        minheapify(heap,1);
        return min;
    }

    public String extractData() {
        Node min = extractElement();
        if (min!=null){
            return min.name;
        } return null;
    }


    public void update(String s, double n) {
        for(int i = 1; i <= currentsize; i++){
            if(heap[i].name.equals(s)){
                if(n < heap[i].dist) { //weil sonst ein Elterknoten gößer als sein Nachfolger sein könnte
                    heap[i].dist = n;
                    bubbleUp(i);
                }
                break;
            }
        }
    }

    private void bubbleUp(int i) {
        while (i > 1 && heap[i / 2].dist > heap[i].dist) {
            vertausche(i, (i / 2));
            i = (i / 2);
        }
    }

    private void minheapify(Node[]heap , int i){
        int smallest;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left <= currentsize && heap[left].dist < heap[i].dist){
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= currentsize && heap[right].dist < heap[smallest].dist) {
            smallest = right;
        }

        if (smallest != i){
            vertausche(i, smallest);
            minheapify(heap, smallest);
        }

    }

    private void vertausche(int erste, int zweite){
        Node tmp;
        tmp = heap[erste];
        heap[erste] = heap[zweite];
        heap[zweite] = tmp;
    }
}
