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
}
