import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    
    private class Node{
        private boolean endOfWord;
        private HashMap<Character, Node> children = new HashMap<>();
        private char value;

        public Node(char value){
            this.value = value;
        }

        @Override
        public String toString(){
            return "value=" + value;
        }

        public boolean hasChild(char ch){
            return children.containsKey(ch);
        }

        public void addChild(char ch){
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch){
            return children.get(ch);
        }

        public Node[] getChildren(){
            return children.values().toArray(new Node[0]);
        }

        public boolean hasChildren(){
            return !children.isEmpty();
        }

        public void removeChild(char ch){
            children.remove(ch);
        }
    }
    private Node root = new Node(' ');
    
    public void insert(String word){
        Node current = root;
        for(var ch : word.toCharArray()){
            if(!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.endOfWord = true;
    }

    public boolean contains(String word){
        if (word == null) return false;
        Node current = root;
        for(var ch : word.toCharArray()){
            if(!current.hasChild(ch))
                return false;
            current = current.getChild(ch);      
        }

        return current.endOfWord;
    }

    public void traverse(){
        traverse(root);
    }

    private void traverse(Node root){
        for(var child : root.getChildren())
            traverse(child);
    }

    public void remove(String word){
        if(word == null) return;

        remove(root, word, 0);
    }

    private void remove(Node root, String word, int index){
        if(index == word.length()){
            root.endOfWord = false;
            return;
        }

        char ch = word.charAt(index);
        var child = root.getChild(ch);
            if (child == null)
                return;

        remove(child, word, index + 1);

        if(!child.hasChildren() && !child.endOfWord)
            root.removeChild(ch);
    }

    public List<String> findWords(String prefix){
        List<String> words = new ArrayList<>();
        var lastNode = findLastNodeOf(prefix);
        findWords(lastNode, prefix, words);

        return words;
    }

    private void findWords(Node root, String prefix, List<String> words){
        if (root == null) return;

        if (root.endOfWord)
            words.add(prefix);
        
        for(var child : root.getChildren())
            findWords(child, prefix + child.value, words);
    }

    private Node findLastNodeOf(String prefix){
        if (prefix == null) return null;

        var current = root;
        for(var ch: prefix.toCharArray()){
            var child = current.getChild(ch);
            if(child == null)
                return null;
            current = child;
        }
        return current;
    }

    public boolean containsRecursive(String word) {
        if (word == null)
            return false;
    
        return containsRecursive(root, word, 0);
    }
    
    private boolean containsRecursive(Node root, String word, int index) {
        // Base condition
        if (index == word.length())
            return root.endOfWord;
    
        if (root == null)
            return false;
    
        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if (child == null)
            return false;
    
        return containsRecursive(child, word, index + 1);
    }

    public int countWords(){
        return countWords(root, 0);
    }

    private int countWords(Node node, int count){
        if(node == null) return count;

        if (node.endOfWord)
            count++;

        for(var key : node.children.keySet())
            count = countWords(node.getChild(key), count);

        return count;
    }

    public int countWordsMosh() {
        return countWordsMosh(root);
    }
    
    private int countWordsMosh(Node root) {
        var total = 0;
    
        if (root.endOfWord)
          total++;
    
        for (var child : root.getChildren())
          total += countWordsMosh(child);
    
        return total;
    }
}
