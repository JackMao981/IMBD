import java.util.ArrayList;
import java.util.Collection;

public class ActorNode extends GraphNode{

    String fName;
    ArrayList<MovieNode> fNeighbors;

    public ActorNode(String name, ArrayList<MovieNode> neighbors) {
        super(name);
        fNeighbors = neighbors;
    }
    public void addNeighbor(MovieNode movie) {
        fNeighbors.add(movie);
    }

}
