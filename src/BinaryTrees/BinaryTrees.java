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

    private static class Status {
        public int num = 0;
        public BinaryTreeNode<Integer> node;
    }

    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {
        Status answer = LCAHelper(root, a, b);
        if (answer.num == 2) {
            return answer.node;
        }
        return null;
    }

    private static Status LCAHelper(BinaryTreeNode<Integer> currentNode, BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {
        if (currentNode == null) {
            return new Status();
        }

        Status leftStatus = LCAHelper(currentNode.left, a, b);
        if (leftStatus.num == 2) {
            return leftStatus;
        }

        Status rightStatus = LCAHelper(currentNode.right, a, b);
        if (rightStatus.num == 2) {
            return rightStatus;
        }

        int numNodes = leftStatus.num + rightStatus.num + (currentNode == a ? 1 : 0) + (currentNode == b ? 1 : 0);

        Status newStatus = new Status();
        newStatus.num = numNodes;
        if (numNodes == 2) newStatus.node = currentNode;

        return newStatus;
    }
}
