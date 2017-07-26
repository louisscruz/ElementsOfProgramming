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
}
