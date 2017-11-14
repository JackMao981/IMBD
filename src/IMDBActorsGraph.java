import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IMDBActorsGraph implements Graph {
	private Scanner actorScanner;
	private Scanner actressScanner;
	private ArrayList<ActorNode> actorNodes;
	private String actorsFilename;
	private String actressesFilename;

	/**
	 * Constructor
	 */
	public IMDBActorsGraph(String actorsFilename, String actressesFilename) throws IOException {
		this.actorsFilename = actorsFilename;
		this.actressesFilename = actressesFilename;
		actorNodes = new ArrayList<ActorNode>();
		initActors();
		readActors();
		parseActors();

	}

	/**
	 * Instantiates actorScanner to read the actors_test.list file
	 */
	private void initActors() {
		File actorsList = new File(actorsFilename);

		try {
			actorScanner = new Scanner(actorsList, "ISO-8859-1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readActors() {

		// brings the scanner to the line that says "THE ACTORS LIST"
		while (actorScanner.hasNext()) {
			if (!actorScanner.nextLine().contains("THE ACTORS LIST")) {
				actorScanner.nextLine();
			} else {
				break;
			}
		}

		for (int i = 0; i < 5; i++) {
			actorScanner.nextLine();
		}

	}

	private void parseActors() {
		StringTokenizer actorTokenizer;
		String actorString;
		ArrayList<MovieNode> movieNodes;
		String movieString;
		String movieName;
		String actorName;
		int counter = 0;
		while (actorScanner.hasNext()) {
			actorString = actorScanner.nextLine();

			// DELETE NEXT LINE
			System.out.println(actorString + "\t" + counter);

			if (!actorString.startsWith("\t")) {
				actorTokenizer = new StringTokenizer(actorString, "\t");
				actorName = actorTokenizer.nextToken();
				System.out.println(actorName);

				actorNodes.add(new ActorNode(actorName, new ArrayList<MovieNode>()));

				movieName = actorTokenizer.nextToken();

				// checks if the movie is a TV or TV movie
				if (!movieName.contains("\"") && !actorString.contains("(TV)")) {
					actorNodes.get(counter).addNeighbor(new MovieNode(movieName, new ArrayList<ActorNode>()));
				}

				// checks indented movies
				while (actorString.startsWith("\t")) {
					movieString = actorScanner.nextLine();
					if (!movieString.contains("\"") && !actorString.contains("(TV)")) {
						actorNodes.get(counter).addNeighbor(new MovieNode(movieString, new ArrayList<ActorNode>()));
					}
				}
				if (actorNodes.get(counter).fNeighbors.size() > 0) {
					// EVENTUALLY DELETE
					System.out.print(actorNodes.get(counter).fName);
					for (int i = 0; i < actorNodes.get(counter).fNeighbors.size(); i++) {
						System.out.print(
								"\t" + ((ArrayList<MovieNode>) actorNodes.get(counter).fNeighbors).get(i).getName());
					}
					System.out.println();

					// DON'T DELETE
					counter++;
				} else {
					actorNodes.remove(counter);
				}
			}
			if (actorScanner.hasNextLine()) {
				actorScanner.nextLine();
			}
		}
	}

	@Override
	public Collection<? extends Node> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNodeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
