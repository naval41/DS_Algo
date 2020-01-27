import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvanceSearchTrie {

	public static void main(String[] args) {
		
		Trie trieDS=new Trie();
		trieDS.insertWord("Dog");
		trieDS.insertWord("Deer");
		trieDS.insertWord("Deal");
		
		System.out.println(trieDS.advanceSearch("De"));
	}

}

class TrieNode {
	Map<Character, TrieNode> children;
	boolean endOfWord;

	TrieNode() {
		children = new HashMap<Character, TrieNode>();
		endOfWord = false;
	}
}

class Trie {
	
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insertWord(String s) {

		TrieNode current = root;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			TrieNode node = current.children.get(ch);
			if (node == null) {
				node = new TrieNode();
				current.children.put(ch, node);
			}
			current = node;
		}
		current.endOfWord = true;
	}
	
	public List<String> advanceSearch(String prefix){
		List<String> autoCompWords=new ArrayList<String>();
		
		TrieNode currentNode=root;
		
		for(int i=0;i<prefix.length();i++) {
			currentNode=currentNode.children.get(prefix.charAt(i));
			if(currentNode==null) return autoCompWords;	
		}
		
		searchWords(currentNode,autoCompWords,prefix);
		return autoCompWords;
	}

	private void searchWords(TrieNode currentNode, List<String> autoCompWords, String word) {
		
		if(currentNode==null) return;
		
		if(currentNode.endOfWord) {
			autoCompWords.add(word);
		}
		
		Map<Character,TrieNode> map=currentNode.children;
		for(Character c:map.keySet()) {
			searchWords(map.get(c),autoCompWords, word+String.valueOf(c));
		}
		
	}

}
