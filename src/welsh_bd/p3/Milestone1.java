package welsh_bd.p3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Milestone1 {
	public static void main(String[] args) {
		String word1 = "";
		String word2 = "";
		Scanner in = new Scanner(System.in);
		
		// First let's read in the lexicon.txt file and turn it into a List<String>
		//ArrayList<String> lexicon = new ArrayList<String>();
		
		//SpellChecker check = new SpellChecker(lexicon);
		
		//lexicon = processFile("resources\\lexicon.txt");
		
		while(!word1.equals("q") && !word2.equals("q")) {
			System.out.println("Please enter the first word: ");
			word1 = in.nextLine();
			
			System.out.println("Please enter the second word: ");
			word2 = in.nextLine();
			
			
			System.out.println("Here's the Levenstein Distance for " + word1 + " and " + word2 + " : " + SpellChecker.editDistance(word1, word2));
			
			
		}
	}
	
	public static ArrayList<String> processFile(String filename) {
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
