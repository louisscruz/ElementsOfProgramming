package Sorting;

import java.util.ArrayList;
import java.util.Collections;
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
}
