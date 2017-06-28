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
}
