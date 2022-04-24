import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

    private ArrayList<Edge> edges;

    public Graph(String file) throws IOException {
        this.edges = new ArrayList<>();
        try {
            if (loadGraph(file) == 1) {
                throw new IOException();
            }
        } catch (IOException ex) {
            System.err.println("Couldn't load the file...");
        }
    }

    public int loadGraph(String file) {
        int currentLine = 1;
        int currentVertex = 0;
        try {
            File graph = new File(file);
            Scanner s = new Scanner(graph);
            currentVertex = s.nextInt();
            while (s.hasNextLine()) {
                Vertex startVertex = new Vertex(currentVertex);
                while (currentLine == currentVertex) {
                    Vertex destVertex = new Vertex(s.nextInt());
                    int price = s.nextInt();
                    Edge edge = new Edge(startVertex, destVertex, price);
                    startVertex.addAdjacentEdge(edge);
                    this.edges.add(edge);
                    if (s.hasNext()) {
                        currentVertex = s.nextInt();
                    } else if (!s.hasNext()) {
                        break;
                    }
                }
                currentLine++;
            }
            s.close();
            return 0;
        } catch (IOException ex) {
            return 1;
        }

    }

    public void printEdges() {
        this.edges.get(0).getStartVertex().printAdjacentEdges();
    }

    public void printIdeg() {
        System.out.println("" + this.edges.get(0).getStartVertex().getOutDeg());
    }

}
