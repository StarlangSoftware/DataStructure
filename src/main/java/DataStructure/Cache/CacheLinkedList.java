package DataStructure.Cache;

public class CacheLinkedList<K, T> {
    private CacheNode<K, T> head = null;
    private CacheNode<K, T> tail = null;

    public void remove(CacheNode<K, T> cacheNode){
        CacheNode<K, T> previous = cacheNode.getPrevious();
        CacheNode<K, T> next = cacheNode.getNext();
        if (previous != null){
            previous.setNext(next);
        } else {
            head = head.getNext();
        }
        if (next != null){
            next.setPrevious(previous);
        } else {
            tail = tail.getPrevious();
        }
    }

    public void add(CacheNode<K, T> cacheNode){
        cacheNode.setPrevious(null);
        cacheNode.setNext(head);
        if (head != null){
            head.setPrevious(cacheNode);
        }
        head = cacheNode;
        if (tail == null){
            tail = cacheNode;
        }
    }

    public CacheNode<K, T> remove(){
        CacheNode<K, T> removed = tail;
        tail = tail.getPrevious();
        if (tail == null){
            head = null;
        }
        return removed;
    }

}
