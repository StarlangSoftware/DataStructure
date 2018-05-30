package DataStructure.Graph;

public class TestGraph {

    public static void main(String[] args){
        Graph<String> g = new Graph<String>(false);
        Node<String> node1 = new Node<String>("1");
        Node<String> node2 = new Node<String>("2");
        Node<String> node3 = new Node<String>("3");
        Node<String> node4 = new Node<String>("4");
        Node<String> node5 = new Node<String>("5");
        g.addEdge(node1, node2, 1);
        g.addEdge(node1, node3, 1);
        g.addEdge(node2, node4, 1);
        g.addEdge(node2, node3, 1);
        g.addEdge(node4, node5, 1);
        GraphList<String> list = g.connectedComponents();
    }
}
