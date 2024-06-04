package DataStructure.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> implements Comparable<Graph<T>> {

    private HashMap<Node<T>, ArrayList<Edge<T>>> edges;
    private HashMap<Node<T>, ArrayList<Edge<T>>> fromEdges;
    private HashSet<Node<T>> nodes;
    private boolean directed = true;

    /**
     * A constructor of {@link Graph} class which takes a boolean directed as input. Creates a {@link HashMap} for edges
     * and fromEdges, a {@link HashSet} for nodes and assigns directed value to directed variable.
     *
     * @param directed boolean input.
     */
    public Graph(boolean directed) {
        edges = new HashMap<>();
        fromEdges = new HashMap<>();
        nodes = new HashSet<>();
        this.directed = directed;
    }

    /**
     * The addEdgeToEdgeList method takes a {@link HashMap}, a {@link Node} and an {@link Edge} as inputs. If given
     * {@link ArrayList} contains given {@link Node}, it adds value of given {@link Node} at {@link HashMap} to given {@link ArrayList}. If not,
     * it creates a new {@link ArrayList} as edgeList and adds given {@link Edge} to newly created {@link ArrayList} and
     * also puts to the given {@link ArrayList}.
     *
     * @param edgeMap {@link HashMap} type input.
     * @param node    {@link Node} type input.
     * @param newEdge {@link Edge} to add {@link ArrayList}.
     */
    private void addEdgeToEdgeList(HashMap<Node<T>, ArrayList<Edge<T>>> edgeMap, Node<T> node, Edge<T> newEdge) {
        ArrayList<Edge<T>> edgeList;
        if (edgeMap.containsKey(node)) {
            edgeList = edgeMap.get(node);
            edgeList.add(newEdge);
        } else {
            edgeList = new ArrayList<>();
            edgeList.add(newEdge);
            edgeMap.put(node, edgeList);
        }
    }

    /**
     * The addToEdges method takes 2 {@link Node}s as inputs; fromNode and toNode and also the corresponding weight of the edge
     * between these two nodes. First, if given fromNode or toNode are not listed in nodes {@link HashSet}, it adds these nodes to
     * nodes {@link HashSet}. If they are listed, then it creates a new {@link Edge} between fromNode and toNode with given weight
     * and calls addEdgeToEdgeList method for adding newEdge to edges and fromEdges {@link HashSet}s.
     *
     * @param fromNode Node that starting an edge.
     * @param toNode   Node that ending an edge.
     * @param weight   between two nodes.
     */
    private void addToEdges(Node<T> fromNode, Node<T> toNode, double weight) {
        Edge<T> newEdge;
        if (!nodes.contains(fromNode)) {
            nodes.add(fromNode);
        }
        if (!nodes.contains(toNode)) {
            nodes.add(toNode);
        }
        newEdge = new Edge<T>(fromNode, toNode, weight);
        addEdgeToEdgeList(edges, fromNode, newEdge);
        addEdgeToEdgeList(fromEdges, toNode, newEdge);
    }

    /**
     * The addEdge method takes 2 {@link Node}s as inputs; fromNode and toNode and also the corresponding weight of the edge
     * between these two nodes. It calls addToEdges method to create an edge between fromNode and toNode and if the graph
     * is not directed it also calls addToEdges method to create a back edge between these nodes.
     *
     * @param fromNode Node that starting an edge.
     * @param toNode   Node that ending an edge.
     * @param weight   between two nodes.
     */
    public void addEdge(Node<T> fromNode, Node<T> toNode, double weight) {
        addToEdges(fromNode, toNode, weight);
        if (!directed) {
            addToEdges(toNode, fromNode, weight);
        }
    }

    /**
     * The containsEdge method takes two {@link Node}s as inputs; fromNode and toNode. If edges {@link HashMap} contains
     * fromNode it loops through the edges and if the label of current toNode equals to the given toNode's label then it returns true,
     * false otherwise.
     *
     * @param fromNode {@link Node} used to search for keys.
     * @param toNode   {@link Node} used to search for labels.
     * @return true if the label of to Node equals to the toNode's label, false otherwise.
     */
    public boolean containsEdge(Node<T> fromNode, Node<T> toNode) {
        if (edges.containsKey(fromNode)) {
            for (Edge<T> edge : edges.get(fromNode)) {
                if (edge.to().getLabel().equals(toNode.getLabel())) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * The numberOfNodes method returns the size of the nodes {@link HashSet}.
     *
     * @return the size of the nodes {@link HashSet}.
     */
    public int numberOfNodes() {
        return nodes.size();
    }

    /**
     * The numberOfEdges method loops through edges {@link HashMap} and accumulates the size of edges {@link HashSet} of each node.
     *
     * @return sum of the total edges of nodes.
     */
    public int numberOfEdges() {
        int sum = 0;
        for (Node<T> node : edges.keySet()) {
            sum += edges.get(node).size();
        }
        return sum;
    }

    /**
     * The nodeList method returns the nodes {@link Set}.
     *
     * @return the nodes {@link Set}.
     */
    public Set<Node<T>> nodeList() {
        return nodes;
    }

    /**
     * The breadthFirstSearchShortestPath method creates a new {@link AllPairsShortestPath} result. Then loops through the
     * nodes {@link HashSet} and calls breadthFirstSearchShortestPath with each node in nodes and puts all the shortest
     * paths from node to all other nodes into a {@link SingleSourceShortestPath}. Finally, it adds each node
     * with the corresponding {@link SingleSourceShortestPath} into newly created {@link AllPairsShortestPath} result.
     *
     * @return {@link AllPairsShortestPath} result.
     */
    public AllPairsShortestPath<T> breadthFirstSearchShortestPath() {
        AllPairsShortestPath<T> result = new AllPairsShortestPath<T>(nodeList());
        for (Node<T> startNode : nodeList()) {
            SingleSourceShortestPath<T> singleSourceShortestPath = breadthFirstSearchShortestPath(startNode);
            result.put(startNode, singleSourceShortestPath);
        }
        return result;
    }

    /**
     * The breadthFirstSearchShortestPath method takes a {@link Node} startNode as an input and creates a new {@link SingleSourceShortestPath} result.
     * Also creates a new {@link HashSet} notVisitedList then puts nodes {@link HashSet} to it and creates an {@link ArrayList} queue.
     * <p>
     * It starts with adding input startNode to the queue and removing it from notVisitedList. Then, it loops when there is an element
     * in the queue. First it gets and removes the first element from queue and assigns it to fromNode. If edges {@link HashMap} contains fromNode
     * it loops through edges {@link HashMap} and gets the toNode of each edge. If toNode is in the notVisitedList {@link HashSet},
     * it updates the resulting {@link SingleSourceShortestPath} and then removes from notVisitedList and adds to the queue.
     *
     * @param startNode {@link Node} type input as starting node.
     * @return SingleSourceShortestPath type result.
     */
    public SingleSourceShortestPath<T> breadthFirstSearchShortestPath(Node<T> startNode) {
        SingleSourceShortestPath<T> result = new SingleSourceShortestPath<T>(startNode);
        HashSet<Node<T>> notVisitedList = nodes.stream().collect(Collectors.toCollection(HashSet::new));
        ArrayList<Node<T>> queue = new ArrayList<>();
        queue.add(startNode);
        notVisitedList.remove(startNode);
        while (queue.size() > 0) {
            Node<T> fromNode = queue.remove(0);
            int currentLength = (int) result.getLength(fromNode);
            if (edges.containsKey(fromNode)) {
                for (Edge<T> edge : edges.get(fromNode)) {
                    Node<T> toNode = edge.to();
                    if (notVisitedList.contains(toNode)) {
                        result.update(toNode, currentLength + 1, fromNode);
                        notVisitedList.remove(toNode);
                        queue.add(toNode);
                    }
                }
            }
        }
        return result;
    }

    /**
     * The floydWarshall method returns an {@link AllPairsShortestPath} type result. First, it initializes the result and
     * the initial values of shortest distances are based on the shortest paths considering no intermediate vertex.
     * <p>
     * Then, loop for the intermediate, source and destination nodes. If length between source and intermediate nodes
     * and length between destination and intermediate nodes are not equal to MAX_VALUE and then
     * it finds the newLength by summing up length between source and intermediate node and intermediate and destination
     * node. If newLength is less then the length between source and destination nodes it updates the {@link AllPairsShortestPath} result.
     *
     * @return AllPairsShortestPath type result.
     */
    public AllPairsShortestPath<T> floydWarshall() {
        AllPairsShortestPath<T> result = new AllPairsShortestPath<T>(nodeList());
        for (Node<T> node : edges.keySet()) {
            for (Edge<T> edge : edges.get(node)) {
                result.update(node, edge.to(), edge.getWeight(), node);
            }
        }
        for (Node<T> node1 : nodeList()) {
            for (Node<T> node2 : nodeList()) {
                for (Node<T> node3 : nodeList()) {
                    if (result.getLength(node2, node1) != Double.MAX_VALUE && result.getLength(node1, node3) != Double.MAX_VALUE) {
                        double newLength = result.getLength(node2, node1) + result.getLength(node1, node3);
                        if (newLength < result.getLength(node2, node3)) {
                            result.update(node2, node3, newLength, node1);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * The dijkstraShortestPath method returns an {@link AllPairsShortestPath} type result. It loop through the nodes {@link HashSet}
     * and calls dijkstraShortestPath algorithm with each node in nodes then puts to the resulting {@link AllPairsShortestPath}.
     *
     * @return AllPairsShortestPath type result.
     */
    public AllPairsShortestPath<T> dijkstraShortestPath() {
        AllPairsShortestPath<T> result = new AllPairsShortestPath<T>(nodeList());
        for (Node<T> startNode : nodeList()) {
            SingleSourceShortestPath<T> singleSourceShortestPath = dijkstraShortestPath(startNode);
            result.put(startNode, singleSourceShortestPath);
        }
        return result;
    }

    /**
     * The dijkstraShortestPath method takes a {@link Node} startNode as an input and returns a {@link SingleSourceShortestPath}
     * result. First, it creates a {@link PriorityQueue} heap and adds all nodes {@link HashSet} into the heap.
     * <p>
     * While the heap is not empty, first it retrieves the head of the heap as currentNode then removes the head
     * of the head. If the currentNode is in edges {@link HashMap}, it loops through the edges and gets toNode of these
     * edges and sums up the length to the currentNode and weight of the edge to find the newPathLength. If newPathLength
     * is less then the length of the path to toNode, it updates the result.
     *
     * @param startNode {@link Node} type input.
     * @return SingleSourceShortestPath type result.
     */
    public SingleSourceShortestPath<T> dijkstraShortestPath(Node<T> startNode) {
        SingleSourceShortestPath<T> result = new SingleSourceShortestPath<T>(startNode);
        PriorityQueue<Node<T>> heap = new PriorityQueue<>(new NodeDistanceComparator<T>(result));
        heap.addAll(nodeList().stream().collect(Collectors.toList()));
        while (!heap.isEmpty()) {
            Node<T> currentNode = heap.poll();
            if (edges.containsKey(currentNode)) {
                for (Edge<T> edge : edges.get(currentNode)) {
                    Node<T> toNode = edge.to();
                    double newPathLength = result.getLength(currentNode) + edge.getWeight();
                    if (newPathLength < result.getLength(toNode)) {
                        result.update(toNode, newPathLength, currentNode);
                    }
                }
            }
        }
        return result;
    }

    /**
     * The depthFirstSearch method takes a {@link HashSet} notVisitedList, a {@link Graph} currentComponent and a {@link Node}
     * currentNode. First it creates a toList {@link ArrayList} via getting values of currentNode from edges {@link HashMap}.
     * <p>
     * Then, loops through the toList, gets toNode of edge, adds edges of currentComponent to edges and fromEdges
     * {@link HashSet}s via addToEdges method, and checks whether the edges of given {@link Graph} currentComponent contains toNode.
     * If not, it removes the toNode from notVisitedList. If edges {@link HashMap} contains toNode, it calls depthFirstSearch recursively.
     *
     * @param notVisitedList   {@link HashSet} type input.
     * @param currentComponent {@link Graph} type input.
     * @param currentNode      {@link Node} type input.
     */
    private void depthFirstSearch(HashSet<Node<T>> notVisitedList, Graph<T> currentComponent, Node<T> currentNode) {
        ArrayList<Edge<T>> toList = edges.get(currentNode);
        for (Edge<T> edge : toList) {
            Node<T> toNode = edge.to();
            boolean containsEdgesFromTo = currentComponent.edges.containsKey(toNode);
            currentComponent.addToEdges(currentNode, toNode, edge.getWeight());
            if (!containsEdgesFromTo) {
                notVisitedList.remove(toNode);
                if (edges.containsKey(toNode)) {
                    depthFirstSearch(notVisitedList, currentComponent, toNode);
                }
            }
        }
    }

    /**
     * The depthFirstSearchDirected method takes a {@link HashSet} notVisitedList, a {@link Graph} currentComponent and a {@link Node}
     * currentNode.
     * <p>
     * Firstly, it creates a toList {@link ArrayList} via getting values of currentNode from edges {@link HashMap}.
     * If the toList is not null, loops through the toList, gets toNode of edge. Then, checks whether the edges of given
     * {@link Graph} currentComponent contains toNode and assigns a boolean variable containsTo.
     * If given {@link Graph} currentComponent does not contain an edge between currentNode and toNode
     * it adds edges of currentComponent to edges and fromEdges {@link HashSet}s via addToEdges method
     * If containsTo variable is false, it removes the toNode from notVisitedList and calls depthFirstSearch recursively.
     * <p>
     * Secondly, it creates a fromList {@link ArrayList} via getting values of currentNode from fromEdges {@link HashMap}
     * and performs the same steps for fromEdges.
     *
     * @param notVisitedList   {@link HashSet} type input.
     * @param currentComponent {@link Graph} type input.
     * @param currentNode      {@link Node} type input.
     */
    private void depthFirstSearchDirected(HashSet<Node<T>> notVisitedList, Graph<T> currentComponent, Node<T> currentNode) {
        ArrayList<Edge<T>> toList = edges.get(currentNode);
        if (toList != null) {
            for (Edge<T> edge : toList) {
                Node<T> toNode = edge.to();
                boolean containsTo = currentComponent.nodes.contains(toNode);
                if (!currentComponent.containsEdge(currentNode, toNode)) {
                    currentComponent.addToEdges(currentNode, toNode, edge.getWeight());
                }
                if (!containsTo) {
                    notVisitedList.remove(toNode);
                    depthFirstSearchDirected(notVisitedList, currentComponent, toNode);
                }
            }
        }
        ArrayList<Edge<T>> fromList = fromEdges.get(currentNode);
        if (fromList != null) {
            for (Edge<T> edge : fromList) {
                Node<T> node = edge.from();
                boolean containsTo = currentComponent.nodes.contains(node);
                if (!currentComponent.containsEdge(node, currentNode)) {
                    currentComponent.addToEdges(node, currentNode, 1);
                }
                if (!containsTo) {
                    notVisitedList.remove(node);
                    depthFirstSearchDirected(notVisitedList, currentComponent, node);
                }
            }
        }
    }

    /**
     * The connectedComponents method creates a {@link GraphList} result, a {@link HashSet} as notVisitedList and assigns
     * keys of edges {@link HashMap}. While the notVisitedList is not empty, it iterates over the nodes performs a depthFirstSearch
     * after removing it from the notVisitedList. Finally, adds the resulting graph to the result {@link GraphList}.
     *
     * @return GraphList type result.
     */
    public GraphList<T> connectedComponents() {
        GraphList<T> result = new GraphList<T>();
        HashSet<Node<T>> notVisitedList = edges.keySet().stream().collect(Collectors.toCollection(HashSet::new));
        while (!notVisitedList.isEmpty()) {
            Node<T> currentNode = notVisitedList.iterator().next();
            Graph<T> currentComponent = new Graph<T>(false);
            notVisitedList.remove(currentNode);
            depthFirstSearch(notVisitedList, currentComponent, currentNode);
            result.add(currentComponent);
        }
        return result;
    }

    /**
     * The weaklyConnectedComponents method creates a {@link GraphList} result, a {@link HashSet} as notVisitedList and assigns
     * nodes {@link HashMap}. While the notVisitedList is not empty, it iterates over the nodes, performs a depthFirstSearchDirected
     * after removing it from the notVisitedList. Finally, adds the resulting graph to the result {@link GraphList}.
     *
     * @return GraphList type result.
     */
    public GraphList<T> weaklyConnectedComponents() {
        GraphList<T> result = new GraphList<T>();
        HashSet<Node<T>> notVisitedList = nodes.stream().collect(Collectors.toCollection(HashSet::new));
        while (!notVisitedList.isEmpty()) {
            Node<T> currentNode = notVisitedList.iterator().next();
            Graph<T> currentComponent = new Graph<T>(true);
            notVisitedList.remove(currentNode);
            depthFirstSearchDirected(notVisitedList, currentComponent, currentNode);
            result.add(currentComponent);
        }
        return result;
    }

    /**
     * The displayAsTree method takes a {@link Node} as a rootNode and an integer tab value. First it adds given number of
     * tabs to an empty String. Then it adds rootNode to this String. If edges {@link HashMap} contains rootNode it gets the
     * to Nodes of this rootNode and calls recursively the displayAsTree method with this to {@link Node}.
     *
     * @param rootNode Node that corresponds to root of the tree.
     * @param tab      Number of tabs that will be displayed.
     * @return result String that contains tabs, rootNode and its child nodes if any.
     */
    public String displayAsTree(Node<T> rootNode, int tab) {
        String result = "";
        for (int i = 0; i < tab; i++) {
            result = result + "\t";
        }
        result = result + rootNode.toString() + "\n";
        if (edges.containsKey(rootNode)) {
            for (Edge<T> edge : edges.get(rootNode)) {
                Node<T> to = edge.to();
                result = result + displayAsTree(to, tab + 1);
            }
        }
        return result;
    }

    /**
     * The displayAsForest method creates a new {@link HashSet} nodeList and adds nodes {@link HashSet} to nodeList.
     * Then, it removes the set view of the keys contained in this fromEdges {@link HashMap} and loops through the nodeList
     * and calls displayAsTree with each node in the list.
     *
     * @return resulting String that has tabs, rootNode and its child nodes if any.
     */
    public String displayAsForest() {
        HashSet<Node<T>> nodeList = new HashSet<>();
        nodeList.addAll(nodes);
        nodeList.removeAll(fromEdges.keySet());
        String result = "";
        for (Node<T> node : nodeList) {
            result = result + displayAsTree(node, 0);
        }
        return result;
    }

    /**
     * The nodesToString method returns the accumulated String of all node in nodes {@link HashSet}.
     *
     * @return String of all node in nodes {@link HashSet}.
     */
    public String nodesToString() {
        String result = "";
        for (Node<T> node : nodes) {
            result = result + " " + node.toString();
        }
        return result;
    }

    /**
     * The compareTo method compares the resulting String of nodesToString method with given Graph's resulting String
     * of nodesToString method.
     *
     * @param o {@link Graph} type input to compare to.
     * @return 0 if two strings lexicographically equal, -1 if input is greater and 1 if input is lesser.
     */
    public int compareTo(Graph<T> o) {
        return nodesToString().compareTo(o.nodesToString());
    }
}
