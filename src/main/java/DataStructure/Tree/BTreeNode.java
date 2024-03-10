package DataStructure.Tree;

import java.util.Comparator;

public class BTreeNode<T>{

    T[] K;
    int m;
    int d;
    boolean leaf;
    BTreeNode<T>[] children;

    public BTreeNode(int d){
        m = 0;
        this.d = d;
        leaf = true;
        K = (T[]) new Object[2 * d + 1];
        children = new BTreeNode[2 * d + 1];
    }
    public BTreeNode(int d, BTreeNode<T> firstChild, BTreeNode<T> secondChild, T newK){
        this(d);
        leaf = false;
        m = 1;
        children[0] = firstChild;
        children[1] = secondChild;
        K[0] = newK;
    }
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

    private void insertIntoK(int index, T insertedK){
        for (int i = m; i > index; i--){
            K[i] = K[i - 1];
        }
        K[index] = insertedK;
    }

    private void moveHalfOfTheKToNewNode(BTreeNode<T> newNode) {
        for (int i = 0; i < d; i++) {
            newNode.K[i] = K[i + d + 1];
            K[i + d + 1] = null;
        }
        newNode.m = d;
    }

    private void moveHalfOfTheChildrenToNewNode(BTreeNode<T> newNode) {
        for (int i = 0 ; i < d; i++){
            newNode.children[i] = children[i + d + 1];
            children[i + d + 1] = null;
        }
    }

    private void moveHalfOfTheElementsToNewNode(BTreeNode<T> newNode){
        moveHalfOfTheKToNewNode(newNode);
        moveHalfOfTheChildrenToNewNode(newNode);
    }

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
