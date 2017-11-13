import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class test {
	static Scanner actorScanner;

	public static void main(String args[]) {
		// File actorsList = new File("src\\actors_test.list");
		//
		// try {
		// actorScanner = new Scanner(actorsList, "ISO-8859-1");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// actorScanner.findInLine("THE ACTORS LIST");
		// while (actorScanner.hasNext()) {
		// if (!actorScanner.nextLine().contains("THE ACTORS LIST")) {
		// System.out.println(actorScanner.nextLine());
		// } else {
		// break;
		// }
		// }
		// System.out.println(actorScanner.nextLine());
		 try
		
		 {
		 IMDBActorsGraph graph = new IMDBActorsGraph("src\\actors_test.list",
		 "");
		 }catch(
		 IOException e)
		 {
		
		 }
	}
}