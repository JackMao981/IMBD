import java.io.IOException;
import java.util.Collection;

public class IMDBMoviesGraph extends IMDBGraph{

    /**
     * Constructs a graph of MovieNodes and ActorNodes where only the MovieNodes are accessible to the user
     * @param actorsFilename the path of the file for actors
     * @param actressesFilename the path of the file for actresses
     * @throws IOException
     */
    public IMDBMoviesGraph(String actorsFilename, String actressesFilename) throws IOException {
        super(actorsFilename, actressesFilename);
    }


    /**
     * Returns the MovieNode of the given name in the graph
     * @param name the name of the requested Node
     * @return the MovieNode
     */
    @Override
    public Node getNodeByName(String name) {
        return mMovies.get(name);
    }

    /**
     * Returns a Collection of all the MovieNodes in the graph
     * @return a Collection of MovieNodes
     */
    @Override
    public Collection<? extends Node> getNodes() {
        return mMovies.values();
    }
}
