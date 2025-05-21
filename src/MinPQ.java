//Quellen:
// https://www.geeksforgeeks.org/binary-heap/
// https://www.geeksforgeeks.org/introduction-to-min-heap-data-structure/
// https://www.geeksforgeeks.org/implement-a-binary-heap-in-java/
// https://www.geeksforgeeks.org/priority-queue-using-binary-heap/


import java.util.ArrayList;
import java.util.List;

public class MinPQ {

    private PQElement[] heap;
    private int maxsize; // A.length
    private int currentsize; // A.heap-size

    public int getCurrentSize() {
        return currentsize;
    }

    public MinPQ(int max) {
        maxsize = max;
        heap = new PQElement[maxsize+1];
        currentsize = 0;
    }// Konstruktor

    public boolean isEmpty() {
        return currentsize == 0;
    }

public boolean insert(String d, double p) {
        int pos = findPosition(d);
        if (pos != -1) {
            // Element existiert schon → update
            if (p < heap[pos].getPrio()) {
                update(d, p);
            }
            return true; // oder false, je nach gewünschtem Verhalten
        }
        if (currentsize == maxsize){
            System.out.println("error: heap overflow");
            return false;
        }
        currentsize++;
        heap[currentsize] = new PQElement(d,p);
        int i = currentsize;
        while(i > 1 && heap[i / 2].getPrio() > heap[i].getPrio()){
            vertausche(i , i / 2);
            i = i / 2;
        }
        return true;
    }

    private int findPosition(String d) {
        for (int i = 1; i <= currentsize; i++) {
            if (heap[i].getData().equals(d)) {
                return i;
            }
        }
        return -1;
    }

    public PQElement extractElement() {
        if (currentsize < 1){
            System.out.println("error: heap underflow");
            return null;
        }
        PQElement min = heap[1];
        heap[1] = heap[currentsize];
        currentsize--;
        minheapify(heap,1);
        return min;
    }

    public String extractData() {
        PQElement min = extractElement();
        if (min!=null){
            return min.getData();
        } return null;
    }


    public void update(String s, double n) {
        for(int i = 1; i <= currentsize; i++){
            if(heap[i].getData().equals(s)){
                if(n < heap[i].getPrio()) { //weil sonst ein Elterknoten gößer als sein Nachfolger sein könnte
                    heap[i].setPrio(n);
                    bubbleUp(i);
                }
                break;
            }
        }
    }

    private void bubbleUp(int i) {
        while (i > 1 && heap[i / 2].getPrio() > heap[i].getPrio()) {
            vertausche(i, (i / 2));
            i = (i / 2);
        }
    }

    private void minheapify(PQElement[]heap , int i){
        int smallest;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left <= currentsize && heap[left].getPrio() < heap[i].getPrio()){
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= currentsize && heap[right].getPrio() < heap[smallest].getPrio()) {
            smallest = right;
        }

        if (smallest != i){
            vertausche(i, smallest);
            minheapify(heap, smallest);
        }

    }

    private void vertausche(int erste, int zweite){
        PQElement tmp;
        tmp = heap[erste];
        heap[erste] = heap[zweite];
        heap[zweite] = tmp;
    }
}
