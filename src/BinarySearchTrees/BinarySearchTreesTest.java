package BinarySearchTrees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import BinarySearchTrees.BinarySearchTrees.BinarySearchTreeNode;

class BinarySearchTreesTest {
    @Nested
    class isProperTreeDepth {
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
    class isProperTreeBreadth {
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
}