import java.util.ArrayList;
import java.util.Collection;

public class ActorNode extends GraphNode {

	/**
	 * Constructs a Node containing the name of an actor which will also have
	 * a Collection of MovieNodes representing the movies the actor has appeared in
	 * @param name the name of the actor
	 */
	public ActorNode(String name) {
		super(name);
	}

	/**
	 * Adds a MovieNode to the Collection of MovieNodes representing the movies the actor appeared in
	 * @param movie the MovieNode
	 */
	public void addNeighbor(MovieNode movie) {
		super.addNeighbor(movie);
	}

}
