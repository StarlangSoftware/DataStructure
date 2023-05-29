package DataStructure.Heap;

import java.util.Comparator;

public abstract class Heap<T> {

    private HeapNode<T> array[];
    protected Comparator<T> comparator;
    private int count;
    private int N;

    public Heap(int N, Comparator<T> comparator){
        array = new HeapNode[N];
        count = 0;
        this.N = N;
        this.comparator = comparator;
    }

    protected abstract int compare(T data1, T data2);

    public boolean isEmpty(){
        return count == 0;
    }

    private void swapNode(int index1, int index2){
        HeapNode<T> tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private void percolateDown(int no){
        int left = 2 * no + 1;
        int right = 2 * no + 2;
        while ((left < count && compare(array[no].getData(), array[left].getData()) < 0) ||
                (right < count && compare(array[no].getData(), array[right].getData()) < 0)){
            if (right >= count || compare(array[left].getData(), array[right].getData()) > 0){
                swapNode(no, left);
                no = left;
            } else {
                swapNode(no, right);
                no = right;
            }
            left = 2 * no + 1;
            right = 2 * no + 2;
        }
    }

    private void percolateUp(int no){
        int parent = (no - 1) / 2;
        while (parent >= 0 && compare(array[parent].getData(), array[no].getData()) < 0){
            swapNode(parent, no);
            no = parent;
            parent = (no - 1) / 2;
        }
    }

    public T delete(){
        HeapNode<T> tmp;
        tmp = array[0];
        array[0] = array[count - 1];
        percolateDown(0);
        count--;
        return tmp.getData();
    }

    public void insert(T data){
        count++;
        array[count - 1] = new HeapNode<>(data);
        percolateUp(count - 1);
    }

    public T peek(){
        return array[0].getData();
    }
}
