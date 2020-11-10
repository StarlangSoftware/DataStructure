package DataStructure.Cache;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class LRUCacheTest {

    @Test
    public void test1() {
        LRUCache<String, String> cache = new LRUCache<>(50000);
        cache.add("item1", "1");
        cache.add("item2", "2");
        cache.add("item3", "3");
        assertTrue(cache.contains("item2"));
        assertFalse(cache.contains("item4"));
    }

    @Test
    public void test2() {
        LRUCache<String, String> cache = new LRUCache<>(50000);
        cache.add("item1", "1");
        cache.add("item2", "2");
        cache.add("item3", "3");
        assertTrue(cache.contains("item2"));
        assertTrue(cache.contains("item1"));
    }

    @Test
    public void test3() {
        Random random = new Random();
        LRUCache<Integer, Integer> cache = new LRUCache<>(1000000);
        for (int i = 0; i < 1000000; i++){
            cache.add(i, i);
        }
        for (int j = 0; j < 100; j++) {
            long time1 = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                int data = random.nextInt(1000000) + 1000000;
                cache.add(data, data);
            }
            System.out.println(System.currentTimeMillis() - time1);
        }
    }

    @Test
    public void test4() {
        Random random = new Random();
        LRUCache<Integer, Integer> cache = new LRUCache<>(1000000);
        for (int i = 0; i < 1000000; i++){
            cache.add(random.nextInt(1000000), i);
        }
        int count = 0;
        for (int i = 0; i < 1000000; i++){
            if (cache.contains(i)){
                count++;
            }
        }
        assertEquals(0.632, count / 1000000.0, 0.001);
    }

}