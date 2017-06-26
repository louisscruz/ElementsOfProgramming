package BinaryTrees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreesTest {
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

    @Nested
    class pathSumTests {
        @Test
        @DisplayName("properly returns true")
        void pathTrue() {
            BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<Integer>();
            root.data = 1;
            BinaryTreeNodeWithParent<Integer> left = new BinaryTreeNodeWithParent<Integer>();
            left.data = 2;
            BinaryTreeNodeWithParent<Integer> right = new BinaryTreeNodeWithParent<Integer>();
            right.data = 3;

            root.left = left;
            root.right = right;

            assertTrue(BinaryTrees.hasPathSum(root, 3));
        }

        @Test
        @DisplayName("properly returns false")
        void pathFalse() {
            BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<Integer>();
            root.data = 1;
            BinaryTreeNodeWithParent<Integer> left = new BinaryTreeNodeWithParent<Integer>();
            left.data = 2;
            BinaryTreeNodeWithParent<Integer> right = new BinaryTreeNodeWithParent<Integer>();
            right.data = 3;

            root.left = left;
            root.right = right;

            assertFalse(BinaryTrees.hasPathSum(root, 5));
        }
    }

    @Nested
    class inOrderTests {
        @Test
        @DisplayName("returns the correct list")
        void order() {
            BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<Integer>();
            root.data = 1;
            BinaryTreeNodeWithParent<Integer> left = new BinaryTreeNodeWithParent<Integer>();
            left.data = 2;
            BinaryTreeNodeWithParent<Integer> right = new BinaryTreeNodeWithParent<Integer>();
            right.data = 3;

            root.left = left;
            root.right = right;

            List<Integer> response = BinaryTrees.inOrder(root);
            List<Integer> answerList = new ArrayList<Integer>(Arrays.asList(2, 1, 3));

            assertEquals(answerList, response);
        }
    }

    @Nested
    class preOrderTests {
        @Test
        @DisplayName("returns the correct list")
        void order() {
            BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<Integer>();
            root.data = 1;
            BinaryTreeNodeWithParent<Integer> left = new BinaryTreeNodeWithParent<Integer>();
            left.data = 2;
            BinaryTreeNodeWithParent<Integer> right = new BinaryTreeNodeWithParent<Integer>();
            right.data = 3;

            root.left = left;
            root.right = right;

            List<Integer> response = BinaryTrees.preOrder(root);
            List<Integer> answerList = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

            assertEquals(answerList, response);
        }
    }

    @Nested
    class successorTests {
        @Test
        @DisplayName("returns the correct node")
        void next() {
            BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> left = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> right = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> leftRight = new BinaryTreeNodeWithParent<Integer>();
            BinaryTreeNodeWithParent<Integer> rightLeft = new BinaryTreeNodeWithParent<Integer>();

            root.left = left;
            root.right = right;
            root.left.right = leftRight;
            root.right.left = rightLeft;

            leftRight.parent = left;
            left.parent = root;
            rightLeft.parent = right;
            right.parent = root;

            assertEquals(root, BinaryTrees.successor(leftRight));
        }
    }

    @Nested
    class inorderTraversalTests {
        @Test
        @DisplayName("returns the correct list")
        void order() {
            BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<Integer>();
            root.data = 1;
            BinaryTreeNodeWithParent<Integer> left = new BinaryTreeNodeWithParent<Integer>();
            left.data = 2;
            BinaryTreeNodeWithParent<Integer> right = new BinaryTreeNodeWithParent<Integer>();
            right.data = 3;

            root.left = left;
            root.right = right;

            List<Integer> response = BinaryTrees.inOrder(root);
            List<Integer> answerList = new ArrayList<Integer>(Arrays.asList(2, 1, 3));

            assertEquals(answerList, response);
        }
    }

    @Nested
    class treeFromPreorderTests {
        @Test
        @DisplayName("returns the correct tree")
        void fromPreorder() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> rightLeft = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.right.left = rightLeft;

            root.data = 5;
            left.data = 3;
            right.data = 7;
            leftLeft.data = 1;
            rightLeft.data = 6;


            Integer[] input = new Integer[]{5, 3, 1, null, null, null, 7, 6, null, null, null};

            BinaryTreeNode<Integer> response = BinaryTrees.fromPreorder(input);

            assertTrue(response.equals(root));
        }
    }

    @Nested
    class listOfLeavesTests {
        @Test
        @DisplayName("returns the proper list")
        void list() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> rightLeft = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.right.left = rightLeft;

            List<BinaryTreeNode<Integer>> answer = new ArrayList<>(Arrays.asList(leftLeft, rightLeft));

            assertEquals(answer, BinaryTrees.listOfLeaves(root));
        }
    }

    @Nested
    class exteriorListTests {
        @Test
        @DisplayName("returns the proper list")
        void list() {
            BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> leftLeft = new BinaryTreeNode<Integer>();
            BinaryTreeNode<Integer> rightLeft = new BinaryTreeNode<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.right.left = rightLeft;

            List<BinaryTreeNode<Integer>> answer = new ArrayList<>(Arrays.asList(root, left, leftLeft, rightLeft, right));

            assertEquals(answer, BinaryTrees.exteriorList(root));
        }
    }

    @Nested
    class nextSiblingTests {
        @Test
        @DisplayName("returns the proper lists")
        void list() {
            BinaryTreeNodeWithNext<Integer> root = new BinaryTreeNodeWithNext<Integer>();
            BinaryTreeNodeWithNext<Integer> left = new BinaryTreeNodeWithNext<Integer>();
            BinaryTreeNodeWithNext<Integer> right = new BinaryTreeNodeWithNext<Integer>();
            BinaryTreeNodeWithNext<Integer> leftLeft = new BinaryTreeNodeWithNext<Integer>();
            BinaryTreeNodeWithNext<Integer> leftRight = new BinaryTreeNodeWithNext<Integer>();
            BinaryTreeNodeWithNext<Integer> rightLeft = new BinaryTreeNodeWithNext<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.left.right = leftRight;
            root.right.left = rightLeft;

            BinaryTrees.contructRightSibling(root);

            assertEquals(left.next, right);
            assertEquals(leftLeft.next, leftRight);
            assertEquals(leftRight.next, rightLeft);
        }
    }

    @Nested
    class lockTests {
        @Test
        @DisplayName("properly locks")
        void list() {
            BinaryTrees.BinaryTreeNodeWithLock<Integer> root = new BinaryTrees.BinaryTreeNodeWithLock<Integer>();
            BinaryTrees.BinaryTreeNodeWithLock<Integer> left = new BinaryTrees.BinaryTreeNodeWithLock<Integer>();
            BinaryTrees.BinaryTreeNodeWithLock<Integer> right = new BinaryTrees.BinaryTreeNodeWithLock<Integer>();
            BinaryTrees.BinaryTreeNodeWithLock<Integer> leftLeft = new BinaryTrees.BinaryTreeNodeWithLock<Integer>();
            BinaryTrees.BinaryTreeNodeWithLock<Integer> leftRight = new BinaryTrees.BinaryTreeNodeWithLock<Integer>();
            BinaryTrees.BinaryTreeNodeWithLock<Integer> rightLeft = new BinaryTrees.BinaryTreeNodeWithLock<Integer>();

            root.left = left;
            root.right = right;
            root.left.left = leftLeft;
            root.left.right = leftRight;
            root.right.left = rightLeft;

            leftLeft.parent = left;
            leftRight.parent = left;
            left.parent = root;
            rightLeft.parent = right;
            right.parent = root;

            assertTrue(left.lock());
            assertTrue(left.isLocked());
            assertFalse(root.lock());
            assertFalse(leftLeft.lock());
            assertTrue(right.lock());
            left.unlock();
            right.unlock();
            assertTrue(root.lock());
        }
    }
}