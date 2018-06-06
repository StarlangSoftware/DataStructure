package DataStructure;

import java.io.Serializable;
import java.util.*;

public class CounterHashMap<K> extends HashMap<K,Integer> implements Serializable{

    public CounterHashMap(){
        super();
    }

    public void put(K key){
        if (containsKey(key)){
            put(key, get(key) + 1);
        } else {
            put(key, 1);
        }
    }

    public void putNTimes(K key, int N){
        if (containsKey(key)){
            put(key, get(key) + N);
        } else {
            put(key, N);
        }
    }

    public int count(K key){
        if (containsKey(key)){
            return get(key);
        } else {
            return 0;
        }
    }

    public int sumOfCounts(){
        int sum = 0;
        for (Integer count : values()){
            sum += count;
        }
        return sum;
    }

    public K max(){
        int maxCount = 0;
        K maxKey = null;
        for (Map.Entry<K,Integer> entry : entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public K max(double threshold){
        int maxCount = 0, total = 0;
        K maxKey = null;
        for (Map.Entry<K,Integer> entry : entrySet()) {
            int count = entry.getValue();
            total += count;
            if (count > maxCount) {
                maxCount = count;
                maxKey = entry.getKey();
            }
        }
        if ((maxCount / total + 0.0) > threshold){
            return maxKey;
        } else {
            return null;
        }
    }

    public List<Entry<K, Integer>> topN(int N){
        ArrayList<Entry<K, Integer>> result = new ArrayList<>();
        for (Map.Entry<K, Integer> entry : entrySet()) {
            result.add(entry);
        }
        result.sort((o1, o2) -> o2.getValue() - o1.getValue());
        return result.subList(0, N);
    }

    public String toString(){
        String result = "";
        for (Map.Entry<K, Integer> entry : entrySet()) {
            result = result + entry.getKey().toString() + ":" + entry.getValue() + "-";
        }
        return result;
    }

}
