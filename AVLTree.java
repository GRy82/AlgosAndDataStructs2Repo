public class AVLTree{
    private class AVLNode{
        private int height;
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

        calculateHeight(root);

        return balance(root);
    }

        
    private AVLNode balance(AVLNode root){
        if(isLeftHeavy(root)){
            if (balanceFactor(root.leftChild) < 0)
                root.leftChild = leftRotate(root.leftChild);
            return rightRotate(root);
        }
        else if(isRightHeavy(root)){
            if(balanceFactor(root.rightChild) > 0)
                root.rightChild = rightRotate(root.rightChild);
            return leftRotate(root);
        }

        return root;
    }


    private AVLNode rightRotate(AVLNode root){
        AVLNode newRoot = root.leftChild;
        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;

        calculateHeight(root);
        calculateHeight(newRoot);

        return newRoot;
    }

    private AVLNode leftRotate(AVLNode root){
        AVLNode newRoot = root.rightChild;
        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;

        calculateHeight(root);
        calculateHeight(newRoot);

        return newRoot;
    }


    private void calculateHeight(AVLNode root){
        root.height = Math.max(
            height(root.leftChild), 
            height(root.rightChild)) + 1;
    }

    private int height(AVLNode node){
        return node == null ? -1 : node.height;
    }


    private boolean isLeftHeavy(AVLNode node){
        return balanceFactor(node) > 1;
    }


    private boolean isRightHeavy(AVLNode node){
        return balanceFactor(node) < -1;
    }


    private int balanceFactor(AVLNode node){
        return node == null ? 0 : height(node.leftChild) - height(node.rightChild);
    }
}
