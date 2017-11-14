import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class IMDBGraph implements Graph{
    protected Map<String, ActorNode> mActors;
    protected Map<String, MovieNode> mMovies;

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

    private void parse(Scanner scanner) throws IOException{

        final String regex = new String("(  )|(\t)"); //whatever character separates categories in the file
        ActorNode currentActor = null;

        bringToStartOfList(scanner, "----\t\t\t------");

        while (scanner.hasNext()) {
            String[] dividedLine = scanner.nextLine().split(regex);

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

            for (int i = 1; i < dividedLine.length; i++) {
                if (!(dividedLine[i].equals(""))) {

                    //checks to make sure its not a tv movie/show
                    if (!(dividedLine[i].contains("(TV)") || dividedLine[i].startsWith("\""))) {

                        MovieNode movie = null;

                        //sees if the movie does not already have a node
                        if (! (mMovies.containsKey(dividedLine[i]))) {
                            //creates new MovieNode
                            movie = new MovieNode(dividedLine[i]);
                            mMovies.put(dividedLine[i], movie);
                        } else {
                            movie = mMovies.get(dividedLine[i]);
                        }

                        //adds ActorNode to MovieNode's neighbors and vise versa
                        movie.addNeighbor(currentActor);
                        currentActor.addNeighbor(movie);
                        break;

                    } else {
                        break;
                    }
                }
            }
        }
    }

    private void bringToStartOfList(Scanner scanner, String codeWord) {
        while (scanner.hasNext()) {
            if (scanner.nextLine().equals(codeWord)) {
                break;
            }
        }
    }

    public Node getNodeByName(String key) {
        return null;
    }

    public Collection<? extends Node> getNodes() {
        return null;
    }
}
