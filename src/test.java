import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class test {
	static Scanner actorScanner;

	public static void main(String args[]) {

		/*
		try {
			IMDBGraph graph = new IMDBGraph("C:\\Users\\relat\\IMBD\\src\\actors_test.list",
					"C:\\Users\\relat\\IMBD\\src\\actresses_test.list");
		}catch(
				IOException e)
		{

		}
		*/



		/*
		try {
			actorScanner = new Scanner(new File("C:\\Users\\relat\\Downloads\\actors.list"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for(int i = 0; i < 1000; i++) {
			System.out.println(actorScanner.nextLine());
		}
		*/

		try {
			IMDBGraph graph = new IMDBGraph("C:\\Users\\relat\\IMDB\\src\\actors_first_10000_lines.list",
					"C:\\Users\\relat\\IMBD\\src\\actresses_test.list");
		}catch(
				IOException e)
		{

		}

	}
}