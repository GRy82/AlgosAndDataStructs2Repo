
public class Main{
    public static void main(String[] args) {
        testBinaryTreeInsert();
    }

    public static void testBinaryTreeInsert(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(7);
        binaryTree.insert(4);
        binaryTree.insert(9);
        binaryTree.insert(1);
        binaryTree.insert(6);
        binaryTree.insert(8);
        binaryTree.insert(8);
        System.out.println("Pause Debugger here.");
    }
}
