import java.util.HashMap;

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
    }
    private Node root = new Node(' ');
    
    public void insert(String word){
        Node current = root;
        for(var ch : word.toCharArray()){
            if(current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.endOfWord = true;
    }
}
