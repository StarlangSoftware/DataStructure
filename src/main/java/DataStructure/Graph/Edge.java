package DataStructure.Graph;

public class Edge<T> {
    private Node<T> from;
    private Node<T> to;
    private double weight;

    /**
     * A constructor of {@link Edge} class which takes 3 inputs and initializes variables with these inputs.
     *
     * @param from   starting node of the edge.
     * @param to     ending node of the edge.
     * @param weight of the edge.
     */
    public Edge(Node<T> from, Node<T> to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * The overridden equals method takes an Object as input and if this object is an instance of Node it casts it to Edge
     * and returns the result of the equality check between from node's label and to node's label.
     *
     * @param secondObject Object type input.
     * @return true if labels are equal to each other, false otherwise.
     */
    public boolean equals(Object secondObject) {
        if (!(secondObject instanceof Node)) {
            return false;
        }
        Edge<T> second = (Edge<T>) secondObject;
        return from.getLabel().equals(second.from.getLabel()) && to.getLabel().equals(second.to.getLabel());
    }

    /**
     * Getter for to {@link Node}.
     *
     * @return to {@link Node}.
     */
    public Node<T> to() {
        return to;
    }

    /**
     * Getter for from {@link Node}.
     *
     * @return from {@link Node}.
     */
    public Node<T> from() {
        return from;
    }

    /**
     * Getter for weight.
     *
     * @return weight value.
     */
    public double getWeight() {
        return weight;
    }
}
