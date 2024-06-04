package DataStructure.Graph;

import java.util.*;

public class GraphList<T> {
    private ArrayList<Graph<T>> graphList;

    /**
     * A constructor of {@link GraphList} class which creates new graphList {@link ArrayList}.
     */
    public GraphList() {
        graphList = new ArrayList<>();
    }

    /**
     * Another constructor of {@link GraphList} class which takes a graphList {@link ArrayList} as an input and initializes
     * graphList variable with this input.
     *
     * @param graphList {@link ArrayList} type input.
     */
    public GraphList(ArrayList<Graph<T>> graphList) {
        this.graphList = graphList;
    }

    /**
     * The add method takes a {@link Graph} as an input and adds this graph to the graphList.
     *
     * @param graph {@link Graph} input to add.
     */
    public void add(Graph<T> graph) {
        graphList.add(graph);
    }

    /**
     * The size input returns the size of the graphList.
     *
     * @return the size of the graphList.
     */
    public int size() {
        return graphList.size();
    }

    /**
     * The getGraph method returns the graph at given index.
     *
     * @param index to get graph.
     * @return the {@link Graph} at given index.
     */
    public Graph<T> getGraph(int index) {
        return graphList.get(index);
    }

    /**
     * The numberOfNodes method loops through the graphList and accumulates the number of nodes at each graph.
     *
     * @return total number of nodes in a graphList.
     */
    public int numberOfNodes() {
        int sum = 0;
        for (Graph<T> graph : graphList) {
            sum += graph.numberOfNodes();
        }
        return sum;
    }

    /**
     * The numberOfEdges method loops through the graphList and accumulates the number of edges at each graph.
     *
     * @return total number of edges in a graphList.
     */
    public int numberOfEdges() {
        int sum = 0;
        for (Graph<T> graph : graphList) {
            sum += graph.numberOfEdges();
        }
        return sum;
    }

    /**
     * The nodeList method creates a new {@link HashSet} and loops through the graphList and adds all nodes to the new {@link HashSet}.
     *
     * @return HashSet type result.
     */
    public HashSet<Node<T>> nodeList() {
        HashSet<Node<T>> result = new HashSet<>();
        for (Graph<T> graph : graphList) {
            result.addAll(graph.nodeList());
        }
        return result;
    }

    /**
     * The compareGraphLists method takes a {@link GraphList} and 3 boolean inputs. First it sorts both graphLists and starting from index 0;
     * <p>
     * With the presence of addFirst if ith index of graphList is greater than the input {@link GraphList}
     * it only adds to result {@link GraphList} the graphList's ith item then increments index of graphList by one.
     * <p>
     * With the presence of addSecond if ith index of graphList is less than the input {@link GraphList}
     * it only adds to result {@link GraphList} the input {@link GraphList}'s ith item then increments index of input GraphList by one.
     * <p>
     * With the presence of addBoth it adds to result {@link GraphList} the graphList's ith item then increments bot indices by one.
     *
     * @param second    {@link GraphList} input.
     * @param addFirst  boolean input.
     * @param addSecond boolean input.
     * @param addBoth   boolean input.
     * @return GraphList type result.
     */
    public GraphList<T> compareGraphLists(GraphList<T> second, boolean addFirst, boolean addSecond, boolean addBoth) {
        int index1, index2;
        ArrayList<Graph<T>> graphList2 = second.graphList;
        GraphList<T> result = new GraphList<T>();
        Collections.sort(graphList);
        Collections.sort(second.graphList);
        index1 = 0;
        index2 = 0;
        while (index1 < graphList.size() && index2 < graphList2.size()) {
            String nodeSet1 = graphList.get(index1).nodesToString();
            String nodeSet2 = graphList2.get(index2).nodesToString();
            if (nodeSet1.compareTo(nodeSet2) < 0) {
                if (addFirst) {
                    result.add(graphList.get(index1));
                }
                index1++;
            } else {
                if (nodeSet1.compareTo(nodeSet2) > 0) {
                    if (addSecond) {
                        result.add(second.graphList.get(index2));
                    }
                    index2++;
                } else {
                    if (addBoth) {
                        result.add(graphList.get(index1));
                    }
                    index1++;
                    index2++;
                }
            }
        }
        if (index1 < graphList.size() && (addFirst || addBoth)) {
            while (index1 < graphList.size()) {
                result.add(graphList.get(index1));
                index1++;
            }
        }
        if (index2 < graphList2.size() && (addSecond || addBoth)) {
            while (index2 < graphList2.size()) {
                result.add(graphList2.get(index2));
                index2++;
            }
        }
        return result;
    }

    /**
     * The sortWrtNodeSize sorts the graphList by node number.
     */
    public void sortWrtNodeSize() {
        Collections.sort(graphList, new GraphSizeComparator<T>());
    }

    /**
     * The sizeString method first sorts the nodes then gets the total number of nodes in graphList and loops till the end of the
     * graphList by starting with making graphCount equal to 1. If the ith item's number of nodes equals to the total number of nodes,
     * increments graphCount, if not assigns ith item's number of nodes to total number of nodes and makes graphCount equal to 1.
     *
     * @return resulting string consists of total number of nodes and graphCount.
     */
    public String sizeString() {
        String result = "";
        sortWrtNodeSize();
        int i = 0;
        int nodeCount = graphList.get(i).numberOfNodes(), graphCount = 1;
        while (i + 1 < graphList.size()) {
            i++;
            if (graphList.get(i).numberOfNodes() == nodeCount) {
                graphCount++;
            } else {
                result = result + nodeCount + "->" + graphCount + "\n";
                nodeCount = graphList.get(i).numberOfNodes();
                graphCount = 1;
            }
        }
        result = result + nodeCount + "->" + graphCount + "\n";
        return result;
    }

    /**
     * The intersection method gets another {@link GraphList} as input and compares it with the graphList and calls compareGraphLists
     * method with addBoth parameter as true.
     *
     * @param second {@link GraphList} to compare.
     * @return GraphList consists of intersection of two {@link GraphList}s.
     */
    public GraphList<T> intersection(GraphList<T> second) {
        return compareGraphLists(second, false, false, true);
    }

    /**
     * The difference method gets another {@link GraphList} as input and compares it with the graphList and calls compareGraphLists
     * method with addFirst parameter as true.
     *
     * @param second {@link GraphList} to compare.
     * @return GraphList consists of differences of two {@link GraphList}s.
     */
    public GraphList<T> difference(GraphList<T> second) {
        return compareGraphLists(second, true, false, false);
    }

    /**
     * The union method gets another {@link GraphList} as input and compares it with the graphList and calls compareGraphLists
     * method with all parameters as true.
     *
     * @param second {@link GraphList} to compare.
     * @return GraphList consists of union of two {@link GraphList}s.
     */
    public GraphList<T> union(GraphList<T> second) {
        return compareGraphLists(second, true, true, true);
    }

}
