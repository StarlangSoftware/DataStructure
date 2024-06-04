package DataStructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AllPairsShortestPath<T> {
    private HashMap<Node<T>, SingleSourceShortestPath<T>> paths;

    /**
     * A constructor of {@link AllPairsShortestPath} class which takes a {@link Set} nodeList as input. It creates a new
     * {@link HashMap} as paths. Then, loops through the nodeList and puts each node with its {@link SingleSourceShortestPath}
     * to paths {@link HashMap}.
     *
     * @param nodeList {@link Set} type nodeList.
     */
    public AllPairsShortestPath(Set<Node<T>> nodeList) {
        paths = new HashMap<>();
        for (Node<T> node : nodeList) {
            paths.put(node, new SingleSourceShortestPath<T>(node));
        }
    }

    /**
     * The put method takes a {@link Node} and a {@link SingleSourceShortestPath} as inputs and puts them into paths {@link HashMap}.
     *
     * @param fromNode                 {@link Node} type input to put paths {@link HashMap}.
     * @param singleSourceShortestPath {@link SingleSourceShortestPath} type input to put paths {@link HashMap}.
     */
    public void put(Node<T> fromNode, SingleSourceShortestPath<T> singleSourceShortestPath) {
        paths.put(fromNode, singleSourceShortestPath);
    }

    /**
     * The update method takes 3 {@link Node}s; fromNode,toNode and newPrevious, and a newLength as inputs. Then, gets the value of fromNode
     * from paths {@link HashMap} and calls update method for that value in order to put toNode with newPrevious and newLength
     * into pathLength and previous {@link HashMap}s.
     *
     * @param fromNode    {@link Node} type input.
     * @param toNode      {@link Node} type input.
     * @param newLength   length of the path.
     * @param newPrevious {@link Node} type input.
     */
    public void update(Node<T> fromNode, Node<T> toNode, double newLength, Node<T> newPrevious) {
        paths.get(fromNode).update(toNode, newLength, newPrevious);
    }

    /**
     * The getLength method takes two {@link Node}s as inputs; fromNode and toNode. It gets the value of fromNode from paths
     * {@link HashMap} and returns the path length between fromNode and toNode.
     *
     * @param fromNode {@link Node} type input.
     * @param toNode   Node to check for the length.
     * @return length of the given path.
     */
    public double getLength(Node<T> fromNode, Node<T> toNode) {
        return paths.get(fromNode).getLength(toNode);
    }

    /**
     * The shortestPath method returns the path between fromNode and toNode as an {@link ArrayList}.
     *
     * @param fromNode {@link Node} type input.
     * @param toNode   {@link Node} type input.
     * @return the path between fromNode and toNode as an {@link ArrayList}.
     */
    public ArrayList<Node<T>> shortestPath(Node<T> fromNode, Node<T> toNode) {
        return paths.get(fromNode).shortestPath(toNode);
    }

}
