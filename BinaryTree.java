import java.util.ArrayList;

public class BinaryTree {
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

    //-------------------------------------------------

    public void traversePreOrder(){
        traversePreOrder(root);
    }

    private void traversePreOrder(Node relativeRoot){
        if (relativeRoot == null) return;

        System.out.println(relativeRoot.value);
        traversePreOrder(relativeRoot.leftChild);
        traversePreOrder(relativeRoot.rightChild);
    }

    //-------------------------------------------------

    public void traverseInOrder(){
        traverseInOrder(root);
    }

    private void traverseInOrder(Node relativeRoot){
        if (relativeRoot.leftChild != null)
            traverseInOrder(relativeRoot.leftChild);
        
        System.out.println(relativeRoot.value);

        if(relativeRoot.rightChild != null)
            traverseInOrder(relativeRoot.rightChild);
        else return;
    }

    //-------------------------------------------------

    public void traversePostOrder(){
        traversePostOrder(root);
    }

    private void traversePostOrder(Node relativeRoot){
        if (relativeRoot.leftChild != null)
            traversePostOrder(relativeRoot.leftChild);
        
        if(relativeRoot.rightChild != null)
            traversePostOrder(relativeRoot.rightChild);
        
        System.out.println(relativeRoot.value);
        return;
    }

    //-------------------------------------------------

    public int height(){
        return height(root);
    }

    private int height(Node relativeRoot){
        if (relativeRoot == null)
            return -1;

        if (relativeRoot.leftChild == null && relativeRoot.rightChild == null)
            return 0;
             
        return 1 + Math.max(
            height(relativeRoot.leftChild), 
            height(relativeRoot.rightChild));
    }

    //---------------------------------------------

    public boolean equals(BinaryTree tree){
        if (tree == null)
            return false;

        return equals(root, tree.root);
    }

    private boolean equals(Node root, Node treeRoot){
        if(root == null && treeRoot == null)
            return true;

        if(root != null && treeRoot != null)
            return root.value == treeRoot.value 
                    && equals(root.leftChild, treeRoot.leftChild)
                    && equals(root.rightChild, treeRoot.rightChild);
        

        return false;   
    }

    //---------------------------------------------

    public boolean validateBinarySearchTree(){
        return validateBinarySearchTree(Integer.MIN_VALUE, Integer.MAX_VALUE, root);
    }

    private boolean validateBinarySearchTree(int min, int max, Node node){
        if (node == null) 
            return true;

        if (node.value > max || node.value < min)
            return false;
        
        return validateBinarySearchTree(min, node.value - 1, node.leftChild)
            && validateBinarySearchTree(node.value + 1, max, node.rightChild);
    }

    //--------------------------------------------

    public void printDistanceKFromRoot(int k){
        if (root == null) throw new IllegalStateException();

        printDistanceKFromRoot(root, k);
    }

    private void printDistanceKFromRoot(Node root , int k){
        if(root == null) return;

        if (k == 0){
            System.out.println(root.value);
        }
        else{
            printDistanceKFromRoot(root.leftChild, k - 1);
            printDistanceKFromRoot(root.rightChild, k - 1);
        }
    }

    public ArrayList<Integer> getListKFromRoot(int k){
        ArrayList<Integer> list = new ArrayList<Integer>();
        getListKFromRoot(root, k, list);
        return list;
    }

    private void getListKFromRoot(Node root, int k, ArrayList<Integer> list){
        if (root == null) return;

        if(k == 0){
            list.add(root.value);
            return;
        }

        getListKFromRoot(root.leftChild, k - 1, list);
        getListKFromRoot(root.rightChild, k - 1, list); 
    }

    //---------------------------------------------------

    public void traverseLevelOrder(){
        for(var i = 0; i <= height(); i++){
            for(var value : getListKFromRoot(i))
                System.out.println(value);
        }
    }

    //----------------Extra Exercises---------------------
    public int size(){
        int size = 0;
        for(var i = 0; i <= height(); i++){
            for(var j = 0; j < getListKFromRoot(i).size(); j++)
                size++;
        }
        return size;
     }

     public int countLeaves(){
        if(root == null) throw new IllegalStateException();

        return countLeaves(root, 0);
     }
    
     private int countLeaves(Node root, int leafCount){
        if (root == null) return leafCount;

        if(root.leftChild == null && root.rightChild == null)
            return leafCount + 1;
        
        leafCount = countLeaves(root.leftChild, leafCount);
        leafCount = countLeaves(root.rightChild, leafCount);

        return leafCount;
     }

     public int treeMax(){
        if (root == null) throw new IllegalStateException(); 

        return treeMax(root, Integer.MIN_VALUE);
     }

     private int treeMax(Node root, int currentMax){
        if (root == null) return currentMax;

        if (root.value > currentMax) 
            currentMax = root.value;
        
        currentMax = treeMax(root.leftChild, currentMax);
        currentMax = treeMax(root.rightChild, currentMax);
        
        return currentMax;
     }

     public boolean contains(int value){
        return contains(root, value);
     }

     private boolean contains(Node root, int value){
        if(root == null) return false;

        if(root.value == value) return true;
        
        return contains(root.leftChild, value)
            || contains(root.rightChild,value);
     }

     public boolean areSiblings(int valueOne, int valueTwo){
        if(valueOne == valueTwo) throw new IllegalArgumentException();

        return areSiblings(root, valueOne, valueTwo);
     }

     private boolean areSiblings(Node root, int valueOne, int valueTwo){
        if (root == null) return false;

        if (root.rightChild == null || root.leftChild == null) return false;

        boolean areSiblings = root.leftChild.value == valueOne && root.rightChild.value == valueTwo
            || root.leftChild.value == valueTwo && root.rightChild.value == valueOne;

        if (areSiblings)
            return true;

        return areSiblings(root.leftChild, valueOne, valueTwo)
            || areSiblings(root.rightChild, valueOne, valueTwo);
     }

     public ArrayList<Integer> getAncestors(int descendent){
        ArrayList<Integer> ancestors = new ArrayList<>();
        getAncestors(ancestors, root, descendent);
        return ancestors;
     }

    private boolean getAncestors(ArrayList<Integer> ancestors, Node root, int descendent){
        if (root == null) return false;

        if (root.value == descendent) return true;

        if (getAncestors(ancestors, root.leftChild, descendent) ||
            getAncestors(ancestors, root.rightChild, descendent)) {
                ancestors.add(root.value);
                return true;
        }

        return false;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }
    
    private boolean isBalanced(Node root) {
        if (root == null) return true;
    
        var balanceFactor = height(root.leftChild) - height(root.rightChild);
    
        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(root.leftChild) &&
                isBalanced(root.rightChild);
    }
    
    public boolean isPerfect() {
        return size() == (Math.pow(2, height() + 1) - 1);
    }
}
