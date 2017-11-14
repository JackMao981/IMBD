import java.io.IOException;
import java.util.Collection;

public class IMDBMoviesGraph extends IMDBGraph{

    public IMDBMoviesGraph(String actorsFilename, String actressesFilename) throws IOException {
        super(actorsFilename, actressesFilename);
    }

    @Override
    public Node getNodeByName(String key) {
        return mMovies.get(key);
    }

    @Override
    public Collection<? extends Node> getNodes() {
        return mMovies.values();
    }
}
