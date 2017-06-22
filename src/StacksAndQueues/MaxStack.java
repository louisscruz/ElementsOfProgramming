package StacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

class MaxStackNode<T> {
    public T value;
    public T max;
}

public class MaxStack<T extends Comparable<T>> {
    private Deque<MaxStackNode<T>> d = new LinkedList<>();

    public T push(T val) {
        MaxStackNode<T> newNode = new MaxStackNode<T>();
        newNode.value = val;

        if (d.isEmpty()) {
            newNode.max = val;
        } else {
            T oldMax = d.peek().max;
            if ((int)(Comparable)val.compareTo(oldMax) > 0) {
                newNode.max = val;
            } else {
                newNode.max = oldMax;
            }
        }

        d.push(newNode);

        return newNode.value;
    }

    public T pop() {
        return d.pop().value;
    }

    public boolean isEmpty() {
        return d.isEmpty();
    }

    public T peek() {
        return d.peek().value;
    }

    public T max() {
        return d.peek().max;
    }
}
