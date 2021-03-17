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
}   
