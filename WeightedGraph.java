import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
    private Map<String, Node> entities = new HashMap<>();
    private Map<Node, List<Edge>> edgesList = new HashMap<>(); 

    private class Node{
        private String label;

        public Node(String label){
            this.label = label;
        }

        @Override
        public String toString(){ return label; }
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
        var node = new Node(label);
        entities.putIfAbsent(label, node);
        edgesList.putIfAbsent(node, new ArrayList<>());
        
    }
    
    public void addEdge(String from, String to, int weight){
        if(!entities.keySet().contains(from) || !entities.keySet().contains(to))
            throw new IllegalArgumentException();

        var fromEntity = entities.get(from);
        var toEntity = entities.get(to);

        edgesList.get(fromEntity).add(new Edge(fromEntity, toEntity, weight));
        edgesList.get(toEntity).add(new Edge(toEntity, fromEntity, weight));
    }
   
}
