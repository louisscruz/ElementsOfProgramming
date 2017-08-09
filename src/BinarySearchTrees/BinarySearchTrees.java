package BinarySearchTrees;

import java.util.*;

public class BinarySearchTrees {
    public static class BinarySearchTreeNode<T> {
        public T data;
        public BinarySearchTreeNode<T> left, right;

        public BinarySearchTreeNode(T data) {
            this.data = data;
        }

        public boolean equals(BinarySearchTreeNode<T> other) {
            return this.data == other.data;
        }
    }

    public static boolean isProperTreeDepth(BinarySearchTreeNode<Integer> tree) {
        return keysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean keysInRange(BinarySearchTreeNode<Integer> tree, Integer min, Integer max) {
        if (tree == null) {
            return true;
        } else if (Integer.compare(tree.data, min) < 0 || Integer.compare(tree.data, max) > 0) {
            return false;
        }

        return keysInRange(tree.left, min, tree.data) && keysInRange(tree.right, tree.data, max);
    }

    public static class QueueEntry {
        public BinarySearchTreeNode<Integer> node;
        public Integer lowerBound, upperBound;

        public QueueEntry(BinarySearchTreeNode<Integer> node, int lowerBound, int upperBound) {
            this.node = node;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    public static boolean isProperTreeBreadth(BinarySearchTreeNode<Integer> tree) {
        Queue<QueueEntry> queue = new LinkedList<>();

        queue.add(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));


        QueueEntry headEntry = queue.poll();

        while (headEntry != null) {
            BinarySearchTreeNode<Integer> node = headEntry.node;
            int min = headEntry.lowerBound;
            int max = headEntry.upperBound;

            boolean inBounds = (node.data >= min) && (node.data <= max);

            if (!inBounds) return false;

            if (node.left != null) {
                queue.add(new QueueEntry(node.left, min, node.data));
            }

            if (node.right != null) {
                queue.add(new QueueEntry(node.right, node.data, max));
            }

            headEntry = queue.poll();
        }

        return true;
    }

    public static BinarySearchTreeNode<Integer> firstLarger(BinarySearchTreeNode<Integer> tree, int target) {
        BinarySearchTreeNode<Integer> currentNode = tree;
        BinarySearchTreeNode<Integer> firstLarger = null;

        while (currentNode != null) {
            if (currentNode.data > target) {
                firstLarger = currentNode;
                currentNode = currentNode.left;
            } else {
                // The node is much smaller than or equal to the target.
                currentNode = currentNode.right;
            }
        }

        return firstLarger;
    }

    public static BinarySearchTreeNode<Integer> firstInOrder(BinarySearchTreeNode<Integer> tree, int target) {
        BinarySearchTreeNode<Integer> currentNode = tree;
        BinarySearchTreeNode<Integer> first = null;

        while (currentNode != null) {
            if (currentNode.data < target) {
                currentNode = currentNode.right;
            } else if (currentNode.data > target) {
                currentNode = currentNode.left;
            } else {
                first = currentNode;
                currentNode = currentNode.left;
            }
        }

        return first;
    }

    public static List<BinarySearchTreeNode<Integer>> kLargest(BinarySearchTreeNode<Integer> tree, int k) {
        List<BinarySearchTreeNode<Integer>> answer = findLargest(tree, k, new ArrayList<BinarySearchTreeNode<Integer>>());
        Collections.reverse(answer);
        return answer;
    }

    public static List<BinarySearchTreeNode<Integer>> findLargest(BinarySearchTreeNode<Integer> tree, int k, List<BinarySearchTreeNode<Integer>> answer) {
        if (tree.right != null) {
            findLargest(tree.right, k, answer);
        }

        if (answer.size() == k) return answer;

        answer.add(tree);

        if (tree.left != null) {
            findLargest(tree.left, k, answer);
        }

        return answer;
    }

    public static boolean exists(BinarySearchTreeNode<Integer> tree, int val) {
        while (tree != null) {
            if (tree.data < val) {
                tree = tree.right;
            } else if (tree.data > val) {
                tree = tree.left;
            } else {
                return true;
            }
        }

        return false;
    }

    public static BinarySearchTreeNode<Integer> lowestAncestor(BinarySearchTreeNode<Integer> tree, int a, int b) {
        BinarySearchTreeNode<Integer> current = tree, ancestor = null;

        while (current != null) {
            boolean aLess = current.data > a;
            boolean bLess = current.data > b;

            if (aLess == bLess) {
                if (aLess) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            } else {
                break;
            }
        }

        if (exists(current, a) && exists(current, b)) {
            ancestor = current;
        }

        return ancestor;
    }

    public static Integer rootIdx;

    public static BinarySearchTreeNode<Integer> rebuildPreOrder(List<Integer> preOrderSequence) {
        rootIdx = 0;
        return rebuildPreOrderOnValueRange(preOrderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static BinarySearchTreeNode<Integer> rebuildPreOrderOnValueRange(List<Integer> preOrderSequence, int min, int max) {
        if (rootIdx == preOrderSequence.size()) {
            return null;
        }

        int root = preOrderSequence.get(rootIdx);
        if (root < min || root > max) {
            return null;
        }

        rootIdx++;

        BinarySearchTreeNode<Integer> leftSubTree = rebuildPreOrderOnValueRange(preOrderSequence, min, root);
        BinarySearchTreeNode<Integer> rightSubTree = rebuildPreOrderOnValueRange(preOrderSequence, root, max);

        BinarySearchTreeNode<Integer> answer = new BinarySearchTreeNode<Integer>(root);
        answer.left = leftSubTree;
        answer.right = rightSubTree;

        return answer;
    }
}
