package DataStructure.Tree;

import java.util.Comparator;

public class Tree <T>{

    protected TreeNode<T> root = null;
    protected Comparator<T> comparator;

    public Tree(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public TreeNode<T> search(T value){
        TreeNode<T> d = root;
        while (d != null){
            if (comparator.compare(d.data, value) == 0){
                return d;
            } else {
                if (comparator.compare(d.data, value) > 0){
                    d = d.left;
                } else {
                    d = d.right;
                }
            }
        }
        return null;
    }

    protected void insertChild(TreeNode<T> parent, TreeNode<T> child){
        if (parent == null) {
            root = child;
        } else {
            if (comparator.compare(child.data, parent.data) < 0) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
    }

    public void insert(TreeNode<T> node){
        TreeNode<T> y = null;
        TreeNode<T> x = root;
        while (x != null){
            y = x;
            if (comparator.compare(node.data, x.data) < 0){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        insertChild(y, node);
    }

    public void insert(T data){
        insert(new TreeNode<>(data));
    }
}
