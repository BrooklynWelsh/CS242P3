/**
 * Console interface for Milestone 1
 * @author Brooklyn Welsh
 */

package spellchecker;

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
		
		
		while(!word1.equals("q") && !word2.equals("q")) {
			System.out.println("Please enter the first word: ");
			word1 = in.nextLine();
			
			System.out.println("Please enter the second word: ");
			word2 = in.nextLine();
			
			
			System.out.println("Here's the Levenstein Distance for " + word1 + " and " + word2 + " : " + SpellChecker.editDistance(word1, word2));
			
			
		}
		in.close();
	}
}
