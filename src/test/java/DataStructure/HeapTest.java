package DataStructure;

import DataStructure.Heap.Heap;
import DataStructure.Heap.MaxHeap;
import DataStructure.Heap.MinHeap;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void testMaxHeap() {
        Heap<Integer> maxHeap = new MaxHeap<Integer>(8, Comparator.<Integer>naturalOrder());
        maxHeap.insert(4);
        maxHeap.insert(6);
        maxHeap.insert(2);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(1);
        maxHeap.insert(7);
        int maxItem = maxHeap.delete();
        assertEquals(7, maxItem);
        maxItem = maxHeap.delete();
        assertEquals(6, maxItem);
        maxItem = maxHeap.delete();
        assertEquals(5, maxItem);
    }

    @Test
    public void testMinHeap() {
        Heap<Integer> minHeap = new MinHeap<>(8, Comparator.<Integer>naturalOrder());
        minHeap.insert(4);
        minHeap.insert(6);
        minHeap.insert(2);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(1);
        minHeap.insert(7);
        int minItem = minHeap.delete();
        assertEquals(1, minItem);
        minItem = minHeap.delete();
        assertEquals(2, minItem);
        minItem = minHeap.delete();
        assertEquals(3, minItem);
    }

}
