package DataStructure.Graph;

import java.util.*;

public class GraphList<T> {
    private ArrayList<Graph<T>> graphList;

    public GraphList(){
        graphList = new ArrayList<>();
    }

    public GraphList(ArrayList<Graph<T>> graphList){
        this.graphList = graphList;
    }

    public void add(Graph<T> graph){
        graphList.add(graph);
    }

    public int size(){
        return graphList.size();
    }

    public Graph<T> getGraph(int index){
        return graphList.get(index);
    }

    public int numberOfNodes(){
        int sum = 0;
        for (Graph<T> graph : graphList){
            sum += graph.numberOfNodes();
        }
        return sum;
    }

    public int numberOfEdges(){
        int sum = 0;
        for (Graph<T> graph : graphList){
            sum += graph.numberOfEdges();
        }
        return sum;
    }

    public HashSet<Node<T>> nodeList(){
        HashSet<Node<T>> result = new HashSet<>();
        for (Graph<T> graph : graphList){
            result.addAll(graph.nodeList());
        }
        return result;
    }

    public GraphList<T> compareGraphLists(GraphList<T> second, boolean addFirst, boolean addSecond, boolean addBoth){
        int index1, index2;
        ArrayList<Graph<T>> graphList2 = second.graphList;
        GraphList<T> result = new GraphList<T>();
        Collections.sort(graphList);
        Collections.sort(second.graphList);
        index1 = 0;
        index2 = 0;
        while (index1 < graphList.size() && index2 < graphList2.size()){
            String nodeSet1 = graphList.get(index1).nodesToString();
            String nodeSet2 = graphList2.get(index2).nodesToString();
            if (nodeSet1.compareTo(nodeSet2) < 0){
                if (addFirst){
                    result.add(graphList.get(index1));
                }
                index1++;
            } else {
                if (nodeSet1.compareTo(nodeSet2) > 0){
                    if (addSecond){
                        result.add(second.graphList.get(index2));
                    }
                    index2++;
                } else {
                    if (addBoth){
                        result.add(graphList.get(index1));
                    }
                    index1++;
                    index2++;
                }
            }
        }
        if (index1 < graphList.size() && (addFirst || addBoth)){
            while (index1 < graphList.size()){
                result.add(graphList.get(index1));
                index1++;
            }
        }
        if (index2 < graphList2.size() && (addSecond || addBoth)){
            while (index2 < graphList2.size()){
                result.add(graphList2.get(index2));
                index2++;
            }
        }
        return result;
    }

    public void sortWrtNodeSize(){
        Collections.sort(graphList, new GraphSizeComparator<T>());
    }

    public String sizeString(){
        String result = "";
        sortWrtNodeSize();
        int i = 0;
        int nodeCount = graphList.get(i).numberOfNodes(), graphCount = 1;
        while (i + 1 < graphList.size()){
            i++;
            if (graphList.get(i).numberOfNodes() == nodeCount){
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

    public GraphList<T> intersection(GraphList<T> second){
        return compareGraphLists(second, false, false, true);
    }

    public GraphList<T> difference(GraphList<T> second){
        return compareGraphLists(second, true, false, false);
    }

    public GraphList<T> union(GraphList<T> second){
        return compareGraphLists(second, true, true, true);
    }

}
