import java.util.ArrayList;
import java.util.Collection;

public class MovieNode extends GraphNode {

    /**
     * Constructs a Node containing the name of a movie and
     * a Collection of ActorNodes representing the actors who appeared in the movie
     * @param name the name of the movie
     */
	public MovieNode(String name) {
		super(name);
	}

	/**
     * Adds an ActorNode to the Collection of ActorNodes representing the actors who appeared in the movie
     * @param actor the ActorNode
     */
    public void addNeighbor(ActorNode actor) {
        super.addNeighbor(actor);
    }
}