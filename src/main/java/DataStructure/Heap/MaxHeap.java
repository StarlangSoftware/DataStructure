package DataStructure.Heap;

import java.util.Comparator;

public class MaxHeap<T> extends Heap<T> {

    public MaxHeap(int N, Comparator<T> comparator) {
        super(N, comparator);
    }

    @Override
    protected int compare(T data1, T data2) {
        return comparator.compare(data1, data2);
    }
}
