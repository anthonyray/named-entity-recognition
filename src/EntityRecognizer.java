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
		 * 	
		 */
		
		Parser wikiParser = new Parser(wikipediaCorpus);  
		List<Mention> mentions = new ArrayList<Mention>();
		
		while (wikiParser.hasNext()){
			Page article = wikiParser.next();  // Retrieves the current article
			String content = article.content;
			String title = article.title;
		
			int cpt = 0; 
			while (cpt < content.length()){
				int entityLength = dictionary.containedLength(content, cpt);
				if (entityLength > 0){
					String entity = content.substring(cpt,cpt+entityLength);
					Mention mention = new Mention(title,entity);
					mentions.add(mention);
					
					cpt += entityLength; // We skip the entity  
				}
				cpt++;
			}

		}
		
		wikiParser.close(); 
		
		return mentions;
	}
	
	public static void main(String args[]) throws IOException {
		/*Trie dictionary = new Trie(new File(args[1]));
		
		for (Mention mention : findMentions(new File(args[0]), dictionary)) {
			System.out.println(mention);
		} */	
		Trie dictionary = new Trie(new File("entities.txt"));
		
		
		for (Mention mention : findMentions(new File("text"), dictionary)) {
			System.out.println(mention);
		}
		
	}
}
