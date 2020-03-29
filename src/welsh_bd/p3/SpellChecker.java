package welsh_bd.p3;

import java.util.List;

public class SpellChecker {
	public SpellChecker (List<String> lexicon) {
		this.lexicon = lexicon;
	}
	
	public boolean spelledCorrectly(String word) {
		
	}
	
	public List<String> suggestWords(String word, int maxEditDistance){
		
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
				if(s1.charAt(i-1) == s2.charAt(j-1))	add = 0;
				else									add = 1;
				
				if(M[i-1][j-1] + add < min)		min = M[i-1][j-1] + add;	// Now check if 3rd case is the min
				
				M[i][j] = min;												// Now update table
			}
		}
		
		return M[s1Length][s2Length];									// Return last entry in table which is the Levenshtein Distance
	}
	
	public static List<String> suggestWordsTest(String word, int maxEditDistance, List<String> lexicon){
		
	}
	
	List<String> lexicon;
}