import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine {

    private List<Node> mVisited;
    private Queue<List<Node>> mToVisit;

    /**
     * Creates a GraphSearcher which implements GraphSearchEngine, and is capable of
     * finding and returning the shortest path between two nodes.
     */
    public GraphSearchEngineImpl() {
        mVisited = new ArrayList<Node>();
        mToVisit = new LinkedList<List<Node>>();
    }

    /**
     * Finds and returns the shortest path between two nodes
     * @param s the start node.
     * @param t the target node.
     * @return the path from s to t, including s and t
     */
    public List<Node> findShortestPath(Node s, Node t) {
        final ArrayList<Node> initialPath = new ArrayList<Node>();
        initialPath.add(s);
        mToVisit.add(initialPath);
        mVisited.add(s);

        while (mToVisit.peek() != null) {
            final List<Node> currentPath = mToVisit.remove();
            final Node currentNode = currentPath.get(currentPath.size());
            final Collection<? extends Node> edges = currentNode.getNeighbors();
            for (Iterator<? extends Node> i = edges.iterator(); i.hasNext(); ) {
                Node neighbor = i.next();
                if (!(mVisited.contains(neighbor))) {
                    final List<Node> copiedPath = new ArrayList<Node>((currentPath.size() + 1 ));
                    Collections.copy(copiedPath, currentPath);
                    copiedPath.add(neighbor);

                    // checks to see if this is the desired node here because
                    // this is before it gets added to the queue-- so you don't have to go through
                    // the whole queue to reach it-- and this is after the path has been copied, so it can easily
                    // return the correct path.
                    if (neighbor == t) {
                        return copiedPath;
                    }

                    mToVisit.add(copiedPath);
                    mVisited.add(neighbor);

                }
            }
        }
        //!!! might want to check to see what this is supposed to return if there is no connection between actors
        return null;
    }


}
