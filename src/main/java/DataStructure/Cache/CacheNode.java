package DataStructure.Cache;

public class CacheNode<K, T> {
    private T data;
    private K key;
    private CacheNode<K, T> previous = null;
    private CacheNode<K, T> next = null;

    public CacheNode(K key, T data){
        this.key = key;
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public K getKey(){
        return key;
    }

    public CacheNode<K, T> getPrevious(){
        return previous;
    }

    public CacheNode<K, T> getNext(){
        return next;
    }

    public void setPrevious(CacheNode<K, T> previous){
        this.previous = previous;
    }

    public void setNext(CacheNode<K, T> next){
        this.next = next;
    }
}
