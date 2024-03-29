import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {

    private class Node{
        private String label;

        public Node(String label){
            this.label = label;
        } 

        @Override
        public String toString(){
            return label;
        }
    }

    private Map<String, Node> entities = new HashMap<>();
    private Map<Node, List<Node>> connections = new HashMap<>();

    public void addNode(String label){
        var node = new Node(label);
        entities.putIfAbsent(label, node);
        connections.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to){
        Node fromEntity = entities.get(from);
        if(fromEntity == null)
            throw new IllegalArgumentException();

        Node toEntity = entities.get(to);
        if(toEntity == null)
            throw new IllegalArgumentException();

        connections.get(fromEntity).add(toEntity);
    }

    public void removeEntity(String label){
        var entity = entities.get(label);
        if(entity == null) return;

        for(var n : connections.keySet())
            connections.get(n).remove(entity);

        connections.remove(entity);
        entities.remove(entity.label);
    }

    public void removeEdge(String from, String to){
        var fromEntity = entities.get(from);
        var toEntity = entities.get(to);
        
        if(fromEntity == null || toEntity == null) return;

        connections.get(fromEntity).remove(toEntity);
    }

    public void print(){
        for (var source : connections.keySet()){
            var targets = connections.get(source);
            if(!targets.isEmpty())
                System.out.println(source + " is connected to " + targets);
        }
    }

    public void depthFirstTraversal(String root){
        Node current = entities.get(root);
        if(current == null) return;

        depthFirstTraversal(current, new HashSet<>());
    }

    private void depthFirstTraversal(Node currentEntity, Set<Node> visitedNodes){
        System.out.println(currentEntity);
        visitedNodes.add(currentEntity);

        for (var neighbor : connections.get(currentEntity)){
            if(!visitedNodes.contains(neighbor)){
                depthFirstTraversal(neighbor, visitedNodes);
            }
        }
    }
    //Same purpose as above, but implemented using iteration, not recursion.
    public void traverseDepthFirst(String root){
        Node current = entities.get(root);
        if(current == null) return;

        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>(); 
        stack.push(current);

        while(!stack.isEmpty()){
            current = stack.pop();
            
            if(visited.contains(current))
                continue;

            System.out.println(current);
            visited.add(current);
            for(var neighbor : connections.get(current))
                stack.push(neighbor);
        }
    }

    public void traverseBreadthFirst(String root){
        var rootNode = entities.get(root);
        if(rootNode == null) return;

        Queue<Node> nodeQueue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();

        nodeQueue.add(rootNode);
        
        while(!nodeQueue.isEmpty()){
            var current = nodeQueue.remove();
            if (visited.contains(current))
                continue;
            
            System.out.println(current);
            visited.add(current);

            for(var connect : connections.get(current))
                if(!nodeQueue.contains(connect))
                    nodeQueue.add(connect);
        }
    }   

    public List<String> topologicalSort(){
        Stack<Node> stack = new Stack<>();
        List<String> sortedLabels = new ArrayList<>();
        Set<Node> visited = new HashSet<>();

        for(var node : entities.values())
            topologicalSort(node, stack, visited);

        while(!stack.isEmpty())
            sortedLabels.add(stack.pop().label);

        return sortedLabels;
    }

    private void topologicalSort(Node node, Stack<Node> stack, Set<Node> visited){
        if(visited.contains(node)) return;

        visited.add(node);
        
        for(var connection : connections.get(node))
            topologicalSort(connection, stack, visited);

        stack.push(node);
    }

    public boolean hasCycle(){
        Set<Node> all = new HashSet<>();
        all.addAll(entities.values());
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        while(!all.isEmpty()){
            var current = all.iterator().next();
            if(hasCycle(current, all, visited, visiting))
                return true;
        }

        return false;
    }

    private boolean hasCycle(Node node, Set<Node> all, Set<Node> visited, Set<Node> visiting){
        all.remove(node);    
        visiting.add(node);
        
        for (var connect : connections.get(node)){
            if(visited.contains(connect))
                continue;

            if(visiting.contains(connect))
                return true;

            if(hasCycle(connect, all, visited, visiting))
                return true; 
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }
}   
