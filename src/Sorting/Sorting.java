package Sorting;

import java.util.*;

public class Sorting {
    public static List<Integer> intersection(List<Integer> first, List<Integer> second) {
        List<Integer> answer = new ArrayList<>();

        int i = 0, j = 0;

        while (i < first.size() && j < second.size()) {
            int firstVal = first.get(i);
            int secondVal = second.get(j);

            if (firstVal < secondVal) {
                i++;
            } else if (firstVal > secondVal) {
                j++;
            } else {
                if (answer.size() == 0 || firstVal != answer.get(answer.size() - 1)) {
                    answer.add(firstVal);
                }
                i++;
                j++;
            }
        }

        return answer;
    }

    public static void mergeArrays(List<Integer> first, List<Integer> second) {
        int firstNull = 0;

        while (firstNull < first.size() && first.get(firstNull) != null) {
            firstNull++;
        }

        int nullStart = first.size() - firstNull;

        if (nullStart < second.size()) {
            throw new IllegalArgumentException("not enough space for arrays to be merged");
        }

        int writeIdx = first.size() - 1;

        int i = nullStart - 1, j = second.size() - 1;

        while (i >= 0 && j >= 0) {
            if (first.get(i) <= second.get(j)) {
                first.set(writeIdx--, second.get(j--));
            } else {
                first.set(writeIdx--, first.get(i--));
            }
        }

        while (j >= 0) {
            first.set(writeIdx--, second.get(j--));
        }
    }

    public static class Name implements Comparable<Name> {
        public String first;
        public String last;

        public Name(String first, String last) {
            this.first = first;
            this.last = last;
        }

        public int compareTo(Name name) {
            int cmpFirst = first.compareTo(name.first);

            if (cmpFirst != 0) return cmpFirst;

            return last.compareTo(name.last);
        }

        @Override
        public String toString() {
            return this.first + ", " + this.last;
        }
    }

    public static void uniqueNames(List<Name> names) {
        Collections.sort(names);

        int writeIdx = 1;

        for (int i = 1; i < names.size(); i++) {
            if (!names.get(i).first.equals(names.get(writeIdx).first)) {
                names.set(++writeIdx, names.get(i));
            }
        }

        names.subList(++writeIdx, names.size()).clear();
    }

    public static Integer smallestNonConstructable(List<Integer> coins) {
        Collections.sort(coins);

        int currentVal = 0;

        for (int a : coins) {
            if (currentVal + 1 < a) {
                return currentVal + 1;
            }

            currentVal += a;
        }

        return currentVal + 1;
    }

