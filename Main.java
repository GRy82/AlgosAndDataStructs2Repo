
public class Main{
    public static BinaryTree binaryTree = new BinaryTree();
    public static void main(String[] args) {
        testBinaryTreeInsert();
        //testBinaryTreeFind();
        binaryTree.traversePreOrder();
    }

    public static void testBinaryTreeInsert(){
        binaryTree.insert(7);
        binaryTree.insert(4);
        binaryTree.insert(9);
        binaryTree.insert(1);
        binaryTree.insert(6);
        binaryTree.insert(8);
        binaryTree.insert(8);
    }

    public static void testBinaryTreeFind(){
        System.out.println(binaryTree.Find(8));
        System.out.println(binaryTree.Find(1));
        System.out.println(binaryTree.Find(7));
        System.out.println(binaryTree.Find(5));
    }
}
