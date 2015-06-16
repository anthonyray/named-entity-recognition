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
	public boolean add(String str) {
		char firstCharacter = str.charAt(0); // Retrieves the first character of the string
		int strSize = str.length();
		
		if (strSize > 1){ // If there are remaining characters
			if (children.get(firstCharacter) != null){ // If there is a children, call the add method on the child, with the substring 
				children.get(firstCharacter).add(s.substring(1));
			}
			else { // If there is no child
				Trie child = new Trie();
				child.setLetter(firstChar);
				addChild(firstCharIndex,child);
			}
			
			children[firstCharIndex].add(str.substring(1));
			
		}
		else { // 
			if 
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
		throw new UnsupportedOperationException("The method Trie.contains has not been implemented.");		
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
		throw new UnsupportedOperationException("The method Trie.containedLength has not been implemented.");
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
		this.letter = (Character) null;
		this.isWord = false;
		this.children = new Trie[26];
		
	}

	/** Constructs a Trie from a collection*/
	public Trie(Iterable<String> collection) {
		for(String s : collection) add(s);
	}
	
	/** Use this to test your implementation. Provide the file with list of Wikipedia titles as argument to this program.*/ 
	public static void main(String[] args) throws IOException {		
		Trie trie = new Trie(new File(args[0]));
		
		for(String test : Arrays.asList("Brooklyn","Dudweiler","Elvis Presley","Juan Pihuave")) {
			System.out.println(test + " is in trie: " + trie.contains(test));
		}

	}
}