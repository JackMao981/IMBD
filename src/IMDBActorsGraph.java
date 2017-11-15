import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IMDBActorsGraph extends IMDBGraph{

	/**
	 * Constructs a graph of MovieNodes and ActorNodes where only the ActorNodes are accessible to the user
	 * @param actorsFilename the path of the file for actors
	 * @param actressesFilename the path of the file for actresses
	 * @throws IOException
	 */
	public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
		super(actorsFilename, actressesFilename);
	}

	/**
	 * Returns the ActorNode of the given name in the graph
	 * @param name the name of the requested Node
	 * @return the ActorNode
	 */
	@Override
	public Node getNodeByName(String name) {
		return mActors.get(name);
	}

	/**
	 * Returns a Collection of all the ActorNodes in the graph
	 * @return a Collection of ActorNodes
	 */
	@Override
	public Collection<? extends Node> getNodes() {
		return mActors.values();
	}
}