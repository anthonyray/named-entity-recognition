import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
	 * @throws IOException 
	 */	
	public static List<Mention> findMentions(File wikipediaCorpus, Trie dictionary) throws IOException {
		/*
		 * 1. Parse the Wikipedia corpus : Ok ! 
		 * 2. For each article, scan the content of the article for entities.
		 * 	2.1 Split the content of the article in sentences, to limit the scope of an entity scanning to a sentence.
		 * 	2.2 For each entity encountered, instantiate a mention object and add it to the list of mentions
		 */
		
		Parser wikiParser = new Parser(wikipediaCorpus);  
		List<Mention> mentions = new ArrayList<Mention>();
		
		while (wikiParser.hasNext()){
			Page article = wikiParser.next();  // Retrieves the current article
			String content = article.content;
			String title = article.title;
			
			StringTokenizer sentence = new StringTokenizer(content, ".\t");
			while (sentence.hasMoreTokens()){
				String str = sentence.nextToken();
				System.out.println(str);
				addNextMention(str,dictionary);
			}

		}
		
		wikiParser.close(); 
		
		return mentions;
	}
	
	public static void addNextMention(String str, Trie dictionary){
		System.out.println(str);
		String entity = dictionary.longestEntityContained(str,0); // Initialisation
		if (entity != null){ // We found an entity
			// Let's remove the entity from the sequence
			int idx = str.indexOf(entity);
			addNextMention(str.substring(0, idx),dictionary);
			addNextMention(str.substring(idx + entity.length(),str.length()-1),dictionary);
		}
	}

	public static void main(String args[]) throws IOException {
		/*Trie dictionary = new Trie(new File(args[1]));
		
		for (Mention mention : findMentions(new File(args[0]), dictionary)) {
			System.out.println(mention);
		} */	
		Trie dictionary = new Trie(new File("entities.txt"));
		findMentions(new File("text"), dictionary);
		
		/*for (Mention mention : findMentions(new File("text"), dictionary)) {
			System.out.println(mention);
		}*/
		
	}
}
