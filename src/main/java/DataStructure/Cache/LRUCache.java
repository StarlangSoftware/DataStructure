package DataStructure.Cache;

import java.util.HashMap;

public class LRUCache<K, T> {
    private int cacheSize;
    private HashMap<K, CacheNode<K, T>> map;
    private CacheLinkedList<K, T> cache;

    /**
     * A constructor of {@link LRUCache} class which takes cacheSize as input. It creates new {@link CacheLinkedList} and
     * {@link HashMap}.
     *
     * @param cacheSize Integer input defining cache size.
     */
    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        cache = new CacheLinkedList<K, T>();
        map = new HashMap<>();
    }

    /**
     * The contains method takes a K type input key and returns true if the {@link HashMap} has the given key, false otherwise.
     *
     * @param key K type input key.
     * @return true if the {@link HashMap} has the given key, false otherwise.
     */
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    /**
     * The get method takes K type input key and returns the least recently used value. First it checks whether the {@link HashMap}
     * has the given key, if so it gets the corresponding cacheNode. It removes that cacheNode from {@link java.util.LinkedList}
     * and adds it again to the beginning of the list since it is more likely to be used again. At the end, returns the
     * data value of that cacheNode.
     *
     * @param key K type input key.
     * @return data value if the {@link HashMap} has the given key, null otherwise.
     */
    public T get(K key) {
        if (map.containsKey(key)) {
            CacheNode<K, T> cacheNode = map.get(key);
            cache.remove(cacheNode);
            cache.add(cacheNode);
            return cacheNode.getData();
        } else {
            return null;
        }
    }

    /**
     * The add method take a key and a data as inputs. First it checks the size of the {@link HashMap}, if it is full (i.e
     * equal to the given cacheSize) then it removes the last cacheNode in the @link java.util.LinkedList}. If it has space for new entries,
     * it creates new cacheNode with given inputs and adds this cacheNode to the {@link java.util.LinkedList} and also puts
     * it to the {@link HashMap}.
     * dolysa cikar
     *
     * @param key  K type input.
     * @param data T type input
     */
    public void add(K key, T data) {
        if (map.size() == cacheSize) {
            CacheNode<K, T> removed = cache.remove();
            map.remove(removed.getKey());
        }
        CacheNode<K, T> cacheNode = new CacheNode<K, T>(key, data);
        cache.add(cacheNode);
        map.put(key, cacheNode);
    }
}
