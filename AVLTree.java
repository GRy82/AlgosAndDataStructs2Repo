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

        root.height = Math.max(
            height(root.leftChild), 
            height(root.rightChild)) + 1;

        if(isLeftHeavy(root))
            System.out.println("Left Heavy");
        else if(isRightHeavy(root))
            System.out.println("Right Heavy");

        return root;
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
