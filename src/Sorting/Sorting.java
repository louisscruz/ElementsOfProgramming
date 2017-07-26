package Sorting;

import java.util.ArrayList;
import java.util.List;

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
}
