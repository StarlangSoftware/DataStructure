package DataStructure.Heap;

import java.util.Comparator;

/**
 * <p>The heap data structure is a binary tree structure consisting of N elements. It shows the basic properties of the
 * binary tree data structure. The heap has a root node and each node of it has at most two child nodes
 * (left and right). The root node of the tree is shown at the top, and its branches grow not to up but to down manner.
 * </p>
 *
 * <p>In a heap, if the maximum element is in the root node and all nodes are smaller than its descendants, then this heap
 * is called max-heap, if the minimum element is in the root node and all nodes are larger than its descendants, then
 * this heap is called min-heap.</p>
 * @param <T> Type of the data stored in the heap node.
 */
public abstract class Heap<T> {

    private final HeapNode<T>[] array;
    protected Comparator<T> comparator;
    private int count;
    private final int N;

    /**
     * Constructor of the heap. According to the comparator, the heap could be min or max heap.
     * @param N Maximum number of elements in the heap.
     * @param comparator Comparator function to compare two elements.
     */
    public Heap(int N, Comparator<T> comparator){
        array = new HeapNode[N];
        count = 0;
        this.N = N;
        this.comparator = comparator;
    }

    protected abstract int compare(T data1, T data2);

    /**
     * Checks if the heap is empty or not.
     * @return True if the heap is empty, false otherwise.
     */
    public boolean isEmpty(){
        return count == 0;
    }

    /**
     * Swaps two heap nodes in the heap given their indexes.
     * @param index1 Index of the first node to swap.
     * @param index2 Index of the second node to swap.
     */
    private void swapNode(int index1, int index2){
        HeapNode<T> tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * The function percolates down from a node of the tree to restore the max-heap property. Left or right children are
     * checked, if one of them is larger than the current element of the heap, the iteration continues. The iteration is,
     * determining the largest one of the node's children and switching that node's value with the current node's value.
     * We need to check if current node's left and right children exist or not. These checks are done with for the left
     * child and with for the right child.
     * @param no Index of the starting node to restore the max-heap property.
     */
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

    /**
     * The function percolates up from a node of the tree to restore the max-heap property. As long as the max-heap
     * property is corrupted, the parent and its child switch their values. We need to pay attention that, the
     * calculated index of the parent must be a valid number. In other words, while going upper levels,we need to see
     * that we can not go up if we are at the root of the tree.
     * @param no Index of the starting node to restore the max-heap property.
     */
    private void percolateUp(int no){
        int parent = (no - 1) / 2;
        while (parent >= 0 && compare(array[parent].getData(), array[no].getData()) < 0){
            swapNode(parent, no);
            no = parent;
            parent = (no - 1) / 2;
        }
    }

    /**
     * The function will return the first element, therefore the first element is stored in the variable, and the first
     * element of the heap is set to the last element of the heap. After that, in order to restore the max-heap
     * property, we percolate down the tree using the function. As a last step, the number of element in the heap is
     * decremented by one.
     * @return The first element
     */
    public T delete(){
        HeapNode<T> tmp;
        tmp = array[0];
        array[0] = array[count - 1];
        percolateDown(0);
        count--;
        return tmp.getData();
    }

    /**
     * The insertion of a new element to the heap, proceeds from the leaf nodes to the root node. First the new element
     * is added to the end of the heap, then as long as the max-heap property is corrupted, the new element switches
     * place with its parent.
     * @param data New element to be inserted.
     */
    public void insert(T data){
        count++;
        array[count - 1] = new HeapNode<>(data);
        percolateUp(count - 1);
    }

}
