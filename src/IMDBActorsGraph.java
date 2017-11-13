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
		initActors();
		readActors();
		
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
				System.out.println(actorScanner.nextLine());
			} else {
				break;
			}
		}
		
		for(int i = 0; i<5; i++){
			System.out.println(actorScanner.nextLine());
		}
		
	}

	private void parseActors(){
		StringTokenizer actorTokenizer;
		String actorString;
		ArrayList<MovieNode> movieNodes;
		while(actorScanner.hasNext()){
			actorString = actorScanner.nextLine();
			
			int counter = 0;
			if(!actorString.startsWith("/t")){
				actorTokenizer = new StringTokenizer(actorString, "/t");
				actorNodes.add(new ActorNode(actorTokenizer.nextToken(); null));
				counter++;
				while(actorString.startsWith("/t")){
					
				}
				
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
