import java.util.ArrayList;
import java.util.Collection;

public class MovieNode extends GraphNode{

    String fName;
    Collection<ActorNode> fNeighbors;

    public MovieNode(String name, ArrayList<ActorNode> neighbors) {
        super(name);
        fNeighbors = neighbors;
    }

    public void addNeighbor(ActorNode actor) {
        fNeighbors.add(actor);
    }
}