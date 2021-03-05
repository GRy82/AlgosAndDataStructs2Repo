
public class Main{
    public static BinaryTree binaryTree = new BinaryTree();
    public static BinaryTree binaryTwo = new BinaryTree();
    public static BinaryTree[] trees = new BinaryTree[]{binaryTree, binaryTwo};
    public static void main(String[] args) {
        testBinaryTreeInsert();
        //testBinaryTreeFind();
        //binaryTree.traversePreOrder();
        //binaryTree.traverseInOrder();
        //binaryTree.traversePostOrder();
        //System.out.println(binaryTree.height());
        //System.out.println(binaryTree.equals(binaryTwo));
        //System.out.println(binaryTree.validateBinarySearchTree());
        //binaryTree.printDistanceKFromRoot(4);
        //System.out.println(binaryTree.size()); 
        //System.out.println(binaryTree.countLeaves()); 
        //System.out.println(binaryTree.treeMax()); 
        //System.out.println(binaryTree.contains(50));
        System.out.println(binaryTree.areSiblings(1, 9));
        System.out.println("Hi");
    }

    public static void testBinaryTreeInsert(){
        binaryTwo.insert(7);
        binaryTwo.insert(4);
        binaryTwo.insert(9);
        binaryTwo.insert(1);
        binaryTwo.insert(6);
        binaryTwo.insert(10);
        binaryTwo.insert(11);
        binaryTree.insert(7);
        binaryTree.insert(4);
        binaryTree.insert(9);
        binaryTree.insert(1);
        binaryTree.insert(6);
        binaryTree.insert(8);
        binaryTree.insert(11);
    }

    public static void testBinaryTreeFind(){
        System.out.println(binaryTree.Find(8));
        System.out.println(binaryTree.Find(1));
        System.out.println(binaryTree.Find(7));
        System.out.println(binaryTree.Find(5));
    }
}
