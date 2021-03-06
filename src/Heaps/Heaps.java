package Heaps;

import java.util.*;

public class Heaps {
    public static class ArrayEntry {
        public Integer value;
        public Integer arrayId;

        public ArrayEntry(Integer value, Integer arrayId) {
            this.value = value;
            this.arrayId = arrayId;
        }
    }

    public static List<Integer> mergeFiles(List<List<Integer>> lists) {
        List<Iterator<Integer>> iters = new ArrayList<>(lists.size());

        for (List<Integer> array : lists) {
            iters.add(array.iterator());
        }

        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(((int)lists.size()), new Comparator<ArrayEntry>() {
            @Override
            public int compare(ArrayEntry o1, ArrayEntry o2) {
                return Integer.compare(o1.value, o2.value);
            }
        });

        for (int i = 0; i < iters.size(); i++) {
            if (iters.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(i).next(), i));
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            ArrayEntry headEntry = minHeap.poll();
            result.add(headEntry.value);

            if (iters.get(headEntry.arrayId).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(), headEntry.arrayId));
            }
        }

        return result;
    }

    private static enum SubarrayType { INCREASING, DECREASING }

    public static List<Integer> increaseDecreaseSort(List<Integer> input) {
        List<List<Integer>> sortedSubarrays = new ArrayList<>();
        SubarrayType subarrayType = SubarrayType.INCREASING;

        int startIdx = 0;

        for (int i = 1; i <= input.size(); i++) {
            if (i == input.size() || (input.get(i - 1) < input.get(i) && subarrayType == SubarrayType.DECREASING) || (input.get(i - 1) >= input.get(i) && subarrayType == SubarrayType.INCREASING)) {
                List<Integer> subList = input.subList(startIdx, i);

                if (subarrayType == SubarrayType.DECREASING) {
                    Collections.reverse(subList);
                }

                sortedSubarrays.add(subList);
                startIdx = i;
                subarrayType = (subarrayType == SubarrayType.DECREASING ? SubarrayType.INCREASING : SubarrayType.DECREASING);
            }
        }

        return mergeFiles(sortedSubarrays);
    }

    public static List<Integer> almostSorted(Iterator<Integer> list, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < k && list.hasNext(); i++) {
            minHeap.add(list.next());
        }

        while (list.hasNext()) {
            minHeap.add(list.next());
            Integer smallest = minHeap.poll();
            answer.add(smallest);
        }

        while (!minHeap.isEmpty()) {
            Integer smallest = minHeap.poll();
            answer.add(smallest);
        }

        return answer;
    }

    public static class Star implements Comparable<Star> {
        public static class StarLocation {
            public double x, y, z;

            public StarLocation(double x, double y, double z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }
        }

        public StarLocation location;

        public Star(double x, double y, double z) {
            this.location = new StarLocation(x, y, z);
        }

        public double distance() {
            return Math.sqrt(location.x * location.x + location.y * location.y + location.z * location.z);
        }

        @Override
        public int compareTo(Star other) {
            return Double.compare(this.distance(), other.distance());
        }
    }

    public static List<Star> closestStars(int k, Iterator<Star> stars) {
        PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        while (stars.hasNext()) {
            Star star = stars.next();
            maxHeap.add(star);
            if (maxHeap.size() == k + 1) {
                maxHeap.remove();
            }
        }

        List<Star> orderedStars = new ArrayList<Star>(maxHeap);
        Collections.sort(orderedStars);
        return orderedStars;
    }

    public static double onlineMedian(Iterator<Integer> sequence) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(16, Collections.reverseOrder());

        while (sequence.hasNext()) {
            int nextVal = sequence.next();

            minHeap.add(nextVal);
            maxHeap.add(minHeap.remove());

            if (minHeap.size() < maxHeap.size()) {
                minHeap.add(maxHeap.remove());
            }
        }

        return (minHeap.size() == maxHeap.size()) ? (0.5 * (minHeap.peek() + maxHeap.peek())) : minHeap.peek();
    }

    public static class HeapEntry {
        public Integer index;
        public Integer value;

        public HeapEntry(Integer index, Integer value) {
            this.index = index;
            this.value = value;
        }
    }

    private static class Compare implements Comparator<HeapEntry> {
        @Override
        public int compare(HeapEntry o1, HeapEntry o2) {
            return Integer.compare(o2.value, o1.value);
        }

        public static final Compare COMPARE_HEAP_ENTRIES = new Compare();
    }

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static List<Integer> maxMaxHeap(List<Integer> list, int k) {
        if (k <= 0) {
            return Collections.EMPTY_LIST;
        }

        PriorityQueue<HeapEntry> candidateMaxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, Compare.COMPARE_HEAP_ENTRIES);

        candidateMaxHeap.add(new HeapEntry(0, list.get(0)));

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            Integer candidateIdx = candidateMaxHeap.peek().index;
            result.add(candidateMaxHeap.remove().value);

            Integer leftChildIdx = 2 * candidateIdx + 1;
            if (leftChildIdx < list.size()) {
                candidateMaxHeap.add(new HeapEntry(leftChildIdx, list.get(leftChildIdx)));
            }

            Integer rightChildIdx = 2 * candidateIdx + 2;
            if (rightChildIdx < list.size()) {
                candidateMaxHeap.add(new HeapEntry(rightChildIdx, list.get(rightChildIdx)));
            }
        }

        return result;
    }

    private static class ValueWithRank {
        public Integer value;
        public Integer rank;

        public ValueWithRank(Integer value, Integer rank) {
            this.value = value;
            this.rank = rank;
        }
    }

    private static class StackCompare implements Comparator<ValueWithRank> {
        @Override
        public int compare(ValueWithRank o1, ValueWithRank o2) {
            return Integer.compare(o2.rank, o1.rank);
        }

        public static final StackCompare COMPARE_VALUE_WITH_RANK = new StackCompare();
    }

    public static class Stack {
        private int timeStamp = 0;
        private PriorityQueue<ValueWithRank> maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, StackCompare.COMPARE_VALUE_WITH_RANK);

        public void push(Integer x) {
            maxHeap.add(new ValueWithRank(x, timeStamp++));
        }

        public Integer pop() throws NoSuchElementException {
            return maxHeap.remove().value;
        }

        public Integer peek() {
            return maxHeap.peek().value;
        }
    }
}
