
public class BinaryTree{
    private class Node{
        private int value;
        private Node rightChild;
        private Node leftChild;

        public Node(int value){
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value){
        Node current = root;

        if(root == null) {
            root = new Node(value);
            return;
        }

        while(true){    
            if(value > current.value){
                if (current.rightChild == null){
                    current.rightChild = new Node(value);
                    break;
                }
                current = current.rightChild;
            }
            else{
                if(current.leftChild == null){
                    current.leftChild = new Node(value);
                    break;
                }
                current = current.leftChild;
            }
        }
    }

    public boolean Find(int value){
        Node current = root;
        
        if(current == null) return false;

        while(current.value != value){    
            if (value > current.value)
                current = current.rightChild;
            else
                current = current.leftChild;
            if(current == null) return false;
        }

        return true;
    }


    public void traversePreOrder(){
        traversePreOrder(root);
    }

    private void traversePreOrder(Node relativeRoot){
        if (relativeRoot == null) return;

        System.out.println(relativeRoot.value);
        traversePreOrder(relativeRoot.leftChild);
        traversePreOrder(relativeRoot.rightChild);
    }

    public void traverseInOrder(){
        traverseInOrder(root);
    }

    private void traverseInOrder(Node relativeRoot){
        if (relativeRoot.leftChild != null){
            traverseInOrder(relativeRoot.leftChild);
        }
        
        System.out.println(relativeRoot.value);

        if(relativeRoot.rightChild != null)
            traverseInOrder(relativeRoot.rightChild);
        else return;
    }
}