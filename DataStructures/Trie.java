/* Trie
 ** |w|
 */

//START
public static boolean insert(TrieNode root, String word){
    char[] s = word.toCharArray();
    TrieNode node = root;
    
    for(int i = 0; i < s.length; ++i){
        int index = charToIndex(s[i]);
        if(node.children[index] == null){
            node.children[index] = new TrieNode(node);
        }
        node = node.children[index];
    }
    node.isEnd = true;
    
    return true;
}

public static boolean search(TrieNode root, String word){
    char[] s = word.toCharArray();
    TrieNode node = root;
    
    for(int i = 0; i < s.length; ++i){
        int index = charToIndex(s[i]);
        if(node.children[index] == null){
            return false;
        }
        node = node.children[index];
    }
    
    return node.isEnd;
}

public static int charToIndex(char c){
    return ((int) c - (int) 'a');
}

static class TrieNode{
    
    boolean isEnd;
    TrieNode[] children;
    
    public TrieNode(){
        isEnd = false;
        children = new TrieNode[26];
    }
}
//END