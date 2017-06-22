package BinaryTrees;

public class BinaryTrees {
    public static boolean isBalanced(BinaryTreeNode<Integer> root) {
        return Math.abs(root.left.height() - root.right.height()) == 0;
    }

    public static boolean isSymmetrical(BinaryTreeNode<Integer> root) {
        return checkSymmetry(root.left, root.right);
    }

    private static boolean checkSymmetry(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.data == right.data && (checkSymmetry(left.left, right.right) || checkSymmetry(left.right, right.left));
        }

        return false;
    }
}
