package BinaryTrees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

    public static BinaryTreeNodeWithParent<Integer> LCAParent(BinaryTreeNodeWithParent<Integer> a, BinaryTreeNodeWithParent<Integer> b) {
        if (a == b) return a;
        if (a != b && a.parent == null && b.parent == null) return null;

        int aDepth = a.depth();
        int bDepth = b.depth();

        if (aDepth != bDepth) {
            boolean aDeeper = aDepth > bDepth;

            int diff = Math.abs(aDepth - bDepth);

            for (int i = 0; i < diff; i++) {
                if (aDeeper) {
                    a = a.parent;
                } else {
                    b = b.parent;
                }
            }
        }

        while (a != null && b != null) {
            a = a.parent;
            b = b.parent;

            if (a == b) return a;
        }

        return null;
    }

    public static boolean hasPathSum(BinaryTreeNode<Integer> root, int sum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return sum == root.data;
        }

        int diff = sum - root.data;

        return hasPathSum(root.left, diff) || hasPathSum(root.right, diff);
    }

    public static List<Integer> inOrder(BinaryTreeNode<Integer> root) {
        Deque<BinaryTreeNode<Integer>> s = new LinkedList<>();
        BinaryTreeNode<Integer> curr = root;
        List<Integer> result = new ArrayList<>();

        while (!s.isEmpty() || curr != null) {
            if (curr != null) {
                s.push(curr);
                curr = curr.left;
            } else {
                curr = s.pop();
                result.add(curr.data);
                curr = curr.right;
            }
        }

        return result;
    }
}
