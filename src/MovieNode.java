import java.util.ArrayList;
import java.util.Collection;

public class MovieNode extends GraphNode {
	ArrayList<ActorNode> fNeighbors;

	public MovieNode(String name) {
		super(name);
	}

    public void addNeighbor(ActorNode actor) {
        super.addNeighbor(actor);
    }
}