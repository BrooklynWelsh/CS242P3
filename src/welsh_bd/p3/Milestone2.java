package welsh_bd.p3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Milestone2 {

	public static void main(String[] args) {
		
		
		// First let's read in the lexicon.txt file and turn it into a List<String>
		ArrayList<String> lexicon = new ArrayList<String>();
		lexicon = processFile("resources\\lexicon.txt");
		
		SpellChecker check = new SpellChecker(lexicon);
				
	}
	
	private static ArrayList<String> processFile(String filename) {
		ArrayList<String> lexicon = new ArrayList();
		try {
			Scanner in = new Scanner(Paths.get(filename));
			// While there are still songs...
			while(in.hasNextLine()) {
				lexicon.add(in.nextLine());
			}
			// Close the scanner
			in.close();
		} catch (IOException e) {	// Else, we probably gave the wrong file name. Print an error message and exit.
			System.out.println("There was an error processing the lexicon file. Exiting program.");
		}
		
		return lexicon;
	}

}
