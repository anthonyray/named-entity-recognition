import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class implements a entity recognizer based on dictionary of 
 * entities.
 */
public class EntityRecognizer {

	/**
	 * Given as arguments (1) the Wikipedia corpus and (2) a dictionary of entities, 
	 * it returns a list with ALL the mentions of entities in the dictionary occurring in the text of the articles. 
	 * For instance if the article about Barack Obama mentions Michelle Obama, assuming she is in the dictionary,
	 * the function generates a mention <Michelle Obama, Barack Obama>.
	 */	
	public static List<Mention> findMentions(File wikipediaCorpus, Trie dictionary) {
		return null;
	}


	public static void main(String args[]) throws IOException {
		Trie dictionary = new Trie(new File(args[1]));
		for (Mention mention : findMentions(new File(args[0]), dictionary)) {
			System.out.println(mention);
		}		
	}
}
