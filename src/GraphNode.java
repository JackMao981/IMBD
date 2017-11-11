import java.util.ArrayList;

public class GraphNode implements Node{

    String fName;
    ArrayList<MovieNode> fNeighbors;

    public GraphNode(String name) {
        fName = name;
    }

    public String getName() {
        return fName;
    }

    public ArrayList<MovieNode> getNeighbors() {
        return fNeighbors;
    }
}

