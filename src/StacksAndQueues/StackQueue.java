package StacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

public class StackQueue<T> {
    private Deque<T> inData = new LinkedList<T>();
    private Deque<T> outData = new LinkedList<T>();

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
}
