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

    public void insert(T data){
        BTreeNode<T> s;
        if (root == null){
            root = new BTreeNode<T>(d);
        }
        if (root.leaf){
            s = root.insertLeaf(data, comparator);
            if (s != null){
                BTreeNode<T> tmp = root;
                root = new BTreeNode<>(d, tmp, s, tmp.K[d]);
                tmp.K[d] = null;
            }
        } else {
            s = root.insertNode(data, comparator, true);
            if (s != null){
                root = s;
            }
        }
    }
}
