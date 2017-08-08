package BinarySearchTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}
