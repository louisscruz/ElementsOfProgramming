package StacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

public class StacksAndQueues {
    private static class ElementWithCachedMax {
        public Integer element;
        public Integer max;

        public ElementWithCachedMax(Integer element, Integer max) {
            this.element = element;
            this.max = max;
        }
    }

    public static class Stack {
        private Deque<ElementWithCachedMax> elementWithCachedMax = new LinkedList<>();

        public boolean empty() {
            return elementWithCachedMax.isEmpty();
        }

        public Integer max() {
            if (empty()) {
                throw new IllegalStateException("max(): empty stack");
            }

            return elementWithCachedMax.peek().max;
        }

        public Integer pop() {
            if (empty()) {
                throw new IllegalStateException("pop(): empty stack");
            }

            return elementWithCachedMax.removeFirst().element;
        }

        public void push(Integer x) {
            elementWithCachedMax.addFirst(new ElementWithCachedMax(x, Math.max(x, empty() ? x : max())));
        }
    }

    public static int rpn(String s) {
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int val = c - '0';

            if (val <= 9 && val >= 0) {
                stack.push(val);
            } else {
                int b = stack.pop();
                int a = stack.pop();
                int newVal;

                if (c == '+') {
                    newVal = a + b;
                } else if (c == '-') {
                    newVal = a - b;
                } else if (c == '/') {
                    newVal = a / b;
                } else {
                    newVal = a * b;
                }

                stack.push(newVal);
            }
        }

        return stack.pop();
    }
}
