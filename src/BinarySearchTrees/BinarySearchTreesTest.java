package BinarySearchTrees;

import static org.junit.jupiter.api.Assertions.*;

import BinaryTrees.BinaryTreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import BinarySearchTrees.BinarySearchTrees.BinarySearchTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class BinarySearchTreesTest {
    @Nested
    class isProperTreeDepthTests {
        @Nested
        class whenTrue {
            @Test
            @DisplayName("returns true")
            void isTrue() {
                BinarySearchTrees.BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<Integer>(5);
                tree.left = new BinarySearchTreeNode<Integer>(3);
                tree.right = new BinarySearchTreeNode<Integer>(7);

                assertTrue(BinarySearchTrees.isProperTreeDepth(tree));
            }
        }

        @Nested
        class whenFalse {
            @Test
            @DisplayName("returns false")
            void isTrue() {
                BinarySearchTrees.BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<Integer>(5);
                tree.left = new BinarySearchTreeNode<Integer>(6);
                tree.right = new BinarySearchTreeNode<Integer>(7);

                assertFalse(BinarySearchTrees.isProperTreeDepth(tree));
            }
        }
    }

    @Nested
    class isProperTreeBreadthTests {
        @Nested
        class whenTrue {
            @Test
            @DisplayName("returns true")
            void isTrue() {
                BinarySearchTrees.BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<Integer>(5);
                tree.left = new BinarySearchTreeNode<Integer>(3);
                tree.right = new BinarySearchTreeNode<Integer>(7);

                assertTrue(BinarySearchTrees.isProperTreeBreadth(tree));
            }
        }

        @Nested
        class whenFalse {
            @Test
            @DisplayName("returns false")
            void isTrue() {
                BinarySearchTrees.BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<Integer>(5);
                tree.left = new BinarySearchTreeNode<Integer>(6);
                tree.right = new BinarySearchTreeNode<Integer>(7);

                assertFalse(BinarySearchTrees.isProperTreeBreadth(tree));
            }
        }
    }

    @Nested
    class firstLargerTests {
        @Nested
        class whenPresent {
            @Test
            @DisplayName("returns the correct node")
            void nextPresent() {
                BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(10);
                tree.left = new BinarySearchTreeNode<>(5);
                tree.right = new BinarySearchTreeNode<>(20);
                tree.left.right = new BinarySearchTreeNode<>(7);

                BinarySearchTreeNode<Integer> answer = BinarySearchTrees.firstLarger(tree, 5);

                assertEquals(tree.left.right, answer);
            }
        }

        @Nested
        class whenNotPresent {
            @Test
            @DisplayName("returns the null")
            void nextPresent() {
                BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(10);
                tree.left = new BinarySearchTreeNode<>(5);
                tree.right = new BinarySearchTreeNode<>(20);
                tree.left.right = new BinarySearchTreeNode<>(7);

                BinarySearchTreeNode<Integer> answer = BinarySearchTrees.firstLarger(tree, 100);

                assertNull(answer);
            }
        }
    }

    @Nested
    class firstInOrderTests {
        @Nested
        class whenPresent {
            @Test
            @DisplayName("returns the correct node")
            void nextPresent() {
                BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(5);
                tree.left = new BinarySearchTreeNode<>(3);
                tree.right = new BinarySearchTreeNode<>(10);
                tree.left.right = new BinarySearchTreeNode<>(5);

                BinarySearchTreeNode<Integer> answer = BinarySearchTrees.firstInOrder(tree, 5);

                assertEquals(tree.left.right, answer);
            }
        }

        @Nested
        class whenNotPresent {
            @Test
            @DisplayName("returns the null")
            void nextPresent() {
                BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(5);
                tree.left = new BinarySearchTreeNode<>(3);
                tree.right = new BinarySearchTreeNode<>(10);
                tree.left.right = new BinarySearchTreeNode<>(5);

                BinarySearchTreeNode<Integer> answer = BinarySearchTrees.firstInOrder(tree, 100);

                assertNull(answer);
            }
        }
    }

    @Nested
    class kLargestTests {
        @Test
        @DisplayName("returns the correct collection")
        void largest() {
            BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(5);
            tree.left = new BinarySearchTreeNode<>(3);
            tree.right = new BinarySearchTreeNode<>(10);
            tree.left.left = new BinarySearchTreeNode<>(1);
            tree.left.right = new BinarySearchTreeNode<>(5);
            tree.right.left = new BinarySearchTreeNode<>(7);
            tree.right.right = new BinarySearchTreeNode<>(11);

            List<BinarySearchTreeNode<Integer>> expected = new ArrayList<>(Arrays.asList(tree.left.right, tree, tree.right.left, tree.right, tree.right.right));

            List<BinarySearchTreeNode<Integer>> answer = BinarySearchTrees.kLargest(tree, 5);

            assertEquals(expected, answer);
        }
    }
}