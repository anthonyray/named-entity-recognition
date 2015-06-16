
public class Node {
	char letter;
	Node[] children; 
	boolean isWord;

	public Node(char letter){
		this.letter = letter;
		this.isWord = false;
		this.children = new Node[26]; // TODO : implement the case with the space character
	}
	
	public char getLetter(){
		return letter;
	}
	
	public boolean isWord(){
		return isWord;
	}
	
	public boolean setIsWord(boolean bool){
		this.isWord = bool;
		return isWord;
	}
	
		
}
