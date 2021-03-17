import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private class Node{
        private String label;

        public Node(String label){
            this.label = label;
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
}   
