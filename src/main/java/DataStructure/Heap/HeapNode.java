package DataStructure.Heap;

public class HeapNode<T>{
    private final T data;

    /**
     * Constructor of HeapNode.
     * @param data Data to be stored in the heap node.
     */
    public HeapNode(T data){
        this.data = data;
    }

    /**
     * Mutator of the data field
     * @return Data
     */
    public T getData(){
        return data;
    }
}
