import java.util.ArrayList;
import java.util.Collection;

public class GraphNode implements Node{

    String fName;
    Collection<? extends Node> fNeighbors;

    public GraphNode(String name) {
        fName = name;

        //!!! issue with inability to instantiate fNeighbors
    }

    public String getName() {
        return fName;
    }

    public Collection<? extends Node> getNeighbors() {
        return fNeighbors;
    }
}

