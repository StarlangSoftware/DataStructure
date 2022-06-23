package DataStructure.Tree;

import java.util.Comparator;

public class BTree<T>{

    BTreeNode<T> root = null;
    Comparator<T> comparator;
    int d;

    public BTree(int d, Comparator<T> comparator){
        this.comparator = comparator;
        this.d = d;
    }

    public BTreeNode<T> search(T value){
        int child;
        BTreeNode<T> b;
        b = root;
        while (!b.leaf){
            child = b.position(value, comparator);
            if (child < b.m && b.K[child].equals(value)){
                return b;
            }
            b = b.children[child];
        }
        child = b.position(value, comparator);
        if (child < b.m && b.K[child].equals(value)){
            return b;
        }
        return null;
    }

    void modifyRoot(BTreeNode<T> firstChild, BTreeNode<T> secondChild, T newK){
        root = new BTreeNode<>(d);
        root.leaf = false;
        root.m = 1;
        root.children[0] = firstChild;
        root.children[1] = secondChild;
        root.K[0] = newK;
    }

    public void insert(T data){
        BTreeNode<T> s;
        if (root == null){
            root = new BTreeNode<T>(d);
        }
        if (root.leaf){
            s = root.insertLeaf(data, comparator);
            if (s != null){
                BTreeNode<T> tmp = root;
                modifyRoot(tmp, s, tmp.K[d]);
                tmp.K[d] = null;
            }
        } else {
            s = root.insertNode(this, data, comparator);
        }
    }
}
