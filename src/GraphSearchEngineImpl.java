import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine {

    private List<Node> mVisited;
    private Queue<Node> mToVisit;
    private Map<Node, Integer> mNodeDistances;

    /**
     * Creates a GraphSearcher which implements GraphSearchEngine, and is capable of
     * finding and returning the shortest path between two nodes.
     */
    public GraphSearchEngineImpl() {
        mVisited = new ArrayList<Node>();
        mToVisit = new LinkedList<Node>();
        mNodeDistances = new HashMap<Node, Integer>();
    }

    /**
     * Finds and returns the shortest path between two nodes
     *
     * @param s the start node.
     * @param t the target node.
     * @return the path from s to t, including s and t
     */
    public List<Node> findShortestPath(Node s, Node t) {
        int radius = 0;
        List<Node> shortestPath = new ArrayList<Node>();

        mToVisit.add(s);

        while (mToVisit.size() > 0) {
            Node visitedNode = mToVisit.remove();
            mVisited.add(visitedNode);
            mNodeDistances.put(visitedNode, radius);
            if (visitedNode.equals(t))
                break;

            final Collection<? extends Node> neighbors = visitedNode.getNeighbors();
            for (Iterator<? extends Node> i = neighbors.iterator(); i.hasNext(); ) {
                Node neighbor = i.next();
                if (!mVisited.contains(neighbor) && !mToVisit.contains(neighbor)){
                    mToVisit.add(neighbor);
                }
            }
            radius++;
        }
        shortestPath.add(t);

        Node currentNode = t;
        for ( ; radius > 0; radius--) {
            Set<Node> possibleNodes = mNodeDistances.keySet();
            for (Iterator<? extends Node> i = possibleNodes.iterator(); i.hasNext(); ) {
                Node node = i.next();

                if (mNodeDistances.get(node) == radius && node.getNeighbors().contains(currentNode)) {
                    shortestPath.add(node);
                    currentNode = node;
                }
            }
        }
        shortestPath.add(s);

        return shortestPath;
    }
}
