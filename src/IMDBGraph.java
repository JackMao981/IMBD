import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class IMDBGraph implements Graph{
    protected Map<String, ActorNode> mActors;
    protected Map<String, MovieNode> mMovies;
    private static final String DIRECT_TO_VIDEO = " (V)";
    private static final String TV_CASE_1 = " (TV)";
    private static final String TV_CASE_2 = "\"";
    private static final String TOP_OF_LIST = "----\t\t\t------";
    private static final String DELIMITER = "(  )|(\t)"; //whatever characters separate categories in the file

    /**
     * Constructs a graph of interconnected ActorNodes and MovieNodes based on
     * two files listing actors/actresses and the movies they appeared in.
     * @param actorsFilename the path of the file for actors
     * @param actressesFilename the path of the file for actresses
     * @throws IOException
     */
    protected IMDBGraph(String actorsFilename, String actressesFilename) throws IOException {
        mActors = new HashMap<String, ActorNode>();
        mMovies = new HashMap<String, MovieNode>();

        try {
            parse(new Scanner(new File(actorsFilename), "ISO-8859-1"));
            parse(new Scanner(new File(actressesFilename), "ISO-8859-1"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the data given by a scanner into ActorNodes and MovieNodes,
     * connects said Nodes, and stores them according to type (ActorNode or MovieNode)
     * @param scanner the scanner
     * @throws IOException
     */
    private void parse(Scanner scanner) throws IOException{
        bringToStartOfList(scanner, TOP_OF_LIST);

        ActorNode currentActor = null;

        while (scanner.hasNext()) {
            String next = scanner.nextLine();

            //only parse lines with tabs because the extra lines after the actors list will not have tabs
            if (next.contains("\t")) {
                String[] dividedLine = next.split(DELIMITER);

                //check for an IOException
                IOException ex = scanner.ioException();
                if (ex != null) {
                    throw ex;
                }

                //checks to see if new actor is encountered in the file
                if (!(dividedLine[0].equals(""))) {

                    //put in so that if an actor only appears in tv shows/movies, they will be removed from the graph
                    //this is placed here because after a new actor is encountered in the file, you know there are no more  possible movies or shows the previous actor could be in
                    if (currentActor != null) {
                        if (currentActor.getNeighbors().size() == 0) {
                            mActors.remove(currentActor.getName());
                        }
                    }

                    //creates new ActorNode
                    ActorNode actor = new ActorNode(dividedLine[0]);
                    mActors.put(dividedLine[0], actor);
                    currentActor = actor;
                }

                parseMovie(currentActor, dividedLine);
            }

        }

        scanner.close();
    }

    /**
     * Brings scanner to the first line that is a certain String
     * so that nextLine() will return the line after that line
     * @param scanner the scanner
     * @param keyString the String that is the line the scanner is to be brought to
     */
    private void bringToStartOfList(Scanner scanner, String keyString) {
        while (scanner.hasNext()) {
            if (scanner.nextLine().equals(keyString)) {
                break;
            }
        }
    }

    /**
     * Parses the movie component of each line, MovieNode is stored neighbors for both ActorNode and MovieNode is added to
     * @param currentActor the corresponding ActorNode for the to-be-parsed MovieNode
     * @param dividedLine the current line split by the delimiter
     */
    private void parseMovie (ActorNode currentActor, String[] dividedLine) {

        int index = 1;
        //skip over any leading blank elements
        while (dividedLine[index].equals("")){
            index++;
        }

        String name = dividedLine[index];

        //checks if movie is a tv show or tv movie
        if (!(name.contains(TV_CASE_1) || name.startsWith(TV_CASE_2))) {

            //removes (V) from made for video movies
            if (name.contains(DIRECT_TO_VIDEO)) {
                name = name.substring(0, name.length() - DIRECT_TO_VIDEO.length());
            }

            MovieNode movie = null;

            //sees if the movie does not already have a node
            if (!(mMovies.containsKey(name))) {

                //creates new MovieNode
                movie = new MovieNode(name);
                mMovies.put(name, movie);
            } else {
                movie = mMovies.get(name);
            }

            //adds ActorNode to MovieNode's neighbors and vise versa
            movie.addNeighbor(currentActor);
            currentActor.addNeighbor(movie);
        }
    }

    /**
     * Should be overridden by subclasses to return an ActorNode or MovieNode of the given name
     * @param name the name of the requested Node
     * @return null because unknown if a ActorNode or MovieNode is desired
     */
    public Node getNodeByName(String name) {
        return null;
    }

    /**
     * Should be overridden by subclasses to return the Collection of all ActorNodes or all MovieNodes in the graph
     * @return null because unknown if a collection of ActorNodes or MovieNodes is desired
     */
    public Collection<? extends Node> getNodes() {
        return null;
    }
}
