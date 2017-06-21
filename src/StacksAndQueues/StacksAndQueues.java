package StacksAndQueues;

import java.util.Deque;
import java.util.Iterator;
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

    public static boolean isWellFormed(String s) {
        Deque<Character> leftChars = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                leftChars.addFirst(c);
            } else {
                if (leftChars.isEmpty()) {
                    return false;
                }
                if ((c == ')' && leftChars.peek() != '(') || (c == '}' && leftChars.peek() != '{') || (c == ']' && leftChars.peek() != '[')) {
                    return false;
                }
                leftChars.pop();
            }
        }

        return leftChars.isEmpty();
    }

    public static String shortestEquivalentPath(String path) {
        if (path.equals("")) {
            throw new IllegalArgumentException("Empty string is not a legal path");
        }

        Deque<String> pathNames = new LinkedList<>();

        if (path.startsWith("/")) {
            pathNames.push("/");
        }

        for (String token : path.split("/")) {
            if (token.equals("..")) {
                if (pathNames.isEmpty() || pathNames.peek().equals("..")) {
                    pathNames.push(token);
                } else {
                    if (pathNames.peek().equals("/")) {
                        throw new IllegalArgumentException("Path error");
                    }
                    pathNames.pop();
                }
            } else if (!token.equals(".") && !token.isEmpty()) {
                pathNames.push(token);
            }
        }

        StringBuilder result = new StringBuilder();

        if (!pathNames.isEmpty()) {
            Iterator<String> it = pathNames.descendingIterator();
            String prev = it.next();
            result.append(prev);

            while (it.hasNext()) {
                if (!prev.equals("/")) {
                    result.append("/");
                }
                prev = it.next();
                result.append(prev);
            }
        }

        return result.toString();
    }

    public static void setJumpOrderRec(PostingListNode L) {
        setJumpOrderHelper(L, 0);
    }

    private static int setJumpOrderHelper(PostingListNode L, int order) {
        if (L != null && L.order == -1) {
            L.order = order++;
            order = setJumpOrderHelper(L.jump, order);
            order = setJumpOrderHelper(L.next, order);
        }

        return order;
    }

    public static void setJumpOrderIter(PostingListNode L) {
        Deque<PostingListNode> s = new LinkedList();
        int order = 0;

        s.push(L);

        while (!s.isEmpty()) {
            PostingListNode curr = s.pop();

            if (curr != null && curr.order == -1) {
                curr.order = order++;
                s.push(curr.next);
                s.push(curr.jump);
            }
        }
    }

    public static Deque<Building> examineBuildings(Iterator<Integer> sequence) {
        int buildingIdx = 0;
        Deque<Building> buildings = new LinkedList<>();

        while (sequence.hasNext()) {
            Integer buildingHeight = sequence.next();

            while (!buildings.isEmpty() && (Integer.compare(buildingHeight, buildings.getLast().height) >= 0)) {
                buildings.removeLast();
            }

            buildings.addLast(new Building(buildingIdx++, buildingHeight));
        }

        return buildings;
    }
}
