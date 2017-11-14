import java.util.ArrayList;
import java.util.Collection;

public class ActorNode extends GraphNode {

	public ActorNode(String name) {
		super(name);

	}

	public void addNeighbor(MovieNode movie) {
		super.addNeighbor(movie);
	}

}
