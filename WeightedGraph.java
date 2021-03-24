import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class WeightedGraph {
    private Map<String, Node> entities = new HashMap<>();

    private class Node{
        private String label;
        private List<Edge> edgesList = new ArrayList<>();

        public Node(String label){
            this.label = label;
        }

        @Override
        public String toString(){ return label; }

        public void addEdge(Node to, int weight){
            edgesList.add(new Edge(this, to, weight));
        }

        public List<Edge> getEdges(){
            return edgesList;
        }
    }
    private class Edge{
        private Node from;
        private Node to;
        private int weight;

        public Edge(Node from, Node to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString(){ 
            return from + "->" + to;
         }
    }

    public void addNode(String label){
        entities.putIfAbsent(label, new Node(label));
    }
    
    public void addEdge(String from, String to, int weight){
        if(!entities.keySet().contains(from) || !entities.keySet().contains(to))
            throw new IllegalArgumentException();

        var fromEntity = entities.get(from);
        var toEntity = entities.get(to);

        fromEntity.addEdge(toEntity, weight);
        toEntity.addEdge(fromEntity, weight);
    }

    public void print(){
        for(var node: entities.values()){
            var edges = node.getEdges();
            if(!edges.isEmpty())
                System.out.println(node + " is connected to " + edges);
        }
    }

    private class NodeEntry{
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority){
            this.node = node;
            this.priority = priority;
        }
    }


    public Path getShortestPath(String from, String to){
        if(!entities.containsKey(from) || !entities.containsKey(to))
            throw new IllegalArgumentException();

        var fromNode = entities.get(from);
        var toNode = entities.get(to);

        Map<Node, Integer> distances = new HashMap<>();
        for(var node : entities.values())
            distances.put(node, Integer.MAX_VALUE);
        distances.replace(fromNode, 0);

        Map<Node, Node> previousNodes = new HashMap<>();

        Set<Node> visited = new HashSet<>();

        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(
            Comparator.comparingInt(ne -> ne.priority));
    
        queue.add(new NodeEntry(fromNode, 0));

        while(!queue.isEmpty()){
            var currentNode = queue.remove().node;
            visited.add(currentNode);
            for(var edge : currentNode.getEdges()){
                if(visited.contains(edge.to)) continue;
                
                var newDistance = distances.get(currentNode) + edge.weight;
                if (newDistance < distances.get(edge.to)){
                    distances.replace(edge.to, newDistance);
                    previousNodes.put(edge.to, currentNode);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }

        return buildPath(toNode, fromNode, previousNodes);
    }

    private Path buildPath(Node toNode, Node fromNode, Map<Node, Node> previousNodes){
        Stack<Node> stack = new Stack<>();
        var current = toNode;
        stack.add(current);
        while(current != fromNode){
            current = previousNodes.get(current);
            stack.add(current);
        }
        
        Path path = new Path();
        while(!stack.isEmpty())
            path.add(stack.pop().label);

        return path;
    }

    public boolean hasCycle(){
        Set<Node> visited = new HashSet<>();
        for(var node: entities.values()) {
            if(!visited.contains(node) &&
                hasCycle(node, null, visited))
                    return true;
        }

        return false;
    }


    private boolean hasCycle(Node current, Node parent, Set<Node> visited){
            visited.add(current);

            for(var edge : current.getEdges()){
                if(edge.to == parent)
                    continue;
                if(visited.contains(edge.to) ||
                  hasCycle(edge.to, current, visited))
                    return true;
            }

            return false;
    }

    public WeightedGraph primsAlgorithm(){
        WeightedGraph spanningTree = new WeightedGraph();
        if(entities.isEmpty()) return spanningTree;

        PriorityQueue<Edge> edges = new PriorityQueue<>(
            Comparator.comparingInt(e -> e.weight)
        );
        var firstArbitraryNode = entities.values().iterator().next();
        edges.addAll(firstArbitraryNode.getEdges()); 
        spanningTree.addNode(firstArbitraryNode.label);

        if(edges.isEmpty()) return spanningTree;
        
        while(entities.size() > spanningTree.entities.size()){
            var smallestEdge = edges.remove();
            var newNode = smallestEdge.to;

            if(spanningTree.entities.containsKey(newNode.label))
                continue;

            spanningTree.addNode(newNode.label);
            spanningTree.addEdge(smallestEdge.from.label, 
                newNode.label, smallestEdge.weight);

            for(var edge : newNode.getEdges())
                if(!spanningTree.entities.containsKey(edge.to.label))
                    edges.add(edge);
        }

        return spanningTree;
    }
}
