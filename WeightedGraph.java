import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   
}
