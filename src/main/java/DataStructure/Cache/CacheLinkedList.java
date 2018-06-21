package DataStructure.Cache;

public class CacheLinkedList<K, T> {
    private CacheNode<K, T> head = null;
    private CacheNode<K, T> tail = null;

    /**
     * The remove method takes a CacheNode type input cacheNode. If cacheNode has a previous node, then assigns cacheNode's
     * next node as previous node's next node. If cacheNode has not got a previous node, then assigns its next node as head node.
     * Moreover, if cacheNode has a next node, then assigns cacheNode's previous node as next node's previous node; if not
     * assigns tail node's previous node as tail. By doing so it removes the cacheNode from doubly {@link java.util.LinkedList}.
     *
     * @param cacheNode {@link CacheNode} type input to remove.
     */
    public void remove(CacheNode<K, T> cacheNode) {
        CacheNode<K, T> previous = cacheNode.getPrevious();
        CacheNode<K, T> next = cacheNode.getNext();
        if (previous != null) {
            previous.setNext(next);
        } else {
            head = head.getNext();
        }
        if (next != null) {
            next.setPrevious(previous);
        } else {
            tail = tail.getPrevious();
        }
    }

    /**
     * The add method adds given {@link CacheNode} type input cacheNode to the beginning of the doubly {@link java.util.LinkedList}.
     * First it sets cacheNode's previous node as null and cacheNode's next node as head node. If head node is not null then it assigns
     * cacheNode's previous node as head node and if tail is null then it assigns cacheNode as tail.
     *
     * @param cacheNode {@link CacheNode} type input to add to the doubly {@link java.util.LinkedList}.
     */
    public void add(CacheNode<K, T> cacheNode) {
        cacheNode.setPrevious(null);
        cacheNode.setNext(head);
        if (head != null) {
            head.setPrevious(cacheNode);
        }
        head = cacheNode;
        if (tail == null) {
            tail = cacheNode;
        }
    }

    /**
     * The remove method removes the last element of the doubly {@link java.util.LinkedList}. It assigns the previous node of
     * current tail as new tail. If the current tail is null then it assigns head to null.
     *
     * @return {@link CacheNode} type output tail which is removd from doubly {@link java.util.LinkedList}.
     */
    public CacheNode<K, T> remove() {
        CacheNode<K, T> removed = tail;
        tail = tail.getPrevious();
        if (tail == null) {
            head = null;
        }
        return removed;
    }

}
