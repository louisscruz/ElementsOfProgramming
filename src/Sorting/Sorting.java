package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
}
