package DataStructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class SingleSourceShortestPath<T> {
    private HashMap<Node<T>, Double> pathLength;
    private HashMap<Node<T>, Node<T>> previous;
    private Node<T> startNode;

    /**
     * A constructor of {@link SingleSourceShortestPath} class which takes a {@link Node} type input startNode. Creates
     * two new {@link HashMap}s for pathLength and previous and assigns given startNode to startNode variable. Then,
     * calls update method in order to put startNode into pathLength and previous {@link HashMap}s.
     *
     * @param startNode {@link Node} type input.
     */
    public SingleSourceShortestPath(Node<T> startNode) {
        pathLength = new HashMap<>();
        previous = new HashMap<>();
        this.startNode = startNode;
        update(startNode, 0, startNode);
    }

    /**
     * The update method takes 2 {@link Node}s; toNode and newPrevious, and a length value. Then it puts Node toNode with newLength
     * value into pathLength {@link HashMap} and also the toNode and newPrevious nodes into previous {@link HashMap}.
     *
     * @param toNode      {@link Node} type input.
     * @param newLength   length of the path.
     * @param newPrevious {@link Node} type input.
     */
    public void update(Node<T> toNode, double newLength, Node<T> newPrevious) {
        pathLength.put(toNode, newLength);
        previous.put(toNode, newPrevious);
    }

    /**
     * The getLength method takes a {@link Node} as input. If the pathLength {@link HashMap} contains given Node, then it
     * returns the corresponding path length of that Node, if pathLength does not contain the given Node then its length will be
     * assigned as the MAX_VALUE.
     *
     * @param toNode Node to check for the length.
     * @return length of the given path.
     */
    public double getLength(Node<T> toNode) {
        if (pathLength.containsKey(toNode)) {
            return pathLength.get(toNode);
        } else {
            return Double.MAX_VALUE;
        }
    }

    /**
     * The shortestPath method takes a {@link Node} toNode as input which is the destination node. First it creates a new
     * {@link ArrayList} result and assigns given toNode to previousNode and adds it to the newly created {@link ArrayList}.
     * While the previousNode is not equal to the startNode, starting from toNodes' previousNode it tries to reach the startNode
     * and adds each previousNode to the new {@link ArrayList} result.
     *
     * @param toNode {@link Node} type input represents destination node.
     * @return ArrayList that contains path from destination to source.
     */
    public ArrayList<Node<T>> shortestPath(Node<T> toNode) {
        Node<T> previousNode;
        ArrayList<Node<T>> result = new ArrayList<>();
        previousNode = toNode;
        result.add(previousNode);
        while (!previousNode.equals(startNode)) {
            previousNode = previous.get(previousNode);
            result.add(previousNode);
        }
        return result;
    }

}
