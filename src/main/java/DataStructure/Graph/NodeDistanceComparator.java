package DataStructure.Graph;

import java.util.Comparator;

public class NodeDistanceComparator<T> implements Comparator<Node<T>> {
    private SingleSourceShortestPath<T> singleSourceShortestPath;

    /**
     * A constructor of {@link NodeDistanceComparator} class which takes a {@link SingleSourceShortestPath} as an input.
     *
     * @param singleSourceShortestPath {@link SingleSourceShortestPath} type input.
     */
    public NodeDistanceComparator(SingleSourceShortestPath<T> singleSourceShortestPath) {
        this.singleSourceShortestPath = singleSourceShortestPath;
    }

    /**
     * The overridden compare method which takes two {@link Node}s as input. If the length of the shortest paths of both nodes equal,
     * it returns 0, if the length of first Node is greater than second Node it returns -1, otherwise it returns 1.
     *
     * @param o1 first {@link Node} to compare.
     * @param o2 second {@link Node} to compare.
     * @return 0 if length of shortest paths equal, -1 if first one is greater than second one, 1 otherwise.
     */
    public int compare(Node<T> o1, Node<T> o2) {
        Double length1, length2;
        length1 = singleSourceShortestPath.getLength(o1);
        length2 = singleSourceShortestPath.getLength(o2);
        return length1.compareTo(length2);
    }
}
