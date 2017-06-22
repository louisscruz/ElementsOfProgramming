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
}