/**
 * ConsoleInterface: gives a user console to test the capabilities of suggesting correctly spelled words
 * @author Brooklyn Welsh
 */

package spellchecker;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
	
	// Private helper function that converts lexicon .txt file into a list
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
	
	public static void main(String[] args) {
		int maxDistance = 2;	// Default distance to 2
		if(args.length == 1) {	// If user defined a distance argument...
			try{				// First make sure that first arg is a number and not char or string with a try catch block
				Integer.parseInt(args[0]);
			}
			catch(NumberFormatException e){
				System.out.println("ERROR: maxEditDistance argument is NaN. \nUSAGE: spellchecker <maxEditDistance>");
				System.exit(-1);
			}
			maxDistance = Integer.valueOf(args[0]);	// Read first arg and set maxDistance
		}
		else if(args.length > 1) {	// If too many args print error message
			System.out.println("ERROR: Too many args. \nUSAGE: spellchecker <maxEditDistance>");
			System.exit(-1);
		}
		
		Scanner in = new Scanner(System.in);
		String word;
		
		// Convert lexicon to list and setup the spellchecker
		List<String> lexicon = processFile("resources\\lexicon.txt"); 
		SpellChecker check = new SpellChecker(lexicon);
		
		while(true) {
			System.out.println("Enter a word to spell check (enter \"0\" to exit): ");
			word = in.nextLine();
			
			if(String.valueOf(word).equals("0")) break;	// Couldn't make loop break immediately without break keyword sorry!
			
			if(check.spelledCorrectly(word)) System.out.println("Spelled correctly.");	// Check if word is spelled correctly and we can skip checking trie
			else{
				List<String> suggestions = check.suggestWords(word, maxDistance);
				if(suggestions.size() == 0) System.out.println("No suggestions.");
				else {
					for(String suggestion: suggestions) {
						System.out.println(suggestion);	// Print each suggestion on new line
					}
				}
			}
		}
		in.close();
	}
}
