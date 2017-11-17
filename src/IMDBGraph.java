import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class IMDBGraph implements Graph{
    protected Map<String, ActorNode> mActors;
    protected Map<String, MovieNode> mMovies;
    private int NUM_SUBTRACT_FOR_V = 4; //number of characters in " (V)"

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
        final String regex = new String("(  )|(\t)"); //whatever characters separate categories in the file

        bringToStartOfList(scanner, "----\t\t\t------");

        ActorNode currentActor = null;

        while (scanner.hasNext()) {
            String next = scanner.nextLine();
            if (next.contains("\t")) {
                String[] dividedLine = next.split(regex);

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
        //starts at index of 1 to cut of the String which could have included the actor's name
        for (int i = 1; i < dividedLine.length; i++) {
            String name = dividedLine[i];
            if (!(name.equals(""))) {

                //checks to make sure its not a tv movie/show
                if (!(name.contains("(TV)") || dividedLine[i].startsWith("\""))) {

                    //removes (V) from made for video movies
                    if (name.contains("(V)")) {
                        name = name.substring(0, name.length() - NUM_SUBTRACT_FOR_V);
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
                break;
            }
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
