package DataStructure;

import java.io.Serializable;
import java.util.*;

public class CounterHashMap<K> extends HashMap<K, Integer> implements Serializable {

    /**
     * A constructor which calls its super.
     */
    public CounterHashMap() {
        super();
    }

    /**
     * The put method takes a K type input. If this map contains a mapping for the key, it puts this key after
     * incrementing its value by one. If his map does not contain a mapping, then it directly puts key with the value of 1.
     *
     * @param key to put.
     */
    public void put(K key) {
        if (containsKey(key)) {
            put(key, get(key) + 1);
        } else {
            put(key, 1);
        }
    }

    /**
     * The putNTimes method takes a K and an integer N as inputs. If this map contains a mapping for the key, it puts this key after
     * incrementing its value by N. If his map does not contain a mapping, then it directly puts key with the value of N.
     *
     * @param key to put.
     * @param N   to increment value.
     */
    public void putNTimes(K key, int N) {
        if (containsKey(key)) {
            put(key, get(key) + N);
        } else {
            put(key, N);
        }
    }

    /**
     * The count method takes a K as input, if this map contains a mapping for the key, it returns the value corresponding
     * this key, 0 otherwise.
     *
     * @param key to get value.
     * @return the value corresponding given key, 0 if it is not mapped.
     */
    public int count(K key) {
        if (containsKey(key)) {
            return get(key);
        } else {
            return 0;
        }
    }

    /**
     * The sumOfCounts method loops through the values contained in this map and accumulates the counts of this values.
     *
     * @return accumulated counts.
     */
    public int sumOfCounts() {
        int sum = 0;
        for (Integer count : values()) {
            sum += count;
        }
        return sum;
    }

    /**
     * The max method loops through the mappings contained in this map and if the current entry's count value is greater
     * than maxCount, which is initialized as 0, it updates the maxCount as current count and maxKey as the current count's
     * key.
     *
     * @return K type maxKey which is the maximum valued key.
     */
    public K max() {
        int maxCount = 0;
        K maxKey = null;
        for (Map.Entry<K, Integer> entry : entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    /**
     * The max method takes a threshold as input and loops through the mappings contained in this map. It accumulates the
     * count values and if the current entry's count value is greater than maxCount, which is initialized as 0,
     * it updates the maxCount as current count and maxKey as the current count's key.
     * <p>
     * At the end of the loop, if the ratio of maxCount/total is greater than the given threshold it returns maxKey, else null.
     *
     * @param threshold double value.
     * @return K type maxKey if greater than the given threshold, null otherwise.
     */
    public K max(double threshold) {
        int maxCount = 0, total = 0;
        K maxKey = null;
        for (Map.Entry<K, Integer> entry : entrySet()) {
            int count = entry.getValue();
            total += count;
            if (count > maxCount) {
                maxCount = count;
                maxKey = entry.getKey();
            }
        }
        if ((maxCount / total + 0.0) > threshold) {
            return maxKey;
        } else {
            return null;
        }
    }

    /**
     * The add method adds value of each key of toBeAdded to the current counterHashMap.
     *
     * @param toBeAdded CounterHashMap to be added to this counterHashMap.
     */
    public void add(CounterHashMap<K> toBeAdded){
        for (K value : toBeAdded.keySet()){
            putNTimes(value, toBeAdded.get(value));
        }
    }

    /**
     * The topN method takes an integer N as inout. It creates an {@link ArrayList} result and loops through the the
     * mappings contained in this map and adds each entry to the result {@link ArrayList}. Then sort this {@link ArrayList}
     * according to their values and returns an {@link ArrayList} which is a sublist of result with N elements.
     *
     * @param N Integer value for defining size of the sublist.
     * @return a sublist of N element.
     */
    public List<Entry<K, Integer>> topN(int N) {
        ArrayList<Entry<K, Integer>> result = new ArrayList<>();
        for (Map.Entry<K, Integer> entry : entrySet()) {
            result.add(entry);
        }
        result.sort((o1, o2) -> o2.getValue() - o1.getValue());
        return result.subList(0, N);
    }

    /**
     * The toString method loops through the mappings contained in this map and returns the string of each entry's key and value.
     *
     * @return String of the each entry's key and value.
     */
    public String toString() {
        String result = "";
        for (Map.Entry<K, Integer> entry : entrySet()) {
            result = result + entry.getKey().toString() + ":" + entry.getValue() + "-";
        }
        return result;
    }

}
