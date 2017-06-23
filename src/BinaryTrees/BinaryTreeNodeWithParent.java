package BinaryTrees;

public class BinaryTreeNodeWithParent<T> extends BinaryTreeNode<T> {
    public BinaryTreeNodeWithParent<T> parent;

    public int depth() {
        return depthHelper(0);
    }

    int depthHelper(int size) {
        if (parent == null) return size;

        return parent.depthHelper(++size);
    }
}
