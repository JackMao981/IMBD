import java.io.IOException;

public class IMDBMoviesGraph extends IMDBGraph{

    public IMDBMoviesGraph(String actorsFilename, String actressesFilename) throws IOException {
        super(actorsFilename, actressesFilename);
    }
}
