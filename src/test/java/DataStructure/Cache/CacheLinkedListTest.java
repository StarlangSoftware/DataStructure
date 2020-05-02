package DataStructure.Cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheLinkedListTest {

    @Test
    public void test1() {
        CacheLinkedList<String, String> cacheLinkedList = new CacheLinkedList<>();
        cacheLinkedList.add(new CacheNode<>("item1", "1"));
        cacheLinkedList.add(new CacheNode<>("item2", "2"));
        CacheNode<String, String> removed = cacheLinkedList.remove();
        assertEquals("item1", removed.getKey());
        assertEquals("1", removed.getData());
        removed = cacheLinkedList.remove();
        assertEquals("item2", removed.getKey());
        assertEquals("2", removed.getData());
    }

    @Test
    public void test2() {
        CacheLinkedList<String, String> cacheLinkedList = new CacheLinkedList<>();
        for (int i = 0; i < 1000; i++){
            cacheLinkedList.add(new CacheNode<>(i + "", i + ""));
        }
        for (int i = 0; i < 1000; i++){
            CacheNode<String, String> removed = cacheLinkedList.remove();
            assertEquals(i + "", removed.getKey());
            assertEquals(i + "", removed.getData());
        }
    }

}