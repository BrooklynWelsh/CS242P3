package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import spellchecker.SpellChecker;

/**
 * This class just tests whether your code will compile in my JUnit tests.
 * This class DOES NOT actually test your code.  It just ensures that it compiles.
 * @author Joe Meehean
 *
 */
class CompilationStub {
	public static void main(String[] args) {
		int distance = SpellChecker.editDistance("a", "b");
			
		List<String> aList = new ArrayList<String>();
		List<String> bList = new LinkedList<String>();
			
		SpellChecker checker = new SpellChecker(aList);
		checker = new SpellChecker(bList);
		boolean spelledCorrectly = checker.spelledCorrectly("word");
		List<String> suggestions = checker.suggestWords("word", 3);
	}
}
