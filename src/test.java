import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class test {
	static Scanner actorScanner;

	public static void main(String args[]) {

		try {
			IMDBGraph graph = new IMDBGraph("C:\\Users\\relat\\IMBD\\src\\actors_test.list",
					"C:\\Users\\relat\\IMBD\\src\\actresses_test.list");
		}catch(
				IOException e)
		{

		}


	}
}