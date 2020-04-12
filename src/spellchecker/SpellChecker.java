package spellchecker;

import java.util.ArrayList;
import java.util.List;

public class SpellChecker {
	public static class TrieNode
	{
		final int ALPHABET_SIZE = 26;
		char character;										// Char represented by this TrieNode	
		TrieNode[] children = new TrieNode[ALPHABET_SIZE]; 	// Array to contain references to all 26 possible children
		boolean validWord;									// Does this node indicate the end of a correctly spelled word
		
		public TrieNode(char character) {
			validWord = false;
			this.character = Character.toUpperCase(character);
		}
		
		private void insert(String word, TrieNode root) {
			TrieNode node = root;
			for(int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				
				if((node.children[(int)currentChar - (int)'A'] != null)) {	// If the children array already has a node for currentChar
					node = node.children[(int)currentChar - (int)'A'];		// Just set node to that one and continue the loop
				}
				else {	// Else if the children array doesn't have a node for current char...
					TrieNode newNode = new TrieNode(currentChar);				// Create one
					if(i == word.length() - 1) newNode.validWord = true;			// If this is last char in word, this node is a leaf node
					node.children[(int)currentChar - (int)'A'] = newNode;			// Put it in the appropriate spot in the array
					node = newNode;												// set node to newNode so we continue crawling the trie
				}
			}
		}
		
		private boolean containsKey(TrieNode[] children, char c) {
			// Return true if a child array contains a non-null reference at for c
			return children[(int)c - (int)'A'] != null;
		}
		
		private TrieNode get(String word) {
			return get(this, word, 0);
		}
		
		private TrieNode get(TrieNode node, String word, int counter) {
			// Counter will be used to tell if we've reached the last char of word
			if(counter == word.length()) return node;	// If at end of word return that node
			char thisChar = word.charAt(counter);		// Else, get the char at current spot and keep traversing
			if(node.children[(int)thisChar - (int)'A'] == null) return null;
			return get(node.children[(int)thisChar - (int)'A'], word, counter + 1);
		}
	}
	
	// Private helper functions for inner class
	private void createTrie() {
		root = new TrieNode(Character.MIN_VALUE);
		
		for(String word : lexicon) {
			root.insert(word.toUpperCase(), root);
		}
	}
	
	
	// Public SpellChecker functions
	public SpellChecker (List<String> lexicon) {
		this.lexicon = lexicon;
		createTrie();
	}
	
	public boolean spelledCorrectly(String word) {
		String upperWord = word.toUpperCase();	// All words/chars are converted to upperCase so Trie math is easier
		TrieNode endNode = root.get(upperWord);
		return (endNode != null) && endNode.validWord;
	}
	
	public List<String> suggestWords(String word, int maxEditDistance){
		List<String> suggestedWords= new ArrayList();	// Create list to contain suggested words
		if(spelledCorrectly(word)) {					// If word is already correct, just add word then return
			suggestedWords.add(word);
		}
		else {											// Else traverse trie looking for words
			
		}
		
		return suggestedWords;
	}
	
	public static int editDistance(String s1, String s2) {
		int s1Length = s1.length();
		int s2Length = s2.length();
		// Create the 2D array to store edit distances
		int M[][] = new int[s1Length + 1][s2Length + 1];
		
		// Start filling in the table
		// First, the base cases
		M[0][0] = 0;		// Empty strings
		
		for(int i = 1; i <= s1Length; i++) {
			M[i][0] = i;	// s1 compared to empty string
		}
		
		for(int j = 0; j <= s2Length; j++) {
			M[0][j] = j;	// s2 compared to empty string
		}
		
		// Now fill in table left-to-right top-to-bottom
		// j goes in outer loop so we go in correct order
		for(int j = 1; j <= s2Length; j++) {
			for(int i = 1; i <= s1Length; i++) {
				// Take the min of the following 3 cases: m[i-1][j] + 1 || m[i][j-1] + 1 || m[i-1][j-1] + ?
				// ? = 0 if s1[i-1] == s2[j-1], ? = 1 if not equal
				
				int min = M[i-1][j] + 1;										// 1st case
				if(M[i][j-1] + 1 < min) min = M[i][j-1] + 1;						// 2nd case
				
				int add;													// For 3rd case, first figure out whether to add 1 or 0
				if(Character.toUpperCase(s1.charAt(i-1)) == Character.toUpperCase(s2.charAt(j-1)))	add = 0;
				else									add = 1;
				
				if(M[i-1][j-1] + add < min)		min = M[i-1][j-1] + add;	// Now check if 3rd case is the min
				
				M[i][j] = min;												// Now update table
			}
		}
		
		return M[s1Length][s2Length];									// Return last entry in table which is the Levenshtein Distance
	}
	
	public static List<String> suggestWordsTest(String word, int maxEditDistance, List<String> lexicon){
		
	}
	
	public List<String> lexicon;
	public TrieNode root;
}
