package LinkedLists;

import java.util.List;

public class LinkedLists {
    public static ListNode<Integer> mergeLinkedLists(ListNode<Integer> left, ListNode<Integer> right) {
        ListNode<Integer> head = new ListNode<Integer>(0, null);
        ListNode<Integer> current = head;
        ListNode<Integer> currentLeft = left, currentRight = right;

        while (currentLeft != null && currentRight != null) {
            if (currentLeft.data <= currentRight.data) {
                current.next = currentLeft;
                currentLeft = currentLeft.next;
            } else {
                current.next = currentRight;
                currentRight = currentRight.next;
            }
            current = current.next;
        }

        current.next = currentLeft != null ? currentLeft : currentRight;

        return head.next;
    }

    public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start, int finish) {
        ListNode<Integer> dummyHead = new ListNode<Integer>(0, L);
        ListNode<Integer> sublistHead = dummyHead;

        int k = 1;

        while (k++ < start) {
            sublistHead = sublistHead.next;
        }

        ListNode<Integer> sublistIter = sublistHead.next;

        while (start++ < finish) {
            ListNode<Integer> temp = sublistIter.next;
            sublistIter.next = temp.next;
            temp.next = sublistHead.next;
            sublistHead.next = temp;
        }

        return dummyHead.next;
    }
}
