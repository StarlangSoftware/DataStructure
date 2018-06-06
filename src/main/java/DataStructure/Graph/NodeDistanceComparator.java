package DataStructure.Graph;

import java.util.Comparator;

public class NodeDistanceComparator<T> implements Comparator<Node<T>>{
    private SingleSourceShortestPath<T> singleSourceShortestPath;

    public NodeDistanceComparator(SingleSourceShortestPath<T> singleSourceShortestPath){
        this.singleSourceShortestPath = singleSourceShortestPath;
    }

    @Override
    public int compare(Node<T> o1, Node<T> o2) {
        Double length1, length2;
        length1 = singleSourceShortestPath.getLength(o1);
        length2 = singleSourceShortestPath.getLength(o2);
        return length1.compareTo(length2);
    }
}
