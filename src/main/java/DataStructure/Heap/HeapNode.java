package DataStructure.Heap;

public class HeapNode<T>{
    private T data;

    public HeapNode(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }
}
