package DataStructure;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CounterHashMapTest {

    @Test
    public void testPut1() {
        CounterHashMap<String> counterHashMap = new CounterHashMap<>();
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item3");
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item1");
        assertEquals(3, counterHashMap.count("item1"));
        assertEquals(2, counterHashMap.count("item2"));
        assertEquals(1, counterHashMap.count("item3"));
    }

    @Test
    public void testPut2() {
        Random random = new Random();
        CounterHashMap<Integer> counterHashMap = new CounterHashMap<>();
        for (int i = 0; i < 1000; i++){
            counterHashMap.put(random.nextInt(1000));
        }
        int count = 0;
        for (int i = 0; i < 1000; i++){
            count += counterHashMap.count(i);
        }
        assertEquals(1000, count);
    }

    @Test
    public void testSumOfCounts() {
        Random random = new Random();
        CounterHashMap<Integer> counterHashMap = new CounterHashMap<>();
        for (int i = 0; i < 1000; i++){
            counterHashMap.put(random.nextInt(1000));
        }
        assertEquals(1000, counterHashMap.sumOfCounts());
    }

    @Test
    public void testPut3() {
        Random random = new Random();
        CounterHashMap<Integer> counterHashMap = new CounterHashMap<>();
        for (int i = 0; i < 1000000; i++){
            counterHashMap.put(random.nextInt(1000000));
        }
        assertEquals(counterHashMap.size() / 1000000.0, 0.632, 0.001);
    }

    @Test
    public void testPutNTimes1() {
        CounterHashMap<String> counterHashMap = new CounterHashMap<>();
        counterHashMap.putNTimes("item1", 2);
        counterHashMap.putNTimes("item2", 3);
        counterHashMap.putNTimes("item3", 6);
        counterHashMap.putNTimes("item1", 2);
        counterHashMap.putNTimes("item2", 3);
        counterHashMap.putNTimes("item1", 2);
        assertEquals(6, counterHashMap.count("item1"));
        assertEquals(6, counterHashMap.count("item2"));
        assertEquals(6, counterHashMap.count("item3"));
    }

    @Test
    public void testPutNTimes2() {
        Random random = new Random();
        CounterHashMap<Integer> counterHashMap = new CounterHashMap<>();
        for (int i = 0; i < 1000; i++){
            counterHashMap.putNTimes(random.nextInt(1000), i + 1);
        }
        assertEquals(500500, counterHashMap.sumOfCounts());
    }

    @Test
    public void testMax() {
        CounterHashMap<String> counterHashMap = new CounterHashMap<>();
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item3");
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item1");
        assertEquals("item1", counterHashMap.max());
    }

    @Test
    public void testMaxThreshold1() {
        CounterHashMap<String> counterHashMap = new CounterHashMap<>();
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item3");
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item1");
        assertEquals("item1", counterHashMap.max(0.4999));
        assertNotEquals("item1", counterHashMap.max(0.5001));
    }

    @Test
    public void testMaxThreshold2() {
        Random random = new Random();
        CounterHashMap<String> counterHashMap = new CounterHashMap<>();
        for (int i = 0; i < 1000000; i++){
            counterHashMap.put(random.nextInt(100) + "");
        }
        double probability = counterHashMap.count(counterHashMap.max()) / 1000000.0;
        assertNotNull(counterHashMap.max(probability - 0.001));
        assertNull(counterHashMap.max(probability + 0.001));
    }

    @Test
    public void testAdd1() {
        CounterHashMap<String> counterHashMap1 = new CounterHashMap<>();
        counterHashMap1.put("item1");
        counterHashMap1.put("item2");
        counterHashMap1.put("item3");
        counterHashMap1.put("item1");
        counterHashMap1.put("item2");
        counterHashMap1.put("item1");
        CounterHashMap<String> counterHashMap2 = new CounterHashMap<>();
        counterHashMap2.putNTimes("item1", 2);
        counterHashMap2.putNTimes("item2", 3);
        counterHashMap2.putNTimes("item3", 6);
        counterHashMap2.putNTimes("item1", 2);
        counterHashMap2.putNTimes("item2", 3);
        counterHashMap2.putNTimes("item1", 2);
        counterHashMap1.add(counterHashMap2);
        assertEquals(9, counterHashMap1.count("item1"));
        assertEquals(8, counterHashMap1.count("item2"));
        assertEquals(7, counterHashMap1.count("item3"));
    }

    @Test
    public void testAdd2() {
        CounterHashMap<String> counterHashMap1 = new CounterHashMap<>();
        counterHashMap1.put("item1");
        counterHashMap1.put("item2");
        counterHashMap1.put("item1");
        counterHashMap1.put("item2");
        counterHashMap1.put("item1");
        CounterHashMap<String> counterHashMap2 = new CounterHashMap<>();
        counterHashMap2.put("item4");
        counterHashMap2.putNTimes("item5", 4);
        counterHashMap2.put("item2");
        counterHashMap1.add(counterHashMap2);
        assertEquals(3, counterHashMap1.count("item1"));
        assertEquals(3, counterHashMap1.count("item2"));
        assertEquals(1, counterHashMap1.count("item4"));
        assertEquals(4, counterHashMap1.count("item5"));
    }

    @Test
    public void testAdd3() {
        CounterHashMap<Integer> counterHashMap1 = new CounterHashMap<>();
        for (int i = 0; i < 1000; i++){
            counterHashMap1.put(i);
        }
        CounterHashMap<Integer> counterHashMap2 = new CounterHashMap<>();
        for (int i = 500; i < 1000; i++){
            counterHashMap2.putNTimes(1000 + i, i + 1);
        }
        counterHashMap1.add(counterHashMap2);
        assertEquals(1500, counterHashMap1.size());
    }

    @Test
    public void testTopN1() {
        CounterHashMap<String> counterHashMap = new CounterHashMap<>();
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item3");
        counterHashMap.put("item1");
        counterHashMap.put("item2");
        counterHashMap.put("item1");
        assertEquals("item1", counterHashMap.topN(1).get(0).getKey());
        assertEquals("item2", counterHashMap.topN(2).get(1).getKey());
        assertEquals("item3", counterHashMap.topN(3).get(2).getKey());
    }

    @Test
    public void testTopN2() {
        CounterHashMap<String> counterHashMap = new CounterHashMap<>();
        for (int i = 0; i < 1000; i++){
            counterHashMap.putNTimes(i + "", 2 * i + 2);
        }
        assertEquals(990 + "", counterHashMap.topN(10).get(9).getKey());
        assertEquals(900 + "", counterHashMap.topN(100).get(99).getKey());
    }


}