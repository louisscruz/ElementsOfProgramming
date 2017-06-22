package BinaryTrees;

public class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;

    public int height() {
        if (left == null && right == null) {
            return 0;
        } else if (right == null) {
            return left.height() + 1;
        } else if (left == null) {
            return right.height() + 1;
        } else {
            return Math.max(left.height(), right.height()) + 1;
        }
    }
}
