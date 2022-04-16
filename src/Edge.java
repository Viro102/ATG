public class Edge {
    private int startVertex;
    private int destVertex;
    private int price;

    public Edge(int start, int destination, int price) {
        this.startVertex = start;
        this.destVertex = destination;
        this.price = price;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getDestVertex() {
        return destVertex;
    }

    public int getPrice() {
        return price;
    }

}
