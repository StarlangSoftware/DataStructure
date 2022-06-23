package DataStructure.Tree;

public class TreeNode <T>{

    protected T data;
    protected TreeNode<T> left = null;
    protected TreeNode<T> right = null;

    public TreeNode(T data){
        this.data = data;
    }
}
