package StacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

public class MaxStackQueue<T extends Comparable<T>> {
    private MaxStack<T> inData = new MaxStack<T>();
    private MaxStack<T> outData = new MaxStack<T>();

    public T enqueue(T el) {
        inData.push(el);

        return el;
    }

    public T dequeue() {
        while (!inData.isEmpty()) {
            outData.push(inData.pop());
        }

        T val = outData.pop();

        while (!outData.isEmpty()) {
            inData.push(outData.pop());
        }

        return val;
    }

    public T max() {
        return inData.max();
    }
}
