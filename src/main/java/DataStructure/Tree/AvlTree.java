package DataStructure.Tree;

import DataStructure.Stack;

import java.util.Comparator;

public class AvlTree<T> extends Tree<T>{

    public AvlTree(Comparator<T> comparator) {
        super(comparator);
    }

    public int height(AvlTreeNode<T> d){
        if (d == null){
            return 0;
        } else {
            return d.height;
        }
    }

    private AvlTreeNode<T> rotateLeft(AvlTreeNode<T> k2){
        AvlTreeNode<T> k1 = (AvlTreeNode<T>) k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height((AvlTreeNode<T>) k2.left), height((AvlTreeNode<T>) k2.right)) + 1;
        k1.height = Math.max(height((AvlTreeNode<T>) k1.left), ((AvlTreeNode<T>) k1.right).height) + 1;
        return k1;
    }

    private AvlTreeNode<T> rotateRight(AvlTreeNode<T> k1){
        AvlTreeNode<T> k2 = (AvlTreeNode<T>) k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k2.height = Math.max(((AvlTreeNode<T>) k2.left).height, height((AvlTreeNode<T>) k2.right)) + 1;
        k1.height = Math.max(height((AvlTreeNode<T>) k1.left), height((AvlTreeNode<T>) k1.right)) + 1;
        return k2;
    }

    private AvlTreeNode<T> doubleRotateLeft(AvlTreeNode<T> k3){
        k3.left = rotateRight((AvlTreeNode<T>) k3.left);
        return rotateLeft(k3);
    }

    private AvlTreeNode<T> doubleRotateRight(AvlTreeNode<T> k1){
        k1.right = rotateLeft((AvlTreeNode<T>) k1.right);
        return rotateRight(k1);
    }

    public void insert(AvlTreeNode<T> node){
        int LEFT = 1, RIGHT = 2;
        AvlTreeNode<T> y = null, x = (AvlTreeNode<T>) root, t;
        int dir1 = 0, dir2 = 0;
        Stack<AvlTreeNode<T>> c = new Stack<>();
        while (x != null){
            y = x;
            c.push(y);
            dir1 = dir2;
            if (comparator.compare(node.data, x.data) < 0){
                x = (AvlTreeNode<T>) x.left;
                dir2 = LEFT;
            } else {
                x = (AvlTreeNode<T>) x.right;
                dir2 = RIGHT;
            }
        }
        insertChild(y, node);
        while (!c.isEmpty()){
            x = c.pop();
            x.height = Math.max(height((AvlTreeNode<T>) x.left), height((AvlTreeNode<T>) x.right)) + 1;
            if (Math.abs(height((AvlTreeNode<T>) x.left) - height((AvlTreeNode<T>) x.right)) == 2){
                if (dir1 == LEFT){
                    if (dir2 == LEFT){
                        t = rotateLeft(x);
                    } else {
                        t = doubleRotateLeft(x);
                    }
                } else {
                    if (dir2 == LEFT){
                        t = doubleRotateRight(x);
                    } else {
                        t = rotateRight(x);
                    }
                }
                y = c.pop();
                insertChild(y, t);
                break;
            }
        }
    }

    public void insert(T item){
        insert(new AvlTreeNode<>(item));
    }
}
