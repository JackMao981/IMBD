import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

/**
 * Code to test Project 3; you should definitely add more tests!
 */
public class GraphPartialTester {
	Graph actorsGraph, moviesGraph;
	GraphSearchEngine searchEngine;

	@Test(timeout=5000)
	/**
	 * Verifies that there is no shortest path between a specific and actor and actress.
	 */
	public void findShortestPathNoPath () {
		final Node actor1 = actorsGraph.getNodeByName("Actor1");
		final Node actress2 = actorsGraph.getNodeByName("Actress2");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actress2);
		assertNull(shortestPath);  // there is no path between these people
	}

	@Test(timeout=5000)
	/**
	 * Verifies that there is a shortest path between Actor1 and Actress1.
	 */
	public void findShortestPathLenghtOneMovieV1 () {
		final Node actor1 = actorsGraph.getNodeByName("Actor1");
		final Node actress1 = actorsGraph.getNodeByName("Actress1");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor1, actress1);
		assertEquals(shortestPath.size(), 3);  // there is no path between these people
	}

	@Test(timeout=50000)
	/**
	 * Verifies that there is a shortest path between Actor4 and Actor6.
	 */
	public void findShortestPathLenghtOneMovieV2 () {
		final Node actor4 = actorsGraph.getNodeByName("Actor4");
		final Node actor6 = actorsGraph.getNodeByName("Actor6");
		final List<Node> shortestPath = searchEngine.findShortestPath(actor4, actor6);
		assertEquals(shortestPath.size(), 5);  // there is no path between these people
	}

	@Before
	/**
	 * Instantiates the actors and movies graphs
	 */
	public void setUp () throws IOException {
		actorsGraph = new IMDBActorsGraph("C:\\Users\\relat\\IMDB\\src\\orgActors", "C:\\Users\\relat\\IMDB\\src\\orgActresses");
		moviesGraph = new IMDBMoviesGraph("C:\\Users\\relat\\IMDB\\src\\orgActors", "C:\\Users\\relat\\IMDB\\src\\orgActresses");
		searchEngine = new GraphSearchEngineImpl();
	}

	@Test
	/**
	 * Just verifies that the graphs could be instantiated without crashing.
	 */
	public void finishedLoading () {
		assertTrue(true);
		// Yay! We didn't crash
	}

	@Test
	/**
	 * Verifies that a specific movie has been parsed.
	 */
	public void testSpecificMovie () {
		testFindNode(moviesGraph, "Movie1 (2001)");
	}

	@Test
	/**
	 * Verifies that a specific actress has been parsed.
	 */
	public void testSpecificActress () {
		testFindNode(actorsGraph, "Actress2");
	}

	/**
	 * Verifies that the specific graph contains a node with the specified name
	 * @param graph the Graph to search for the node
	 * @param name the name of the Node
	 */
	private static void testFindNode (Graph graph, String name) {
		final Collection<? extends Node> nodes = graph.getNodes();
		boolean found = false;
		for (Node node : nodes) {
			if (node.getName().trim().equals(name)) {
				found = true;
			}
		}
		assertTrue(found);
	}
}
