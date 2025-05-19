public class PQElement {
    private String data;
    private double priority;

    public PQElement(String s, double d) {
        this.data = s;
        this.priority = d;
    }
    public String getData() {
        return this.data;
    }
    public double getPrio() {
        return this.priority;
    }
    public void setPrio(double d) {
        this.priority = d;
    }
}
