package BinaryTrees;

import com.sun.source.tree.BinaryTree;

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

    public static List<Integer> preOrder(BinaryTreeNode<Integer> root) {
        Deque<BinaryTreeNode<Integer>> s = new LinkedList<>();
        s.add(root);
        List<Integer> result = new ArrayList<>();

        while (!s.isEmpty()) {
            BinaryTreeNode<Integer> curr = s.pop();
            if (curr != null) {
                if (curr.right != null) s.push(curr.right);
                if (curr.left != null) s.push(curr.left);

                result.add(curr.data);
            }
        }

        return result;
    }

    public static BinaryTreeNodeWithParent<Integer> successor(BinaryTreeNodeWithParent<Integer> node) {
        if (node.parent == null) {
            return null;
        } else if (node.right == null) {
            if (node.parent.left == node) {
                return node.parent;
            } else if (node.parent.parent == null) {
                return null;
            } else {
                return node.parent.parent;
            }
        } else {
            return (BinaryTreeNodeWithParent)node.right;
        }
    }

    public static List<Integer> inorderTraversal(BinaryTreeNodeWithParent<Integer> node) {
        BinaryTreeNodeWithParent<Integer> prev = null, curr = node;
        List<Integer> result = new ArrayList<>();

        while (curr != null) {
            BinaryTreeNodeWithParent<Integer> next;
            if (curr.parent == prev) {
                if (curr.left != null) {
                    next = (BinaryTreeNodeWithParent)curr.left;
                } else {
                    result.add(curr.data);
                    next = (curr.right != null) ? (BinaryTreeNodeWithParent)curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                result.add(curr.data);
                next = (curr.right != null) ? (BinaryTreeNodeWithParent)curr.right : curr.parent;
            } else {
                next = curr.parent;
            }

            prev = curr;
            curr = next;
        }

        return result;
    }

    public static BinaryTreeNode<Integer> fromPreorder(Integer[] input) {
        int inputIdx = 0;
        return fromPreorderAcc(input, inputIdx).node;
    }

    private static class NodeWrapper {
        public BinaryTreeNode<Integer> node;
        public int index;

        public NodeWrapper(BinaryTreeNode<Integer> node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    private static NodeWrapper fromPreorderAcc(Integer[] input, int inputIdx) {
        Integer data = input[inputIdx++];

        if (data == null) {
            return new NodeWrapper(null, inputIdx);
        }

        NodeWrapper left = fromPreorderAcc(input, inputIdx);
        NodeWrapper right = fromPreorderAcc(input, left.index);

        BinaryTreeNode<Integer> newNode = new BinaryTreeNode<Integer>();

        newNode.data = data;
        newNode.left = left.node;
        newNode.right = right.node;

        return new NodeWrapper(newNode, right.index);
    }

    public static List<BinaryTreeNode<Integer>> listOfLeaves(BinaryTreeNode<Integer> root) {
        List<BinaryTreeNode<Integer>> leaves = new ArrayList<>();

        addLeaves(root, leaves);

        return leaves;
    }

    private static void addLeaves(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> leaves) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            leaves.add(node);
        } else {
            addLeaves(node.left, leaves);
            addLeaves(node.right, leaves);
        }
    }

    public static List<BinaryTreeNode<Integer>> exteriorList(BinaryTreeNode<Integer> root) {
        List<BinaryTreeNode<Integer>> exterior = new LinkedList<>();

        if (root != null) {
            exterior.add(root);
            exterior.addAll(leftBoundaryAndLeaves(root.left, true));
            exterior.addAll(rightBoundaryAndLeaves(root.right, true));
        }

        return exterior;
    }

    private static List<BinaryTreeNode<Integer>> leftBoundaryAndLeaves(BinaryTreeNode<Integer> subtreeRoot, boolean isBoundary) {
        List<BinaryTreeNode<Integer>> result = new LinkedList<>();

        if (subtreeRoot != null) {
            if (isBoundary || isLeaf(subtreeRoot)) {
                result.add(subtreeRoot);
            }

            result.addAll(leftBoundaryAndLeaves(subtreeRoot.left, isBoundary));
            result.addAll(leftBoundaryAndLeaves(subtreeRoot.right, isBoundary && subtreeRoot.left == null));
        }

        return result;
    }

    private static List<BinaryTreeNode<Integer>> rightBoundaryAndLeaves(BinaryTreeNode<Integer> subtreeRoot, boolean isBoundary) {
        List<BinaryTreeNode<Integer>> result = new LinkedList<>();

        if (subtreeRoot != null) {
            result.addAll(rightBoundaryAndLeaves(subtreeRoot.left, isBoundary && subtreeRoot.right == null));
            result.addAll(rightBoundaryAndLeaves(subtreeRoot.right, isBoundary));
            if (isBoundary || isLeaf(subtreeRoot)) {
                result.add(subtreeRoot);
            }
        }

        return result;
    }

    private static boolean isLeaf(BinaryTreeNode<Integer> node) {
        return node.left == null && node.right == null;
    }

    public static void contructRightSibling(BinaryTreeNodeWithNext<Integer> tree) {
        BinaryTreeNodeWithNext<Integer> leftStart = tree;

        while (leftStart != null && leftStart.left != null) {
            populateLowerLevelNextField(leftStart);
            leftStart = leftStart.left;
        }
    }

    private static void populateLowerLevelNextField(BinaryTreeNodeWithNext<Integer> startNode) {
        BinaryTreeNodeWithNext<Integer> iter = startNode;

        while (iter != null) {
            iter.left.next = iter.right;

            if (iter.next != null) {
                iter.right.next = iter.next.left;
            }

            iter = iter.next;
        }
    }

    public static class BinaryTreeNodeWithLock<T> extends BinaryTreeNodeWithParent<T> {
        public BinaryTreeNodeWithLock<T> left, right, parent;
        private boolean locked = false;
        private int numLockedDescendants = 0;

        public boolean isLocked() { return locked; }

        public boolean lock() {
            if (numLockedDescendants > 0 || locked) {
                return false;
            }

            for (BinaryTreeNodeWithLock<T> iter = parent; iter != null; iter = iter.parent) {
                if (iter.isLocked()) return false;
            }

            locked = true;

            for (BinaryTreeNodeWithLock<T> iter = parent; iter != null; iter = iter.parent) {
                iter.numLockedDescendants++;
            }

            return true;
        }

        public void unlock() {
            if (isLocked()) {
                locked = false;

                for (BinaryTreeNodeWithLock<T> iter = parent; iter != null; iter = iter.parent) {
                    iter.numLockedDescendants--;
                }
            }
        }
    }
}
