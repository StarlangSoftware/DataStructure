package DataStructure.Graph;

public class Edge<T> {
    private Node<T> from;
    private Node<T> to;
    private double weight;

    public Edge(Node<T> from, Node<T> to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public boolean equals(Object secondObject){
        if (!(secondObject instanceof Node)){
            return false;
        }
        Edge<T> second = (Edge<T>) secondObject;
        return from.getLabel().equals(second.from.getLabel()) && to.getLabel().equals(second.to.getLabel());
    }

    public Node<T> to(){
        return to;
    }

    public Node<T> from(){
        return from;
    }

    public double getWeight(){
        return weight;
    }
}
