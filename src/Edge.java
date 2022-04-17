public class Edge {
    private Vertex startVertex;
    private Vertex destVertex;
    private int price;

    public Edge(Vertex start, Vertex destination, int price) {
        this.startVertex = start;
        this.destVertex = destination;
        this.price = price;
    }

    public void printEdge() {
        System.out.println(
                "{" + this.startVertex.getId() + ", " + this.destVertex.getId() + ", " + this.getPrice() + "}");
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getDestVertex() {
        return destVertex;
    }

    public int getPrice() {
        return price;
    }

}
