import java.util.*;

public class GraphSearcher implements GraphSearchEngine {

    private List<Node> mVisited;
    private Node mVisitedNode;
    private Queue<Node> mToVisit;
    private List<Node> mShortestPath;
    private List<NodeDistance> mNodeDistances;
    private int radius;

    public class NodeDistance {
        public int mDistance;
        public Node mNode;

        public NodeDistance(int distance, Node node) {
            mDistance = distance;
            mNode = node;
        }

        public int getDistance() {
            return this.mDistance;
        }

        public Node getNodeWithDistance() {
            return this.mNode;
        }
    }


    /**
     * Creates a GraphSearcher which implements GraphSearchEngine, and is capable of
     * finding and returning the shortest path between two nodes.
     */
    public GraphSearcher() {
        mVisited = new ArrayList<Node>();
        mToVisit = new LinkedList<Node>();
        mNodeDistances = new ArrayList<NodeDistance>();
        mShortestPath = new ArrayList<Node>();
        radius = 0;

    }
    /**
     * Finds and returns the shortest path between two nodes
     *
     * @param s the start node.
     * @param t the target node.
     * @return the path from s to t, including s and t
     */
    public List<Node> findShortestPath(Node s, Node t) {
        mToVisit.add(s);
        /**
         * Searches through the graph until node t is found, building a list of visited nodes
         * and their distances from s.
         */
        while (mToVisit.size() > 0) {
            mVisitedNode = mToVisit.remove();
            mVisited.add(mVisitedNode);
            mNodeDistances.add(new NodeDistance(radius, mVisitedNode));
            if (mVisitedNode.equals(t))
                break;

            final Collection<? extends Node> neighbors = mVisitedNode.getNeighbors();
            for (Iterator<? extends Node> i = neighbors.iterator(); i.hasNext(); ) {
                Node neighbor = i.next();
                if (!mVisited.contains(neighbor) && !mToVisit.contains(neighbor)){
                    mToVisit.add(s);
                }

            }
            radius++;
        }
        mShortestPath.add(t);
        /**
         * Backtracks from t through the list of visited nodes and their distances, until
         * a shortest path to node s is generated. After the path to s is generated, s is added.
         */

        for (int i = radius - 1; i > 0; radius--) {
            for (int j = 0; j < mNodeDistances.size(); j++) {
                if (mNodeDistances.get(j).getDistance() == i) {
                    mShortestPath.add(mNodeDistances.get(j).getNodeWithDistance());
                    break;
                }
            }
        }
        mShortestPath.add(s);
        return mShortestPath;
    }
}

