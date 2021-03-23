import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

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

    public int getShortestDistance(String from, String to){
        var fromNode = entities.get(from);
        Map<Node, Integer> distances = new HashMap<>();
        for(var node : entities.values())
            distances.put(node, Integer.MAX_VALUE);
        distances.replace(fromNode, 0);

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
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }
        return distances.get(entities.get(to));
    }
}
