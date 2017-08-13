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

    public static class ArrayData implements Comparable<ArrayData> {
        public int val;
        public int idx;

        public ArrayData(int idx, int val) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ArrayData o) {
            int result = Integer.compare(val, o.val);
            if (result == 0) {
                result = Integer.compare(idx, o.idx);
            }
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof ArrayData)) {
                return false;
            }

            if (this == obj) {
                return true;
            }

            ArrayData that = (ArrayData)obj;
            return this.val == that.val && this.idx == that.idx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.val, this.idx);
        }
    }

    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        List<Integer> heads = new ArrayList<>(sortedArrays.size());

        for (List<Integer> arr : sortedArrays) {
            heads.add(0);
        }

        int result = Integer.MAX_VALUE;
        NavigableSet<ArrayData> currentHeads = new TreeSet<>();

        for (int i = 0; i < sortedArrays.size(); ++i) {
            currentHeads.add(new ArrayData(i, sortedArrays.get(i).get(heads.get(i))));
        }

        while (true) {
            result = Math.min(result, currentHeads.last().val - currentHeads.first().val);
            int idxNextMin = currentHeads.first().idx;
            heads.set(idxNextMin, heads.get(idxNextMin) + 1);
            if (heads.get(idxNextMin) >= sortedArrays.get(idxNextMin).size()) {
                return result;
            }
            currentHeads.pollFirst();
            currentHeads.add(new ArrayData(idxNextMin, sortedArrays.get(idxNextMin).get(heads.get(idxNextMin))));
        }
    }

    public static class ABSqrt2 implements Comparable<ABSqrt2> {
        public int a, b;
        public double val;

        public ABSqrt2(int a, int b) {
            this.a = a;
            this.b = b;
            this.val = a + (b * Math.sqrt(2));
        }

        @Override
        public int compareTo(ABSqrt2 other) {
            return Double.compare(this.val, other.val);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (!(obj instanceof ABSqrt2)) {
                return false;
            }

            ABSqrt2 other = (ABSqrt2)obj;

            return other.val == this.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.a, this.b);
        }
    }

    public static List<ABSqrt2> ab2First(int k) {
        NavigableSet<ABSqrt2> candidates = new TreeSet<>();

        candidates.add(new ABSqrt2(0, 0));

        List<ABSqrt2> result = new ArrayList<>();

        while (result.size() < k) {
            ABSqrt2 smallest = candidates.pollFirst();
            result.add(smallest);

            candidates.add(new ABSqrt2(smallest.a + 1, smallest.b));
            candidates.add(new ABSqrt2(smallest.a, smallest.b + 1));
        }

        return result;
    }

    public static List<ABSqrt2> ab2Second(int k) {
        int i = 0, j = 0;

        List<ABSqrt2> result = new ArrayList<>();

        if (k == 0) return result;

        result.add(new ABSqrt2(0, 0));

        while (result.size() < k) {
            ABSqrt2 firstA = result.get(i);
            ABSqrt2 firstB = result.get(j);

            ABSqrt2 nextA = new ABSqrt2(firstA.a + 1, firstA.b);
            ABSqrt2 nextB = new ABSqrt2(firstB.a, firstB.b + 1);

            int cmp = nextA.compareTo(nextB);

            if (cmp < 0) {
                result.add(nextA);
                ++i;
            } else if (cmp > 0) {
                result.add(nextB);
                ++j;
            } else {
                result.add(nextA);
                ++i;
                ++j;
            }
        }

        return result;
    }
}
