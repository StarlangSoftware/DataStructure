package DataStructure.Cache;

public class CacheNode<K, T> {
    private T data;
    private K key;
    private CacheNode<K, T> previous = null;
    private CacheNode<K, T> next = null;

    /**
     * A constructor of {@link CacheNode} class which takes a key and a data as inputs and initializes private fields with these inputs.
     *
     * @param key  K type input for representing keys.
     * @param data T type input values represented by keys.
     */
    public CacheNode(K key, T data) {
        this.key = key;
        this.data = data;
    }

    /**
     * Getter for data value.
     *
     * @return data value.
     */
    public T getData() {
        return data;
    }

    /**
     * Getter for key value.
     *
     * @return key value.
     */
    public K getKey() {
        return key;
    }

    /**
     * Getter for the previous CacheNode.
     *
     * @return previous CacheNode.
     */
    public CacheNode<K, T> getPrevious() {
        return previous;
    }

    /**
     * Getter for the next CacheNode.
     *
     * @return next CacheNode.
     */
    public CacheNode<K, T> getNext() {
        return next;
    }

    /**
     * Setter for the previous CacheNode.
     *
     * @param previous CacheNode.
     */
    public void setPrevious(CacheNode<K, T> previous) {
        this.previous = previous;
    }

    /**
     * Setter for the next CacheNode.
     *
     * @param next CacheNode.
     */
    public void setNext(CacheNode<K, T> next) {
        this.next = next;
    }
}
