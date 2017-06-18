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
}