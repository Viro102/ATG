public class Main {
    public static void main(String[] args) throws Exception {
        Graph graph = new Graph("dat/graph.txt");
        graph.printEdges();
        graph.printIdeg();
    }
}
