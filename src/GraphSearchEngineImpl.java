import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine {

    private Queue<Node> mToVisit;
    private List<Node> mNodesVisited;
    private Map<Node, Integer> mNodeDistances;

    /**
     * Creates a GraphSearcher which is capable of finding and returning a List of Nodes
     * representing the shortest path between two nodes
     */
    public GraphSearchEngineImpl() {
        mToVisit = new LinkedList<Node>();
        mNodesVisited = new LinkedList<Node>();

        //keeps track of all nodes visited and their distance from Node s
        mNodeDistances = new HashMap<Node, Integer>();
    }

    /**
     * Finds and returns the shortest path between two nodes
     * @param s the start node
     * @param t the target node
     * @return the path from s to t, including s and t
     */
    public List<Node> findShortestPath(Node s, Node t) {
        // add node s to the queue
        mToVisit.add(s);
        // the distance to s is 0
        mNodeDistances.put(s, 0);

        // continue processing from the queue until node t is found or the queue is empty
        while (mToVisit.peek() != null) {
            Node visitedNode = mToVisit.remove();
            mNodesVisited.add(visitedNode);
            if (visitedNode.equals(t)) {
                //if node checked is the desired end node,
                // backtrack to find the list of nodes constituting the path to said node
                return backtrack(s, t, mNodeDistances.get(t));
            }

            final Collection<? extends Node> neighbors = visitedNode.getNeighbors();
            for (Iterator<? extends Node> i = neighbors.iterator(); i.hasNext(); ) {
                Node neighbor = i.next();
                //checks to make sure the Node has  not already been visited or if that it is not already in the Queue
                if (!mNodesVisited.contains(neighbor) && !mToVisit.contains(neighbor)){
                    mToVisit.add(neighbor);
                    mNodeDistances.put(neighbor, mNodeDistances.get(visitedNode) + 1);
                }
            }
        }

        //if there is no connection between the two nodes, return null
        return null;
    }

    /**
     * Backtracks from t to s to return the List that constitutes the shortest path between the two nodes
     * @param s the initial start node
     * @param t the initial end node
     * @param lengthOfShortestPath the distance from s to t
     * @return the shortest path from s to t
     */
    private List<Node> backtrack(Node s, Node t, int lengthOfShortestPath) {
        List<Node> shortestPath = new ArrayList<Node>();

        //starts with end node and backtracks
        shortestPath.add(t);

        Node currentNode = t;
        for (int i = lengthOfShortestPath - 1 ; i > 0; i--) {
            for (Iterator<? extends Node> j = mNodesVisited.iterator(); j.hasNext(); ) {
                Node node = j.next();

                if (mNodeDistances.get(node) == i && node.getNeighbors().contains(currentNode)) {
                    shortestPath.add(node);
                    currentNode = node;
                }
            }
        }
        shortestPath.add(s);

        //reverses path to give in correct order
        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
