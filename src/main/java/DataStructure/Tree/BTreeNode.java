package DataStructure.Tree;

import java.util.Comparator;

public class BTreeNode<T>{

    T[] K;
    int m;
    int d;
    boolean leaf;
    BTreeNode<T>[] children;

    /**
     * Constructor of the B+ Tree node. By default, it is a leaf node. Initializes the attributes.
     * @param d d in d-ary tree.
     */
    public BTreeNode(int d){
        m = 0;
        this.d = d;
        leaf = true;
        K = (T[]) new Object[2 * d + 1];
        children = new BTreeNode[2 * d + 1];
    }

    /**
     * Another constructor of a B+ Tree node. By default, it is not a leaf node. Adds two children.
     * @param d d in d-ary tree.
     * @param firstChild First child of the root node.
     * @param secondChild Second child of the root node.
     * @param newK First value in the node.
     */
    public BTreeNode(int d, BTreeNode<T> firstChild, BTreeNode<T> secondChild, T newK){
        this(d);
        leaf = false;
        m = 1;
        children[0] = firstChild;
        children[1] = secondChild;
        K[0] = newK;
    }

    /**
     * Searches the position of value in the list K. If the searched value is larger than the last value of node, we
     * need to continue the search with the rightmost child. If the searched value is smaller than the i. value of node,
     * we need to continue the search with the i. child.
     * @param value Searched value
     * @param comparator Comparator function which compares two elements.
     * @return The position of searched value in array K.
     */
    int position(T value, Comparator<T> comparator){
        if (m == 0){
            return 0;
        }
        if (comparator.compare(value, K[m - 1]) > 0){
            return m;
        } else {
            for (int i = 0; i < m; i++){
                if (comparator.compare(value, K[i]) <= 0){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Add the new value insertedK to the array K into the calculated position index.
     * @param index Place to insert new value
     * @param insertedK New value to be inserted.
     */
    private void insertIntoK(int index, T insertedK){
        for (int i = m; i > index; i--){
            K[i] = K[i - 1];
        }
        K[index] = insertedK;
    }

    /**
     * Transfers the last d values of the current node to the newNode.
     * @param newNode New node.
     */
    private void moveHalfOfTheKToNewNode(BTreeNode<T> newNode) {
        for (int i = 0; i < d; i++) {
            newNode.K[i] = K[i + d + 1];
            K[i + d + 1] = null;
        }
        newNode.m = d;
    }

    /**
     * Transfers the last d links of the current node to the newNode.
     * @param newNode New node.
     */
    private void moveHalfOfTheChildrenToNewNode(BTreeNode<T> newNode) {
        for (int i = 0 ; i < d; i++){
            newNode.children[i] = children[i + d + 1];
            children[i + d + 1] = null;
        }
    }

    /**
     * Transfers the last d values and the last d links of the current node to the newNode.
     * @param newNode New node.
     */
    private void moveHalfOfTheElementsToNewNode(BTreeNode<T> newNode){
        moveHalfOfTheKToNewNode(newNode);
        moveHalfOfTheChildrenToNewNode(newNode);
    }

    /**
     * First the function position is used to determine the node or the subtree to which the new node will be added.
     * If this subtree is a leaf node, we call the function insertLeaf that will add the value to a leaf node. If this
     * subtree is not a leaf node the function calls itself with the determined subtree. Both insertNode and insertLeaf
     * functions, if adding a new value/node to that node/subtree necessitates a new child node to be added to the
     * parent node, they will both return the new added node and the node obtained by dividing the original node. If
     * there is not such a restructuring, these functions will return null. If we add a new child node to the parent
     * node, first we open a space for that child node in the value array K, then we add this new node to the array K.
     * After adding there are two possibilities:
     * <ul>
     *     <li>After inserting the new child node, the current node did not exceed its capacity. In this case, we open
     *     space for the link, which points to the new node, in the array children and place that link inside of this
     *     array.</li>
     *     <li>After inserting the new child node, the current node exceed its capacity. In this case, we need to create
     *     newNode, transfer the last d values and the last d links of the current node to the newNode. As a last case,
     *     if the divided node is the root node, we need to create a new root node and the first child of this new root
     *     node will be b, and the second child of the new root node will be newNode.</li>
     * </ul>
     * @param value Value to be inserted into B+ tree.
     * @param comparator Comparator function to compare two elements.
     * @param isRoot If true, value is inserted as a root node, otherwise false.
     * @return If inserted node results in a creation of a node, the function returns that node, otherwise null.
     */
    public BTreeNode<T> insertNode(T value, Comparator<T> comparator, boolean isRoot){
        BTreeNode<T> s, newNode;
        int child;
        child = position(value, comparator);
        if (!children[child].leaf){
            s = children[child].insertNode(value, comparator, false);
        } else {
            s = children[child].insertLeaf(value, comparator);
        }
        if (s == null){
            return null;
        }
        insertIntoK(child, children[child].K[d]);
        children[child].K[d] = null;
        if (m < 2 * d){
            children[child + 1] = s;
            m++;
            return null;
        } else {
            newNode = new BTreeNode<>(d);
            newNode.leaf = false;
            moveHalfOfTheElementsToNewNode(newNode);
            newNode.children[d] = s;
            m = d;
            if (isRoot){
                BTreeNode<T> a = new BTreeNode<>(d, this, newNode, this.K[d]);
                this.K[d] = null;
                return a;
            } else {
                return newNode;
            }
        }
    }

    /**
     * First the function position is used to determine the position where the new value will be placed Then we open a
     * space for that value in the value array K, then we add this new value to the array K into the calculated
     * position. At this stage there are again two possibilities:
     * <ul>
     *     <li>After inserting the new value, the current leaf node did not exceed its capacity. The function returns
     *     null.</li>
     *     <li>After inserting the new value, the current leaf node exceed its capacity. In this case, we need to create
     *     the newNode, and transfer the last d values of node b to this newNode.</li>
     * </ul>
     * @param value Value to be inserted into B+ tree.
     * @param comparator Comparator function to compare two elements.
     * @return If inserted node results in a creation of a node, the function returns that node, otherwise null.
     */
    public BTreeNode<T> insertLeaf(T value, Comparator<T> comparator){
        int child;
        BTreeNode<T> newNode;
        child = position(value, comparator);
        insertIntoK(child, value);
        if (m < 2 * d){
            m++;
            return null;
        } else {
            newNode = new BTreeNode<>(d);
            moveHalfOfTheKToNewNode(newNode);
            m = d;
            return newNode;
        }
    }

}
