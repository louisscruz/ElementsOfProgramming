package LinkedLists;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

class LinkedListsTest {
    @Nested
    class mergeLinkedLists {
        @Test
        @DisplayName("properly merges two linked lists")
        void mergeLinkedLists() {
            ListNode<Integer> leftList = new ListNode<Integer>(1, new ListNode<Integer>(3, null));
            ListNode<Integer> rightList = new ListNode<Integer>(2, new ListNode<Integer>(4, null));
            ListNode<Integer> answerList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(4, null))));

            ListNode<Integer> answer = LinkedLists.mergeLinkedLists(leftList, rightList);

            assertTrue(answer.equals(answerList));
        }
    }

    @Nested
    class reverseListTests {
        @Test
        @DisplayName("properly reverses the correct portion of the list")
        void reverseLists() {
            ListNode<Integer> originalList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
            ListNode<Integer> answerList = new ListNode<Integer>(1, new ListNode<Integer>(3, new ListNode<Integer>(2, null)));

            ListNode<Integer> answer = LinkedLists.reverseSublist(originalList, 2, 3);

            assertTrue(answer.equals(answerList));
        }
    }

    @Nested
    class hasCycle {
        @Test
        @DisplayName("properly detects a cycle and returns the cycle point")
        void hasCycle() {
            ListNode<Integer> originalList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
            originalList.next.next.next = originalList.next;

            assertEquals(originalList.next, LinkedLists.hasCycle(originalList));
        }
    }

    @Nested
    class noncyclicOverlapTests {
        @Test
        @DisplayName("properly finds the point of overlap")
        void noncyclicOverlap() {
            ListNode<Integer> shortList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
            ListNode<Integer> longList = new ListNode<Integer>(0, shortList);

            assertEquals(shortList, LinkedLists.noncyclicOverlap(shortList, longList));
        }
    }

    @Nested
    class cyclicOverlapTests {
        @Nested
        class whenNoCycles {
            @Test
            @DisplayName("properly returns the nocycle result")
            void noCycles() {
                ListNode<Integer> shortList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
                ListNode<Integer> longList = new ListNode<Integer>(0, shortList);

                ListNode<Integer> answer = LinkedLists.cyclicOverlap(shortList, longList);

                assertTrue(answer.equals(shortList));
            }
        }

        @Nested
        class whenOneCycle {
            @Test
            @DisplayName("properly returns null")
            void oneCycle() {
                ListNode<Integer> shortList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
                shortList.next.next.next = shortList.next;
                ListNode<Integer> longList = new ListNode<Integer>(0, null);

                ListNode<Integer> answer = LinkedLists.cyclicOverlap(shortList, longList);

                assertNull(answer);
            }
        }

        @Nested
        class whenTwoCycles {
            @Test
            @DisplayName("properly returns the correct node")
            void twoCycles() {
                ListNode<Integer> shortList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
                shortList.next.next.next = shortList.next;
                ListNode<Integer> longList = new ListNode<Integer>(0, shortList);

                ListNode<Integer> answer = LinkedLists.cyclicOverlap(shortList, longList);

                assertEquals(shortList, answer);
            }
        }
    }

    @Nested
    class deleteNodeTests {
        // Give more time, I would test that the appropriate error is thrown if the node is the tail node

        @Test
        @DisplayName("properly resets node value")
        void deleteNode() {
            ListNode<Integer> list = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
            ListNode<Integer> nodeToDelete = list.next;
            ListNode<Integer> answerList = new ListNode<Integer>(1, new ListNode<Integer>(3, null));

            LinkedLists.deleteNode(nodeToDelete);

            assertTrue(list.equals(answerList));
        }
    }

    @Nested
    class removeDuplicatesTests {
        @Test
        @DisplayName("does not mutate unique lists")
        void uniqueList() {
            ListNode<Integer> list = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(3, new ListNode<Integer>(3, null))))));
            ListNode<Integer> answerList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));

            ListNode<Integer> answer = LinkedLists.uniqueList(list);

            assertTrue(answer.equals(answerList));
        }
    }

    @Nested
    class removeKthNodeTests {
        @Test
        @DisplayName("properly removes the correct node")
        void removesKthNode() {
            ListNode<Integer> list = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
            ListNode<Integer> answerList = new ListNode<Integer>(1, new ListNode<Integer>(3, null));

            LinkedLists.removeKthNode(list, 2);

            assertTrue(list.equals(answerList));
        }
    }

    @Nested
    class rightShiftTests {
        @Test
        @DisplayName("properly shifts")
        void rightShift() {
            ListNode<Integer> list = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, null)));
            ListNode<Integer> shiftedList = new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(1, null)));

            ListNode<Integer> answer = LinkedLists.rightShift(list, 1);

            assertTrue(answer.equals(shiftedList));
        }
    }

    @Nested
    class evenOddMergeTests {
        @Test
        @DisplayName("properly rotates the links")
        void evenOddMerge() {
            ListNode<Integer> list = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(4, null))));
            ListNode<Integer> answerList = new ListNode<Integer>(1, new ListNode<Integer>(3, new ListNode<Integer>(2, new ListNode<Integer>(4, null))));

            ListNode<Integer> answer = LinkedLists.evenOddMerge(list);

            assertTrue(answer.equals(answerList));
        }
    }

    @Nested
    class isLinkedListPalindromeTests {
        @Test
        @DisplayName("properly detects true")
        void truePalindrome() {
            ListNode<Integer> list = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(2, new ListNode<Integer>(1, null))));

            assertTrue(LinkedLists.isLinkedListPalindrome(list));
        }

        @Test
        @DisplayName("properly detects false")
        void falsePalindrome() {
            ListNode<Integer> list = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(2, new ListNode<Integer>(3, null))));

            assertFalse(LinkedLists.isLinkedListPalindrome(list));
        }
    }

    @Nested
    class listPivotingTests {
        @Test
        @DisplayName("properly returns the correct list")
        void listPivot() {
            ListNode<Integer> list = new ListNode<Integer>(2, new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(1, new ListNode<Integer>(3, new ListNode<Integer>(3, null))))));
            ListNode<Integer> answerList = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(3, new ListNode<Integer>(3, null))))));

            ListNode<Integer> answer = LinkedLists.listPivoting(list, 2);

            assertTrue(answer.equals(answerList));
        }
    }

    @Nested
    class addTwoNumbersTests {
        @Test
        @DisplayName("returns the correct list")
        void addTwoNumbers() {
            ListNode<Integer> num1 = new ListNode<Integer>(3, new ListNode<Integer>(1, new ListNode<Integer>(4, null)));
            ListNode<Integer> num2 = new ListNode<Integer>(7, new ListNode<Integer>(0, new ListNode<Integer>(9, null)));
            ListNode<Integer> answerList = new ListNode<Integer>(0, new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(1, null))));

            ListNode<Integer> answer = LinkedLists.addTwoNumbers(num1, num2);

            assertTrue(answer.equals(answerList));
        }
    }
}