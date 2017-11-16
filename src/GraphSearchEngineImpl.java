import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine {

    private Queue<Node> mToVisit;
    private Map<Node, Integer> mNodeDistances;

    /**
     * Creates a GraphSearcher which is capable of finding and returning a List of Nodes
     * representing the shortest path between two nodes
     */
    public GraphSearchEngineImpl() {
        mToVisit = new LinkedList<Node>();
        //keeps track of all nodes visited and their distance from Node s
        mNodeDistances = new HashMap<Node, Integer>();
    }

    /**
     * Finds and returns the shortest path between two nodes
     * @param s the start node.
     * @param t the target node.
     * @return the path from s to t, including s and t
     */
    public List<Node> findShortestPath(Node s, Node t) {
        int radius = 0;

        mToVisit.add(s);

        while (mToVisit.peek() != null) {
            Node visitedNode = mToVisit.remove();
            if (visitedNode.equals(t)) {
                return backtrack(s, t, radius);
            }
            mNodeDistances.put(visitedNode, radius);

            final Collection<? extends Node> neighbors = visitedNode.getNeighbors();
            for (Iterator<? extends Node> i = neighbors.iterator(); i.hasNext(); ) {
                Node neighbor = i.next();
                //if the Node has already been visisted or if it is already in the Queue
                if (!mNodeDistances.containsKey(neighbor) && !mToVisit.contains(neighbor)){
                    mToVisit.add(neighbor);
                }
            }
            radius++;
        }

        return null;
    }

    /**
     * Backtracks from t to s to return the List that constitutes the shortest path between the two nodes
     * @param s the initial start node
     * @param t the initial end node
     * @param lengthOfShortestPath the distance from s to t
     * @return
     */
    private List<Node> backtrack(Node s, Node t, int lengthOfShortestPath) {
        List<Node> shortestPath = new ArrayList<Node>();
        shortestPath.add(t);

        Node currentNode = t;
        for (int i = lengthOfShortestPath - 1 ; i > 0; i--) {
            Set<Node> possibleNodes = mNodeDistances.keySet();
            for (Iterator<? extends Node> j = possibleNodes.iterator(); j.hasNext(); ) {
                Node node = j.next();

                if (mNodeDistances.get(node) == lengthOfShortestPath && node.getNeighbors().contains(currentNode)) {
                    shortestPath.add(node);
                    currentNode = node;
                }
            }
        }
        shortestPath.add(s);

        return shortestPath;
    }
}
