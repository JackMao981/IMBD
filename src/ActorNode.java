import java.util.ArrayList;
import java.util.Collection;

public class ActorNode extends GraphNode{

    String fName;
    Collection<MovieNode> fNeighbors;

    public ActorNode(String name, Collection<MovieNode> neighbors) {
        super(name);
        fNeighbors = neighbors;
    }
    public void addNeighbor(MovieNode movie) {
        fNeighbors.add(movie);
    }

}
