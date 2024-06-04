package DataStructure.Graph;

public class Node<T> {
    private T label;

    /**
     * A constructor of Node class which takes a T type input Label and assigns this Label to private variable label.
     *
     * @param Label T type input.
     */
    public Node(T Label) {
        this.label = Label;
    }

    /**
     * The overridden equals method takes an Object as input and if this object is an instance of Node it casts it to Node
     * and returns the result of the equality check between label and input's label.
     *
     * @param secondObject Object type input.
     * @return true if labels are equal to each other, false otherwise.
     */
    public boolean equals(Object secondObject) {
        if (!(secondObject instanceof Node)) {
            return false;
        }
        Node<T> second = (Node<T>) secondObject;
        return label.equals(second.label);
    }

    /**
     * Getter for the label.
     *
     * @return label.
     */
    public T getLabel() {
        return label;
    }

    /**
     * The overridden hashCode method returns the hash code of the label.
     *
     * @return the hash code of the label.
     */
    public int hashCode() {
        return label.hashCode();
    }

    /**
     * The overridden toString method returns label as string.
     *
     * @return label string.
     */
    public String toString() {
        return label.toString();
    }

}
