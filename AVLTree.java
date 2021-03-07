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
        if (root == null) {
            root = new AVLNode(value);
            return;
        }

        insert(root, root, value);
    }

    private void insert(AVLNode root, AVLNode previous, int value){
        if (root == null) {
            root = new AVLNode(value);
            if(value > previous.value)
                previous.rightChild = root;
            else if(value < previous.value)
                previous.leftChild = root;
            return;
        }

        if (value > root.value)
            insert(root.rightChild, root, value);
        
        if (value < root.value)
            insert(root.leftChild, root, value);
    }
}