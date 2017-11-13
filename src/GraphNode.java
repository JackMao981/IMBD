import java.util.ArrayList;
import java.util.Collection;

public class GraphNode implements Node {

	String fName;
	Collection<? extends Node> fNeighbors;

	public GraphNode(String name, Collection<? extends Node> neighbor) {
		fName = name;
		fNeighbors = neighbor;
		// !!! issue with inability to instantiate fNeighbors
	}

	public String getName() {
		return fName;
	}

	public Collection<? extends Node> getNeighbors() {
		return fNeighbors;
	}
}
