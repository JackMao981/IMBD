import java.util.ArrayList;
import java.util.Collection;

public class GraphNode implements Node {

	private String mName;
	private Collection<Node> mNeighbors;

	/**
	 * Constructs a Node for a graph where the edges are represented by a Collection of neighboring Nodes
	 * @param name the name of the Node
	 */
	protected GraphNode(String name) {
		mName = name;
		mNeighbors = new ArrayList<Node>();
	}

	/**
	 * Returns the name of the Node
	 * @return the name
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Returns a Collection of all neighboring Nodes
	 * @return neighboring Nodes
	 */
	public Collection<? extends Node> getNeighbors() {
		return mNeighbors;
	}

	/**
	 * Adds a Node to the Collection of neighboring Nodes
	 * @param node the Node to be added to the Collection of neighbors
	 */
    protected void addNeighbor(Node node) {
        mNeighbors.add(node);
    }
}
