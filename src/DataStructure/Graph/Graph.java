package DataStructure.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> implements Comparable<Graph<T>>{

    private HashMap<Node<T>, ArrayList<Edge<T>>> edges;
    private HashMap<Node<T>, ArrayList<Edge<T>>> fromEdges;
    private HashSet<Node<T>> nodes;
    private boolean directed = true;

    public Graph(boolean directed) {
        edges = new HashMap<>();
        fromEdges = new HashMap<>();
        nodes = new HashSet<>();
        this.directed = directed;
    }

    private void addEdgeToEdgeList(HashMap<Node<T>, ArrayList<Edge<T>>> edgeMap, Node<T> node, Edge<T> newEdge){
        ArrayList<Edge<T>> edgeList;
        if (edgeMap.containsKey(node)){
            edgeList = edgeMap.get(node);
            edgeList.add(newEdge);
        } else {
            edgeList = new ArrayList<>();
            edgeList.add(newEdge);
            edgeMap.put(node, edgeList);
        }
    }

    private void addToEdges(Node<T> fromNode, Node<T> toNode, double weight){
        Edge<T> newEdge;
        if (!nodes.contains(fromNode)){
            nodes.add(fromNode);
        }
        if (!nodes.contains(toNode)){
            nodes.add(toNode);
        }
        newEdge = new Edge<T>(fromNode, toNode, weight);
        addEdgeToEdgeList(edges, fromNode, newEdge);
        addEdgeToEdgeList(fromEdges, toNode, newEdge);
    }

    public void addEdge(Node<T> fromNode, Node<T> toNode, double weight){
        addToEdges(fromNode, toNode, weight);
        if (!directed){
            addToEdges(toNode, fromNode, weight);
        }
    }

    public boolean containsEdge(Node<T> fromNode, Node<T> toNode){
        if (edges.containsKey(fromNode)){
            for (Edge<T> edge : edges.get(fromNode)){
                if (edge.to().getLabel().equals(toNode.getLabel())){
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public int numberOfNodes(){
        return nodes.size();
    }

    public int numberOfEdges(){
        int sum = 0;
        for (Node<T> node : edges.keySet()){
            sum += edges.get(node).size();
        }
        return sum;
    }

    public Set<Node<T>> nodeList(){
        return nodes;
    }

    public AllPairsShortestPath<T> breadthFirstSearchShortestPath(){
        AllPairsShortestPath<T> result = new AllPairsShortestPath<T>(nodeList());
        for (Node<T> startNode : nodeList()){
            SingleSourceShortestPath<T> singleSourceShortestPath = breadthFirstSearchShortestPath(startNode);
            result.put(startNode, singleSourceShortestPath);
        }
        return result;
    }

    public SingleSourceShortestPath<T> breadthFirstSearchShortestPath(Node<T> startNode){
        SingleSourceShortestPath<T> result = new SingleSourceShortestPath<T>(startNode);
        HashSet<Node<T>> notVisitedList = nodes.stream().collect(Collectors.toCollection(HashSet::new));
        ArrayList<Node<T>> queue = new ArrayList<>();
        queue.add(startNode);
        notVisitedList.remove(startNode);
        while (queue.size() > 0){
            Node<T> fromNode = queue.remove(0);
            int currentLength = (int) result.getLength(fromNode);
            if (edges.containsKey(fromNode)){
                for (Edge<T> edge : edges.get(fromNode)){
                    Node<T> toNode = edge.to();
                    if (notVisitedList.contains(toNode)){
                        result.update(toNode, currentLength + 1, fromNode);
                        notVisitedList.remove(toNode);
                        queue.add(toNode);
                    }
                }
            }
        }
        return result;
    }

    public AllPairsShortestPath<T> floydWarshall(){
        AllPairsShortestPath<T> result = new AllPairsShortestPath<T>(nodeList());
        for (Node<T> node : edges.keySet()){
            for (Edge<T> edge: edges.get(node)){
                result.update(node, edge.to(), edge.getWeight(), node);
            }
        }
        for (Node<T> node1 : nodeList()){
            for (Node<T> node2 : nodeList()){
                for (Node<T> node3: nodeList()){
                    if (result.getLength(node2, node1) != Double.MAX_VALUE && result.getLength(node1, node3) != Double.MAX_VALUE){
                        double newLength = result.getLength(node2, node1) + result.getLength(node1, node3);
                        if (newLength < result.getLength(node2, node3)){
                            result.update(node2, node3, newLength, node1);
                        }
                    }
                }
            }
        }
        return result;
    }

    public AllPairsShortestPath<T> dijkstraShortestPath(){
        AllPairsShortestPath<T> result = new AllPairsShortestPath<T>(nodeList());
        for (Node<T> startNode : nodeList()){
            SingleSourceShortestPath<T> singleSourceShortestPath = dijkstraShortestPath(startNode);
            result.put(startNode, singleSourceShortestPath);
        }
        return result;
    }

    public SingleSourceShortestPath<T> dijkstraShortestPath(Node<T> startNode){
        SingleSourceShortestPath<T> result = new SingleSourceShortestPath<T>(startNode);
        PriorityQueue<Node<T>> heap = new PriorityQueue<>(new NodeDistanceComparator<T>(result));
        heap.addAll(nodeList().stream().collect(Collectors.toList()));
        while (!heap.isEmpty()){
            Node<T> currentNode = heap.poll();
            if (edges.containsKey(currentNode)){
                for (Edge<T> edge : edges.get(currentNode)){
                    Node<T> toNode = edge.to();
                    double newPathLength = result.getLength(currentNode) + edge.getWeight();
                    if (newPathLength < result.getLength(toNode)){
                        result.update(toNode, newPathLength, currentNode);
                    }
                }
            }
        }
        return result;
    }

    private void depthFirstSearch(HashSet<Node<T>> notVisitedList, Graph<T> currentComponent, Node<T> currentNode){
        ArrayList<Edge<T>> toList = edges.get(currentNode);
        for (Edge<T> edge : toList){
            Node<T> toNode = edge.to();
            boolean containsEdgesFromTo = currentComponent.edges.containsKey(toNode);
            currentComponent.addToEdges(currentNode, toNode, edge.getWeight());
            if (!containsEdgesFromTo){
                notVisitedList.remove(toNode);
                if (edges.containsKey(toNode)){
                    depthFirstSearch(notVisitedList, currentComponent, toNode);
                }
            }
        }
    }

    private void depthFirstSearchDirected(HashSet<Node<T>> notVisitedList, Graph<T> currentComponent, Node<T> currentNode){
        ArrayList<Edge<T>> toList = edges.get(currentNode);
        if (toList != null){
            for (Edge<T> edge : toList){
                Node<T> toNode = edge.to();
                boolean containsTo = currentComponent.nodes.contains(toNode);
                if (!currentComponent.containsEdge(currentNode, toNode)){
                    currentComponent.addToEdges(currentNode, toNode, edge.getWeight());
                }
                if (!containsTo){
                    notVisitedList.remove(toNode);
                    depthFirstSearchDirected(notVisitedList, currentComponent, toNode);
                }
            }
        }
        ArrayList<Edge<T>> fromList = fromEdges.get(currentNode);
        if (fromList != null){
            for (Edge<T> edge : fromList){
                Node<T> node = edge.from();
                boolean containsTo = currentComponent.nodes.contains(node);
                if (!currentComponent.containsEdge(node, currentNode)){
                    currentComponent.addToEdges(node, currentNode, 1);
                }
                if (!containsTo){
                    notVisitedList.remove(node);
                    depthFirstSearchDirected(notVisitedList, currentComponent, node);
                }
            }
        }
    }

    public GraphList<T> connectedComponents(){
        GraphList<T> result = new GraphList<T>();
        HashSet<Node<T>> notVisitedList = edges.keySet().stream().collect(Collectors.toCollection(HashSet::new));
        while (!notVisitedList.isEmpty()){
            Node<T> currentNode = notVisitedList.iterator().next();
            Graph<T> currentComponent = new Graph<T>(false);
            notVisitedList.remove(currentNode);
            depthFirstSearch(notVisitedList, currentComponent, currentNode);
            result.add(currentComponent);
        }
        return result;
    }

    public GraphList<T> weaklyConnectedComponents(){
        GraphList<T> result = new GraphList<T>();
        HashSet<Node<T>> notVisitedList = nodes.stream().collect(Collectors.toCollection(HashSet::new));
        while (!notVisitedList.isEmpty()){
            Node<T> currentNode = notVisitedList.iterator().next();
            Graph<T> currentComponent = new Graph<T>(true);
            notVisitedList.remove(currentNode);
            depthFirstSearchDirected(notVisitedList, currentComponent, currentNode);
            result.add(currentComponent);
        }
        return result;
    }

    public String displayAsTree(Node<T> rootNode, int tab){
        String result = "";
        for (int i = 0; i < tab; i++){
            result = result + "\t";
        }
        result = result + rootNode.toString() + "\n";
        if (edges.containsKey(rootNode)){
            for (Edge<T> edge : edges.get(rootNode)){
                Node<T> to = edge.to();
                result = result + displayAsTree(to, tab + 1);
            }
        }
        return result;
    }

    public String displayAsForest(){
        HashSet<Node<T>> nodeList = new HashSet<>();
        nodeList.addAll(nodes);
        nodeList.removeAll(fromEdges.keySet());
        String result = "";
        for (Node<T> node : nodeList){
            result = result + displayAsTree(node, 0);
        }
        return result;
    }

    public String nodesToString(){
        String result = "";
        for (Node<T> node : nodes){
            result = result + " " + node.toString();
        }
        return result;
    }

    public int compareTo(Graph<T> o) {
        return nodesToString().compareTo(o.nodesToString());
    }
}
