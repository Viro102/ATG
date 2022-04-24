import java.util.ArrayList;

public class Vertex {
    private int id;
    private int inDeg;
    private int outDeg;
    private ArrayList<Edge> adjacentEdges;

    public Vertex(int id) {
        this.inDeg = 0;
        this.outDeg = 0;
        this.adjacentEdges = new ArrayList<>();
        this.id = id;
    }

    public void addAdjacentEdge(Edge edge) {
        this.adjacentEdges.add(edge);
        this.outDeg++;
    }

    public void printAdjacentEdges() {
        for (Edge edge : this.adjacentEdges) {
            edge.printEdge();
        }
    }

    public int getId() {
        return this.id;
    }

    public int getOutDeg() {
        return this.outDeg;
    }

    public int getInDeg() {
        return this.inDeg;
    }

    public void setOutDeg(int i) {
        this.outDeg = i;
    }

    public void setInDeg(int i) {
        this.inDeg = i;
    }

}
