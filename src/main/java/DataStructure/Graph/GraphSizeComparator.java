package DataStructure.Graph;

import java.util.Comparator;

public class GraphSizeComparator<T> implements Comparator<Graph<T>> {

    public int compare(Graph<T> o1, Graph<T> o2) {
        if (o1.numberOfNodes() > o2.numberOfNodes()){
            return -1;
        } else {
            if (o1.numberOfNodes() < o2.numberOfNodes()){
                return 1;
            } else {
                return 0;
            }
        }
    }
}
