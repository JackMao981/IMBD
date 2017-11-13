import java.util.ArrayList;
import java.util.Collection;

public class MovieNode extends GraphNode{

    public MovieNode(String name, ArrayList<ActorNode> neighbors) {
        super(name, neighbors);
    }

    public void addNeighbor(ActorNode actor) {
        fNeighbors.add(actor);
    }
}