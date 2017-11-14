import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class IMDBGraph implements Graph{
    private HashMap<String, ActorNode> mActors;
    private HashMap<String, MovieNode> mMovies;
    private File mActorFile;
    private File mActressFile;
    private Scanner mActorScanner;
    private Scanner mActressScanner;

    public IMDBGraph(String actorsFilename, String actressesFilename) throws IOException {
        mActors = new HashMap<String, ActorNode>();
        mMovies = new HashMap<String, MovieNode>();
        mActorFile = new File(actorsFilename);
        mActressFile = new File(actressesFilename);

        try {
            mActorScanner = new Scanner(mActorFile, "ISO-8859-1");
            mActressScanner = new Scanner(mActressFile, "ISO-8859-1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        parse(mActorScanner);
        parse(mActressScanner);
    }

    private void parse(Scanner scanner) {

        final String regex = new String("  "); //whatever character separates categories in the file
        ActorNode currentActor = null;

        while (scanner.hasNext()) {
            String[] dividedLine = scanner.nextLine().split(regex);

            //checks to see if new actor is encountered in the file
            if (!(dividedLine[0].equals(""))) {

                //put in so that if an actor only appears in tv shows/movies, they will be removed from the graph
                //this is placed here because after a new actor is encountered in the file, you know there are no more  possible movies or shows the previous actor could be in
                if (currentActor.getNeighbors() == null) {
                    mActors.remove(currentActor.getName());
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

                    }
                }
            }
        }

    }

    public GraphNode getNodeByName(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection<? extends Node> getNodes() {
        // TODO Auto-generated method stub
        return null;
    }







}
