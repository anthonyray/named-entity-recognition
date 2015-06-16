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
			/*
			 * VERY HEAVY IMPLEMENTATION. It should harness the power of the Trie datastructure.
			 * 
			 * 			for (int i = 0 ; i < content.length() ; i++){
							String entity = dictionary.longestEntityContained(content,i);
							if (entity != null){
								Mention mention = new Mention(title,entity);
								if (!mentions.contains(mention)){
									mentions.add(mention);
									System.out.println("Added : " + mention.toString());
								}
							}
							
						}
			 */
			String content = article.content;
			String title = article.title;
			
			StringTokenizer sentence = new StringTokenizer(content, ".\t");
			while (sentence.hasMoreTokens()){
				String str = sentence.nextToken();
				for (int i = 0 ; i < str.length() ; i++){
					String entity = dictionary.longestEntityContained(str,i);
					if (entity != null){
						Mention mention = new Mention(title,entity);
						if (!mentions.contains(mention)){
							mentions.add(mention);
						}
					}
					
				}
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
