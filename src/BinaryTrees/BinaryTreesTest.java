package BinaryTrees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreesTest<T> {
    @Nested
    class balancedTests {
        @Test
        @DisplayName("properly returns true")
        void balanced() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;

            assertTrue(BinaryTrees.isBalanced(root));
        }

        @Test
        @DisplayName("properly returns false")
        void unbalanced() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;

            assertFalse(BinaryTrees.isBalanced(root));
        }
    }

    @Nested
    class symmetricalTests {
        @Test
        @DisplayName("properly returns true")
        void symmetrical() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;

            assertTrue(BinaryTrees.isSymmetrical(root));
        }

        @Test
        @DisplayName("properly returns false")
        void asymmetrical() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> rightLeft = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.right.left = rightLeft;

            assertFalse(BinaryTrees.isSymmetrical(root));
        }
    }

    @Nested
    class lcaTests {
        @Test
        @DisplayName("returns the proper node")
        void ancestor() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> rightLeft = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.right.left = rightLeft;

            assertEquals(root, BinaryTrees.LCA(root, leftLeft, right));
        }
    }

    @Nested
    class lcaParentTests {
        @Test
        @DisplayName("returns the proper node")
        void ancestor() {
            BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> left = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> right = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> leftLeft = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> rightLeft = new BinaryTreeNodeWithParent<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.right.left = rightLeft;

            leftLeft.parent = left;
            left.parent = root;
            rightLeft.parent = right;
            right.parent = root;

            assertEquals(root, BinaryTrees.LCAParent(leftLeft, right));
        }
    }
}