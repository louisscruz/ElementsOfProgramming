package LinkedLists;

public class ListNode<T> {
    public T data;
    public ListNode<T> next;

    ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public static ListNode<Integer> search(ListNode<Integer> L, int key) {
        while (L != null && L.data != key) {
            L = L.next;
        }

        return L;
    }

    public void insertAfter(ListNode<T> newNode) {
        newNode.next = this.next;
        this.next = newNode;
    }

    public void deleteAfter() {
        this.next = this.next.next;
    }

    public boolean equals(ListNode<T> node) {
        ListNode<T> currentThis = this;
        ListNode<T> currentNode = node;

        while (currentThis != currentNode) {
            if (currentThis.data != currentNode.data) return false;

            currentThis = currentThis.next;
            currentNode = currentNode.next;
        }

        return true;
    }

    public int length() {
        ListNode<T> current = this;
        int i = 1;

        while (current.next != null) {
            current = current.next;
            i++;
        }

        return i;
    }

    public ListNode<T> offset(int k) {
        ListNode<T> current = this;

        while (k > 0) {
            current = current.next;
            k--;
        }

        return current;
    }

    public int distance(ListNode<T> x) {
        ListNode<T> current = this;

        int count = 0;

        while (current != null) {
            if (current == x) {
                return count;
            }
            count++;
            current = current.next;
        }

        return -1;
    }
}
