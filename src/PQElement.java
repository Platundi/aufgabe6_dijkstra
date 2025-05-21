//Quellen:
// https://www.geeksforgeeks.org/custom-comparator-for-a-specific-element-type-in-a-priorityqueue-in-java/


public class PQElement {
    private String data;
    private double priority;

    public PQElement(String s, double d) {
        this.data = s;
        this.priority = d;

    }
    public String getData() {
        return data;
    }
    public double getPrio() {
        return priority;
    }
    public void setPrio(double d) {
        this.priority = d;
    }

}
