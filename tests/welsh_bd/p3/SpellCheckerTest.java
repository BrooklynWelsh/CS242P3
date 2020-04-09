package welsh_bd.p3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class SpellCheckerTest {

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
	
	@Test
	void testSpellChecker() {
		List<String> lexicon = processFile("resources\\lexicon.txt");
		SpellChecker check = new SpellChecker(lexicon);
		
		assertEquals(Character.MIN_VALUE, check.root.character);		// Test that root character was initialized correctly
	
	}

	@Test
	void testSpelledCorrectly() {
		fail("Not yet implemented");
	}

	@Test
	void testSuggestWords() {
		fail("Not yet implemented");
	}

	@Test
	void testEditDistance() {
		fail("Not yet implemented");
	}

	@Test
	void testSuggestWordsTest() {
		fail("Not yet implemented");
	}

}