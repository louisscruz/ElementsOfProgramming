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
}
