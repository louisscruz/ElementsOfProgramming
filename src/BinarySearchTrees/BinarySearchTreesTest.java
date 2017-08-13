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

    @Nested
    class lowestAncestorTests {
        @Nested
        class whenPresent {
            @Test
            @DisplayName("returns the proper node")
            void present() {
                BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(5);
                tree.left = new BinarySearchTreeNode<>(3);
                tree.right = new BinarySearchTreeNode<>(10);
                tree.left.left = new BinarySearchTreeNode<>(1);
                tree.left.right = new BinarySearchTreeNode<>(5);
                tree.right.left = new BinarySearchTreeNode<>(7);
                tree.right.right = new BinarySearchTreeNode<>(11);

                BinarySearchTreeNode<Integer> answer = BinarySearchTrees.lowestAncestor(tree, 7, 11);

                assertEquals(tree.right, answer);
            }
        }

        @Nested
        class whenNotPresent {
            @Test
            @DisplayName("returns null")
            void notPresent() {
                BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(5);
                tree.left = new BinarySearchTreeNode<>(3);
                tree.right = new BinarySearchTreeNode<>(10);
                tree.left.left = new BinarySearchTreeNode<>(1);
                tree.left.right = new BinarySearchTreeNode<>(5);
                tree.right.left = new BinarySearchTreeNode<>(7);
                tree.right.right = new BinarySearchTreeNode<>(11);

                BinarySearchTreeNode<Integer> answer = BinarySearchTrees.lowestAncestor(tree, 6, 11);

                assertNull(answer);
            }
        }
    }

    @Nested
    class rebuildPreOrderTests {
        @Test
        @DisplayName("returns the correct tree")
        void rebuild() {
            List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 1, 4, 10, 7, 11));

            BinarySearchTreeNode<Integer> answer = BinarySearchTrees.rebuildPreOrder(list);

            BinarySearchTreeNode<Integer> tree = new BinarySearchTreeNode<>(5);
            tree.left = new BinarySearchTreeNode<>(3);
            tree.right = new BinarySearchTreeNode<>(10);
            tree.left.left = new BinarySearchTreeNode<>(1);
            tree.left.right = new BinarySearchTreeNode<>(4);
            tree.right.left = new BinarySearchTreeNode<>(7);
            tree.right.right = new BinarySearchTreeNode<>(11);

            assert(tree.equals(answer));
            assert(tree.left.equals(answer.left));
            assert(tree.right.equals(answer.right));
            assert(tree.left.left.equals(answer.left.left));
            assert(tree.left.right.equals(answer.left.right));
            assert(tree.right.left.equals(answer.right.left));
            assert(tree.right.right.equals(answer.right.right));
        }
    }

    @Nested
    class findMinDistanceSortedArraysTests {
        @Test
        @DisplayName("returns the smallest")
        void smallest() {
            List<Integer> first = new ArrayList<>(Arrays.asList(5, 10, 15));
            List<Integer> second = new ArrayList<>(Arrays.asList(3, 6, 9, 12, 15));
            List<Integer> third = new ArrayList<>(Arrays.asList(8, 16, 24));

            List<List<Integer>> lists = new ArrayList<>(Arrays.asList(first, second, third));

            int answer = BinarySearchTrees.findMinDistanceSortedArrays(lists);

            assertEquals(1, answer);
        }
    }
}