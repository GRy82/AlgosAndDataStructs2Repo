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
            if(current.hasChild(ch))
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
}
