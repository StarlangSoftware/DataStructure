package DataStructure.Tree;

public class AvlTreeNode <T> extends TreeNode<T>{

    protected int height;

    public AvlTreeNode(T data) {
        super(data);
        this.height = 1;
    }
}
