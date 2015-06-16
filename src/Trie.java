import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * It defines the methods that define a Trie data structure.
 * @author Luis Galárraga.
 *
 */
public class Trie {
	
	char letter;
	Map<Character,Trie> children = new HashMap<Character,Trie>();
	boolean isWord;
	
	/**
	 * Adds a string to the trie.
	 * @param  
	 * @return true if the trie changed as a result of this operation, that is if
	 * the new string was not in the dictionary.
	 */
	
	public boolean setIsWord(boolean isword){
		isWord = isword;
		return isWord;
	}
	
	public char setLetter(char letter){
		this.letter = letter;
		return letter;
	}
	
	public boolean add(String str) {
		char firstCharacter = str.charAt(0); // Retrieves the first character of the string
		int strSize = str.length();
		
		if (strSize > 1){ // If there are remaining characters
			if (children.get(firstCharacter) == null){ // If there is a children, call the add method on the child, with the substring 
				Trie child = new Trie();
				child.setLetter(firstCharacter);
				addChild(firstCharacter,child);
			}			
			return children.get(firstCharacter).add(str.substring(1));
			
		}
		else { // There is one character left, 
			if (children.get(firstCharacter) == null){ // if it's not in the children, add it and set last trie isWord to True
				Trie lastChild = new Trie();
				lastChild.setLetter(firstCharacter);
				lastChild.setIsWord(true);
				addChild(firstCharacter,lastChild);
				return true;
			}
			else { // If the last character is in the children, do nothing.
				children.get(firstCharacter).setIsWord(true);
				return false;
			}
		}
	}
	
	private Trie addChild(char letter, Trie t){
		children.put(letter,t);
		return t;
	}
	
	/**
	 * Checks whether a string exists in the trie.
	 * @param str
	 * @return true if the string is in the trie, false otherwise. 
	 */
	public boolean contains(String str) {
		if (str.length() > 0){
			char firstCharacter = str.charAt(0);
			if (children.get(firstCharacter) != null){
				return children.get(firstCharacter).contains(str.substring(1));
			}
			else {
				return false;
			}
			
		}
		else {
			if (isWord){
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	/**
	 * Given an input text and a start position, it returns the length of the longest word in the trie 
	 * occurring from <var>startPos</var> in the input text. 
	 * For example, if the trie contains words "York" and "New York", containedLength("I live in New York!", 10)
	 * returns 8, that is, the length of the word "New York" because this is the longest word registered in the trie 
	 * starting at position 10 in the input text.
	 * @param s
	 * @param startPos
	 * @return int
	 */
	public int containedLength(String s, int startPos) {
	/*
	 * We start at startPos, and iterate until we reach the end of the string
	 */
		int maximumLength = -1;
		
		for (int i = startPos ; i < s.length() ; i++){
		/*
		 * For each substring i, we try check if every combination is contained in the Trie
		 */
			for (int j = 0 ; j <= s.length() - i ; j++ ){
				String combination = s.substring(i, i + j );
				if (this.contains(combination)){ // If we found a combination, add it to the counting hashmap 
					if (maximumLength < combination.length()){
						maximumLength = combination.length();
					}
				}
			}
			
		}
		
		return maximumLength;
	}
	
	public String longestEntityContained(String s, int startPos) {
	/*
	 * We start at startPos, and iterate until we reach the end of the string
	 */
		int maximumLength = -1;
		String longestEntity = null;
		
		for (int i = startPos ; i < s.length() ; i++){
		/*
		 * For each substring i, we try check if every combination is contained in the Trie
		 */
			for (int j = 0 ; j <= s.length() - i ; j++ ){
				String combination = s.substring(i, i + j );
				if (this.contains(combination)){ // If we found a combination, add it to the counting hashmap 
					if (maximumLength < combination.length()){
						maximumLength = combination.length();
						longestEntity = combination;
					}
				}
			}
			
		}
		
		return longestEntity;
	}
	
	/** Constructs a Trie from the lines of a file*/
	public Trie(File file) throws IOException {
		try(BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"))) {
			String line;
			while((line=in.readLine())!=null) {
				add(line);
			}
		}
	}
	
	/** Constructs an empty Trie*/
	public Trie() {
		this.letter = '\0';
		this.isWord = false;
		this.children = new HashMap<Character,Trie>();
		
	}

	/** Constructs a Trie from a collection*/
	public Trie(Iterable<String> collection) {
		for(String s : collection) add(s);
	}
	
	/** Use this to test your implementation. Provide the file with list of Wikipedia titles as argument to this program.*/ 
	public static void main(String[] args) throws IOException {		
		Trie trie = new Trie(new File("entities.txt"));
		
		for(String test : Arrays.asList("Brooklyn","Dudweiler","Elvis Presley","Juan Pihuave","York")) {
			System.out.println(test + " is in trie: " + trie.contains(test));
		}
		
		System.out.println(trie.longestEntityContained("I love to see Alan Turing in New York City. ",7));
		
	}
}