    public static class Event {
        public int start;
        public int end;

        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class Endpoint implements Comparable<Endpoint> {
        public int time;
        public boolean isStart;

        public int compareTo(Endpoint e) {
            if (time != e.time) {
                return Integer.compare(time, e.time);
            }

            return isStart && !e.isStart ? -1 : !isStart && e.isStart ? 1 : 0;
        }

        public Endpoint (int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }

    public static int simultaneousEvents(List<Event> events) {
        List<Endpoint> endpoints = new ArrayList<>();

        for (Event e : events) {
            endpoints.add(new Endpoint(e.start, true));
            endpoints.add(new Endpoint(e.end, false));
        }

        Collections.sort(endpoints);

        int current = 0, max = 0;

        for (Endpoint e : endpoints) {
            if (e.isStart) {
                current++;
                max = Math.max(current, max);
            } else {
                current--;
            }
        }

        return max;
    }

    public static class Interval implements Comparable<Interval> {
        public Endpoint left = new Endpoint();
        public Endpoint right = new Endpoint();

        private static class Endpoint {
            public boolean isClosed;
            public int val;
        }

        public Interval(int leftVal, boolean leftClosed, int rightVal, boolean rightClosed) {
            this.left.val = leftVal;
            this.left.isClosed = leftClosed;
            this.right.val = rightVal;
            this.right.isClosed = rightClosed;
        }

        public int compareTo(Interval i) {
            if (Integer.compare(left.val, i.left.val) != 0) {
                return left.val - i.left.val;
            }

            if (left.isClosed && !i.left.isClosed) {
                return -1;
            }

            return (!left.isClosed && i.left.isClosed) ? 1 : 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Interval)) {
                return false;
            }

            if (this == obj) return true;

            Interval that = (Interval)obj;

            return left.val == that.left.val && left.isClosed == that.left.isClosed;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left.val, left.isClosed);
        }
    }

    public static List<Interval> unionOfIntervals(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        Collections.sort(intervals);
        List<Interval> result = new ArrayList<>(Arrays.asList(intervals.get(0)));

        for (Interval i : intervals) {
            int lastIdx = result.size() - 1;
            boolean isNotEmpty = !result.isEmpty();
            boolean leftIsLessThanResultRight = i.left.val < result.get(lastIdx).right.val;
            boolean leftIsEqualToResultRight = i.left.val == result.get(lastIdx).right.val;
            boolean leftAndResultRightTouching = (i.left.isClosed || result.get(lastIdx).right.isClosed);
            boolean intersectionExists = leftIsLessThanResultRight;
            boolean barelyTouching = leftIsEqualToResultRight && leftAndResultRightTouching;
            boolean shouldIncrease = isNotEmpty && (intersectionExists || barelyTouching);
            if (shouldIncrease) {
                boolean isLarger = i.right.val > result.get(lastIdx).right.val;
                if (isLarger) {
                    result.get(lastIdx).right = i.right;
                }
            } else {
                result.add(i);
            }
        }

        return result;
    }

    public static class Student {
        public int age;

        public Student(int age) {
            this.age = age;
        }
    }

    public static void groupByAge(List<Student> students) {
        Map<Integer, Integer> ageToCount = new HashMap<>();

        for (Student s : students) {
            int key = s.age;

            if (ageToCount.containsKey(key)) {
                ageToCount.put(key, ageToCount.get(key) + 1);
            } else {
                ageToCount.put(key, 1);
            }
        }

        Map<Integer, Integer> ageToOffset = new HashMap<>();
        int offset = 0;

        for (Map.Entry<Integer, Integer> entry : ageToCount.entrySet()) {
            ageToOffset.put(entry.getKey(), offset);
            offset += entry.getValue();
        }

        while (!ageToOffset.isEmpty()) {
            Map.Entry<Integer, Integer> from = ageToOffset.entrySet().iterator().next();
            Integer toAge = students.get(from.getValue()).age;
            Integer toValue = ageToOffset.get(toAge);

            Collections.swap(students, from.getValue(), toValue);

            Integer count = ageToCount.get(toAge) - 1;

            ageToCount.put(toAge, count);

            if (count > 0) {
                ageToOffset.put(toAge, toValue + 1);
            } else {
                ageToOffset.remove(toAge);
            }
        }
    }

    public static class Player implements Comparable<Player> {
        public int height;

        public Player(int height) {
            this.height = height;
        }

        @Override
        public int compareTo(Player other) {
            return this.height - other.height;
        }
    }

    public static boolean properPhoto(List<Player> teamOne, List<Player> teamTwo) {
//        Assumption: teams are same size
        Collections.sort(teamOne);
        Collections.sort(teamTwo);

        boolean firstTaller = teamOne.get(0).height > teamTwo.get(0).height;

        List<Player> taller = firstTaller ? teamOne : teamTwo;
        List<Player> shorter = firstTaller ? teamTwo : teamOne;

        for (int i = 0; i < taller.size(); i++) {
            Player tallPlayer = taller.get(i);
            Player shortPlayer = shorter.get(i);
            int disparity = tallPlayer.compareTo(shortPlayer);

            if (disparity <= 0) {
                return false;
            }
        }

        return true;
    }

    public static class ListNode<T> {
        public T val;
        public ListNode<T> next;

        public ListNode(T val) {
            this.val = val;
        }
    }

    public static ListNode<Integer> listSort(ListNode<Integer> list) {
        if (list == null || list.next == null) {
            return list;
        }

        ListNode<Integer> preSlow = null, slow = list, fast = list;

        while (fast != null && fast.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        preSlow.next = null;

        return mergeSortedLists(listSort(list), listSort(slow));
    }

    public static ListNode<Integer> mergeSortedLists(ListNode<Integer> a, ListNode<Integer> b) {
        ListNode<Integer> head;

        if (a.val <= b.val) {
            head = a;
            a = a.next;
        } else {
            head = b;
            b = b.next;
        }

        ListNode<Integer> currentNode = head;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                currentNode.next = a;
                a = a.next;
            } else {
                currentNode.next = b;
                b = b.next;
            }

            currentNode = currentNode.next;
        }

        ListNode<Integer> remainder = a == null ? b : a;

        currentNode.next = remainder;

        return head;
    }
}
