package DataStructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AllPairsShortestPath<T> {
    private HashMap<Node<T>, SingleSourceShortestPath<T>> paths;

    public AllPairsShortestPath(Set<Node<T>> nodeList){
        paths = new HashMap<>();
        for (Node<T> node : nodeList){
            paths.put(node, new SingleSourceShortestPath<T>(node));
        }
    }

    public void put(Node<T> fromNode, SingleSourceShortestPath<T> singleSourceShortestPath){
        paths.put(fromNode, singleSourceShortestPath);
    }

    public void update(Node<T> fromNode, Node<T> toNode, double newLength, Node<T> newPrevious){
        paths.get(fromNode).update(toNode, newLength, newPrevious);
    }

    public double getLength(Node<T> fromNode, Node<T> toNode){
        return paths.get(fromNode).getLength(toNode);
    }

    public ArrayList<Node<T>> shortestPath(Node<T> fromNode, Node<T> toNode){
        return paths.get(fromNode).shortestPath(toNode);
    }

}
