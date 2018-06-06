package DataStructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class SingleSourceShortestPath<T> {
    private HashMap<Node<T>, Double> pathLength;
    private HashMap<Node<T>, Node<T>> previous;
    private Node<T> startNode;

    public SingleSourceShortestPath(Node<T> startNode){
        pathLength = new HashMap<>();
        previous = new HashMap<>();
        this.startNode = startNode;
        update(startNode, 0, startNode);
    }

    public void update(Node<T> toNode, double newLength, Node<T> newPrevious){
        pathLength.put(toNode, newLength);
        previous.put(toNode, newPrevious);
    }

    public double getLength(Node<T> toNode){
        if (pathLength.containsKey(toNode)){
            return pathLength.get(toNode);
        } else {
            return Double.MAX_VALUE;
        }
    }

    public ArrayList<Node<T>> shortestPath(Node<T> toNode){
        Node<T> previousNode;
        ArrayList<Node<T>> result = new ArrayList<>();
        previousNode = toNode;
        result.add(previousNode);
        while (!previousNode.equals(startNode)){
            previousNode = previous.get(previousNode);
            result.add(previousNode);
        }
        return result;
    }

}
