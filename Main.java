import java.util.List;

//import java.util.LinkedList;

public class Main {
    public static WeightedGraph weightedGraph = new WeightedGraph();
    public static Graph graph = new Graph();
    public static Trie trie = new Trie();
    public static BinaryTree binaryTree = new BinaryTree();
    public static BinaryTree binaryTwo = new BinaryTree();
    public static BinaryTree[] trees = new BinaryTree[]{binaryTree, binaryTwo};
    public static AVLTree avlTree = new AVLTree();
    public static Heap heap = new Heap();
    public static void main(String[] args) {
        testShortestPath();
        testWeightGraphClass();
        testGraphHasCycle();
        testTopologicalSort();
        constructTestTrie();
        //testTrieCountWords();
        //testTrieContainsRecursive();
        //int[] numbers = { 5, 4, 9, 7, 6, 8 };
        //int[] descendingOrder = sortArrayWithHeap(numbers);
        //System.out.println(Arrays.toString(descendingOrder));
        //testHeap();
        //testAVLTreeInsert();
        //testBinaryTreeInsert();
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
        //System.out.println(binaryTree.areSiblings(1, 19));
        //System.out.println(binaryTree.getAncestors(13));
        //System.out.println(binaryTree.isBalanced());
        //System.out.println(binaryTree.isPerfect());
    }

    public static void testShortestPath(){
        weightedGraph.addNode("A");
        weightedGraph.addNode("B");
        weightedGraph.addNode("C");
        weightedGraph.addEdge("A", "B", 2);
        weightedGraph.addEdge("B", "C", 3);
        weightedGraph.addEdge("A", "C", 10);
        var path = weightedGraph.getShortestPath("A", "C");
        System.out.println(path);
    }

    public static void testWeightGraphClass(){
        weightedGraph.addNode("A");
        weightedGraph.addNode("B");
        weightedGraph.addNode("C");
        weightedGraph.addEdge("A", "B", 5);
        weightedGraph.addEdge("A", "C", 5);
        weightedGraph.print();
    }

    public static void testGraphHasCycle(){
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        graph.addEdge("D", "A");
        System.out.println(graph.hasCycle());
    }

    public static void testTopologicalSort(){
        graph.addNode("X");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("P");
        graph.addEdge("X", "A");
        graph.addEdge("X", "B");
        graph.addEdge("B", "P");
        graph.addEdge("A", "P");
        List<String> labels = graph.topologicalSort();
        System.out.println(labels);
    }

    public static void constructTestTrie(){
        trie.insert("ball");
        trie.insert("balloon");
        trie.insert("hi");
    }

    public static void testTrieContainsRecursive(){
        System.out.println(trie.containsRecursive("bal"));
        System.out.println(trie.containsRecursive("hi"));
        System.out.println(trie.containsRecursive("his"));
        System.out.println(trie.containsRecursive("balloon"));
        System.out.println(trie.containsRecursive("balloons"));
        System.out.println(trie.containsRecursive(""));
        System.out.println(trie.containsRecursive(null));
    }

    public static void testTrieCountWords(){
        System.out.println(trie.countWords());
    }

    public static void testHeap(){
        heap.insert(6);
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.moshRemove();
        
        System.out.println("Done");
    }

    public static void testAVLTreeInsert(){
        avlTree.insert(5);
        avlTree.insert(8);
        avlTree.insert(3);
        avlTree.insert(10);
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

    public static int[] sortArrayWithHeap(int[] numbers){
        Heap heap = new Heap();
        for (var number : numbers)
            heap.insert(number);

        for (int i = 0; i < numbers.length; i++)
            numbers[i] = heap.moshRemove();

        return numbers; 
    }
}
