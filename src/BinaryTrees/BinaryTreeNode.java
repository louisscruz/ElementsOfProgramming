package BinaryTrees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;

    public int height() {
        if (left == null && right == null) {
            return 0;
        } else if (right == null) {
            return left.height() + 1;
        } else if (left == null) {
            return right.height() + 1;
        } else {
            return Math.max(left.height(), right.height()) + 1;
        }
    }

    public boolean equals(BinaryTreeNode<T> other) {
        Deque<BinaryTreeNode<T>> list = new LinkedList<>();
        Deque<BinaryTreeNode<T>> otherList = new LinkedList<>();
        list.addFirst(this);
        otherList.addFirst(other);
        return equalsAcc(list, otherList);
    }

    private boolean equalsAcc(Deque<BinaryTreeNode<T>> list, Deque<BinaryTreeNode<T>> otherList) {
        BinaryTreeNode<T> first = list.pollLast(), second = otherList.pollLast();

        if (first == null && second == null) {
            return true;
        } else if (first == null || second == null) {
            return false;
        } else if (first.data != second.data) {
            return false;
        }

        BinaryTreeNode<T> newListLeft = first.left, newListRight = first.right;
        BinaryTreeNode<T> newOtherListLeft = second.left, newOtherListRight = second.right;

        if ((newListLeft != null && newOtherListLeft == null) || (newListLeft == null && newOtherListLeft != null)) return false;
        if ((newListRight != null && newOtherListRight == null) || (newListRight == null && newOtherListRight != null)) return false;

        if (newListLeft != null) list.addFirst(newListLeft);
        if (newListRight != null) list.addFirst(newListRight);
        if (newOtherListLeft != null) otherList.addFirst(newOtherListLeft);
        if (newOtherListRight != null) otherList.addFirst(newOtherListRight);

        return equalsAcc(list, otherList);
    }
}
