import java.util.ArrayList;
import java.util.Collection;

public class GraphNode implements Node {

	protected String mName;
	protected Collection<GraphNode> mNeighbors;

	protected GraphNode(String name) {
		mName = name;
		mNeighbors = new ArrayList<GraphNode>();
	}

	public String getName() {
		return mName;
	}

	public Collection<? extends Node> getNeighbors() {
		return mNeighbors;
	}

    protected void addNeighbor(GraphNode movie) {
        mNeighbors.add(movie);
    }
}
