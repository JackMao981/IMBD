import java.util.ArrayList;
import java.util.Collection;

public class ActorNode implements Node{

    String fName;
    ArrayList<MovieNode> fNeighbors;

    public ActorNode(String name, ArrayList<MovieNode> neighbors) {
        fName = name;
        fNeighbors = neighbors;
    }

    public String getName() {
        return fName;
    }

    public ArrayList<MovieNode> getNeighbors() {
        return fNeighbors;
    }
}
