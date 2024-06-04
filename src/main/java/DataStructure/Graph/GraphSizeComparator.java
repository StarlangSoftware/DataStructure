package DataStructure.Graph;

import java.util.Comparator;

public class GraphSizeComparator<T> implements Comparator<Graph<T>> {

    /**
     * The compare method takes two {@link Graph}s as inputs. If the size of the nodes {@link java.util.HashSet} of first
     * graph equals to the size of the nodes {@link java.util.HashSet} of second graph then it returns 0, if it is greater
     * than the second one it returns -1, and 1 otherwise.
     * .
     *
     * @param o1 first {@link Graph} to compare.
     * @param o2 second {@link Graph} to compare.
     * @return 0 if both has equal numberOfNodes, if first {@link Graph}'s numberOfNodes is greater than second one return -1, 1 otherwise.
     */
    public int compare(Graph<T> o1, Graph<T> o2) {
        if (o1.numberOfNodes() > o2.numberOfNodes()) {
            return -1;
        } else {
            if (o1.numberOfNodes() < o2.numberOfNodes()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
