import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Graph {

    private HashMap<Vertex, ArrayList<Edge>> adjacencyList;
    private ArrayList<Edge> edges;
    private ArrayList<Vertex> startVertices;

    public Graph(String file) throws IOException {
        this.edges = new ArrayList<>();
        this.startVertices = new ArrayList<>();
        try {
            if (loadGraph(file) == 1) {
                throw new IOException();
            }
        } catch (IOException ex) {
            System.err.println("Couldn't load the file...");
        }
    }

    public int loadGraph(String file) {
        try {
            File graph = new File(file);
            Scanner s = new Scanner(graph);
            while (s.hasNextLine()) {
                Vertex startVertex = new Vertex(s.nextInt());
                Vertex destVertex = new Vertex(s.nextInt());
                int price = s.nextInt();
                Edge edge = new Edge(startVertex, destVertex, price);
                this.startVertices.add(startVertex);
                this.edges.add(edge);

            }
            s.close();
            return 0;
        } catch (IOException ex) {
            return 1;
        }

    }

    public void getEdges() {
        this.adjacencyList = new HashMap<>();
    }

    public void getAdjacentEdges() {
        for (int i = 0; i < this.adjacencyList.size(); i++) {

        }
    }
}
