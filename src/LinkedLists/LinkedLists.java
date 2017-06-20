package LinkedLists;

import java.util.List;
import java.util.Arrays;

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

    public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
        ListNode<Integer> fast = head, slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int cycleLength = 0;

                do {
                    cycleLength++;
                    fast = fast.next;
                } while (slow != fast);

                ListNode<Integer> scoutHead = head;

                while (cycleLength-- > 0) {
                    scoutHead = scoutHead.next;
                }

                ListNode<Integer> scoutTail = head;

                while (scoutHead != scoutTail) {
                    scoutHead = scoutHead.next;
                    scoutTail = scoutTail.next;
                }

                return scoutTail;
            }
        }

        return null;
    }

    public static ListNode<Integer> noncyclicOverlap(ListNode<Integer> first, ListNode<Integer> second) {
        int firstSize = first.length(), secondSize = second.length();

        ListNode<Integer> firstStart = first, secondStart = second;

        if (firstSize > secondSize) {
            firstStart = first.offset(firstSize - secondSize);
        } else if (secondSize > firstSize) {
            secondStart = second.offset(secondSize - firstSize);
        }

        while (firstStart != null && secondStart != null) {
            if (firstStart == secondStart) {
                return firstStart;
            }

            firstStart = firstStart.next;
            secondStart = secondStart.next;
        }

        return null;
    }

    public static ListNode<Integer> cyclicOverlap(ListNode<Integer> first, ListNode<Integer> second) {
        ListNode<Integer> firstCycle = hasCycle(first);
        ListNode<Integer> secondCycle = hasCycle(second);

        if (firstCycle == null && secondCycle == null) {
            return noncyclicOverlap(first, second);
        } else if ((firstCycle != null && secondCycle == null) || (firstCycle == null && secondCycle != null)) {
            return null;
        }

        ListNode<Integer> temp = secondCycle;

        do {
            temp = temp.next;
        } while (temp != firstCycle && temp != secondCycle);

        if (temp != firstCycle) {
            return null;
        }

        int firstStemLength = first.distance(firstCycle), secondStemLength = second.distance(secondCycle);
        int count = Math.abs(firstStemLength - secondStemLength);

        if (firstStemLength > secondStemLength) {
            first = first.offset(count);
        } else {
            second = second.offset(count);
        }

        while (first != second && first != firstCycle && second != secondCycle) {
            first = first.next;
            second = second.next;
        }

        return first == second ? first : firstCycle;
    }

    public static void deleteNode(ListNode<Integer> node) {
        if (node.next == null) throw new IllegalArgumentException();

        node.data = node.next.data;
        node.next = node.next.next;
    }

    public static ListNode<Integer> uniqueList(ListNode<Integer> list) {
        ListNode<Integer> currentNode = list;

        while (currentNode.next != null) {
            if (currentNode.next.data == currentNode.data) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }

        return list;
    }

    public static void removeKthNode(ListNode<Integer> list, int k) {
        if (k < 1) throw new IllegalArgumentException();

        ListNode<Integer> current = list, scout = list;

        while (k-- > 0 && scout != null) {
            scout = scout.next;
        }

        if (scout == null) return;

        while (scout != null) {
            scout = scout.next;
            current = current.next;
        }

        deleteNode(current);
    }

    public static ListNode<Integer> rightShift(ListNode<Integer> list, int shift) {
        ListNode<Integer> newStart = list, temp = null;

        while (shift-- > 0) {
            if (shift == 0) temp = newStart;
            newStart = newStart.next;
        }

        temp.next = null;

        ListNode<Integer> current = newStart;

        while (current.next != null) {
            current = current.next;
        }

        current.next = list;

        return newStart;
    }

    public static ListNode<Integer> evenOddMerge(ListNode<Integer> list) {
        if (list == null) return list;

        ListNode<Integer> evenDummyHead = new ListNode<Integer>(0, null);
        ListNode<Integer> oddDummyHead = new ListNode<Integer>(0, null);

        List<ListNode<Integer>> tails = Arrays.asList(evenDummyHead, oddDummyHead);

        int turn = 0;

        for (ListNode<Integer> iter = list; iter != null; iter = iter.next) {
            tails.get(turn).next = iter;
            tails.set(turn, tails.get(turn).next);
            turn ^= 1;
        }

        tails.get(1).next = null;
        tails.get(0).next = oddDummyHead.next;

        return evenDummyHead.next;
    }

    public static boolean isLinkedListPalindrome(ListNode<Integer> list) {
        ListNode<Integer> slow = list, fast = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode<Integer> firstHalfIter = list;
        ListNode<Integer> secondHalfIter = slow.reverseList();

        while (secondHalfIter != null && firstHalfIter != null) {
            if (secondHalfIter.data != firstHalfIter.data) {
                return false;
            }

            secondHalfIter = secondHalfIter.next;
            firstHalfIter = firstHalfIter.next;
        }

        return true;
    }
}
