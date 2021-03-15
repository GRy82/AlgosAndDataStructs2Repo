public class Trie {
    
    private class Node{
        public  int ALPHABET_SIZE = 26;
        private boolean endOfWord;
        private Node[] children = new Node[ALPHABET_SIZE];
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
            int index = ch - 'a';
            if(current.children[index] == null)
                current.children[index] = new Node(ch);
            current = current.children[index];
        }
        current.endOfWord = true;
    }
}
