public class AVLTree{
    private class AVLNode{
        private AVLNode rightChild;
        private AVLNode leftChild;
        private int value;

        public AVLNode(int value){
            this.value = value;
        }
    }

    private AVLNode root;

    public void insert(int value){
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode root,int value){
        if (root == null) 
            return new AVLNode(value);

        if (value > root.value)
            root.rightChild = insert(root.rightChild, value);
        
        if (value < root.value)
            root.leftChild = insert(root.leftChild, value);

        return root;
    }
}