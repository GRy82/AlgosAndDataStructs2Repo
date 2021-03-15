import java.util.HashMap;

public class Trie {
    
    private class Node{
        public  int ALPHABET_SIZE = 26;
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
    }
    private Node root = new Node(' ');
    
    public void insert(String word){
        Node current = root;
        for(var ch : word.toCharArray()){
            if(current.children.get(ch) == null)
                current.children.put(ch, new Node(ch));
            current = current.children.get(ch);
        }
        current.endOfWord = true;
    }
}
