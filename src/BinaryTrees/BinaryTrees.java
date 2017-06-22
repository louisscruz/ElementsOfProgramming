package BinaryTrees;

public class BinaryTrees {
    public static boolean isBalanced(BinaryTreeNode<Integer> root) {
        return Math.abs(root.left.height() - root.right.height()) == 0;
    }
}
