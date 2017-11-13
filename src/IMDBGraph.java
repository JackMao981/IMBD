import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class IMDBGraph implements Graph{
    private HashMap<String, GraphNode> fMap;

    /**
     * Constructs a IMDB Graph.
     */
    public IMDBGraph(){
        fMap = new HashMap<String, GraphNode>();
    }

    /**
     * Retrives a specified node from a graph.
     * @param key they desired node's key.
     * @return the desired node.
     */
    public GraphNode getNodeByName(String key){
      return fMap.get(key);
    }

    /**
     * Retrives all of the nodes in a collection.
     * @return the nodes stored in a collection.//
     */
    public Collection<GraphNode> getNodes() {
        return fMap.values()
    }

    /**
     * Adds a node to the collection.
     * @param aNode the node to add.
     */
    //if it fucks up it's because it should be GraphNode
    //-JSS from the past
    public void addNode(Node aNode) {
        fMap.put(aNode.getName(), aNode);
    }





}
