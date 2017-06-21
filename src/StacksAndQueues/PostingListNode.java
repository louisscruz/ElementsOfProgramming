package StacksAndQueues;

public class PostingListNode {
    public int order = -1;
    public PostingListNode jump;
    public PostingListNode next;

    PostingListNode(PostingListNode n) {
        this.next = n;
    }
}
