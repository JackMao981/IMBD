import java.util.ArrayList;
import java.util.Collection;

public class MovieNode implements Node{

    String fName;
    ArrayList<ActorNode> fNeighbors;

    public MovieNode(String name, ArrayList<ActorNode> neighbors) {
        fName = name;
        fNeighbors = neighbors;
    }

    public String getName() {
        return fName;
    }

    public void addNeighbor(ActorNode actor) {
        fNeighbors.add(actor);
    }

    public ArrayList<ActorNode> getNeighbors() {
        return fNeighbors;
    }
}