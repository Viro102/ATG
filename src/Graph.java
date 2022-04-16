import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

    private ArrayList<Edge> edges;

    public Graph() {
        this.edges = new ArrayList<>();
    }

    public void loadGraph() {
        try {
            File graph = new File("dat/graph.txt");
            Scanner s = new Scanner(graph);
            while (s.hasNextLine()) {
                this.edges.add(new Edge(s.nextInt(), s.nextInt(), s.nextInt()));
            }
            s.close();
        } catch (IOException ex) {
            System.err.println("Error");
        }
        
            
    }

    public void getEdges() {
        for(Edge e : edges) {
            System.out.println(e.getStartVertex() + " to " + e.getDestVertex() + " price: " + e.getPrice());
        }
        System.out.println(this.edges.size());
    }
}
