package DataStructure.Graph;

public class Node<T>{
    private T label;

    public Node(T Label){
        this.label = Label;
    }

    public boolean equals(Object secondObject){
        if (!(secondObject instanceof Node)){
            return false;
        }
        Node<T> second = (Node<T>) secondObject;
        return label.equals(second.label);
    }

    public T getLabel(){
        return label;
    }

    public int hashCode(){
        return label.hashCode();
    }

    public String toString(){
        return label.toString();
    }

}
