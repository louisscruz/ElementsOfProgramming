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
}