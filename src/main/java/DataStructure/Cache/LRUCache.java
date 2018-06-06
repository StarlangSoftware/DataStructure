package DataStructure.Cache;

import java.util.HashMap;

public class LRUCache<K, T> {
    private int cacheSize;
    private HashMap<K, CacheNode<K, T>> map;
    private CacheLinkedList<K, T> cache;

    public LRUCache(int cacheSize){
        this.cacheSize = cacheSize;
        cache = new CacheLinkedList<K, T>();
        map = new HashMap<>();
    }

    public boolean contains(K key){
        return map.containsKey(key);
    }

    public T get(K key){
        if (map.containsKey(key)){
            CacheNode<K, T> cacheNode = map.get(key);
            cache.remove(cacheNode);
            cache.add(cacheNode);
            return cacheNode.getData();
        } else {
            return null;
        }
    }

    public void add(K key, T data){
        if (map.size() == cacheSize){
            CacheNode<K, T> removed = cache.remove();
            map.remove(removed.getKey());
        }
        CacheNode<K, T> cacheNode = new CacheNode<K, T>(key, data);
        cache.add(cacheNode);
        map.put(key, cacheNode);
    }
}
