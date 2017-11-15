import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class test {
	static IMDBActorsGraph aGraph;
	 IMDBMoviesGraph mGraph;

	public static void main(String args[]) {

		try {
			IMDBActorsGraph aGraph = new IMDBActorsGraph("C:\\Users\\relat\\IMDB\\src\\actors_test.list",
					"C:\\Users\\relat\\IMBD\\src\\actresses_test.list");
			IMDBMoviesGraph mGraph = new IMDBMoviesGraph("C:\\Users\\relat\\IMDB\\src\\actors_test.list",
					"C:\\Users\\relat\\IMBD\\src\\actresses_test.list");

			System.out.println(aGraph.getNodeByName("Actor1").getName());
			System.out.println(aGraph.getNodeByName("Actress2").getName());
			mGraph.getNodeByName("Movie1 (2001)").getNeighbors();

			for (Iterator<? extends Node> i = mGraph.getNodeByName("Movie2 (2002)").getNeighbors().iterator(); i.hasNext(); ) {
				Node neighbor = i.next();
				System.out.println(neighbor.getName());
			}


		}catch(
				IOException e)
		{

		}

	}
}