package Searching;

import java.util.*;

public class Searching {
    public static int findFirst(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, result = -1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);

            if (list.get(mid) > k) {
                right = mid - 1;
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }

        return result;
    }

    public static int findFirstGreater(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, result = -1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);

            if (list.get(mid) > k) {
                right = mid - 1;
            } else if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                left = mid + 1;
                result = mid + 1;
            }
        }

        return (result < list.size()) ? result : -1;
    }

    public static int findEqualsIndex(List<Integer> list) {
        Integer first = 0, last = list.size() - 1, result = -1;
        Integer mid, midVal;

        while (first <= last) {
            mid = first + ((last - first) / 2);
            midVal = list.get(mid);

            if (mid == midVal) {
                result = mid;
                last = mid - 1;
            } else if (mid < midVal) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }

        return result;
    }

    public static int findCyclicFirst(List<Integer> list) {
        Integer first = 0, last = list.size() - 1, lastVal = list.get(last);
        Integer result = -1, mid, midVal;

        while (first <= last) {
            mid = first + ((last - first) / 2);
            midVal = list.get(mid);

            if (midVal >= lastVal) {
                first = mid + 1;
            } else if (midVal < lastVal) {
                result = mid;
                last = mid - 1;
            }
        }

        return result;
    }

    public static int intSquareRoot(int i) {
        if (i <= 0) throw new IllegalArgumentException();
        if (i <= 3) return 1;
        int left = 2, right;

        while (left * left * left * left <= i) {
            left = left * left;
        }

        right = left * left;

        while (left + 1 < right) {
            int mid = left + ((right - left) / 2);
            int midSquared = mid * mid;

            if (midSquared < i) {
                left = mid;
            } else if (midSquared > i) {
                right = mid;
            } else {
                return mid;
            }
        }

        return left;
    }

    public static double squareRoot(double i) {
        double lower, upper;

        if (i <= 1.0) {
            lower = 0;
            upper = 1;
        } else {
            lower = 1;
            upper = i;
        }

        while (compare(upper, lower) != Ordering.EQUAL) {
            double mid = lower + (0.5 * (upper - lower));
            double midSquared = mid * mid;

            if (compare(midSquared, i) == Ordering.LARGER) {
                upper = mid;
            } else {
                lower = mid;
            }
        }

        return lower;
    }

    private static enum Ordering { SMALLER, EQUAL, LARGER }

    private static Ordering compare(double a, double b) {
        final double EPSILON = 0.00001;

        double diff = (a - b) / b;

        return diff < -EPSILON ? Ordering.SMALLER : (diff > EPSILON ? Ordering.LARGER : Ordering.EQUAL);
    }

    public static int[] twoDimensionalSearch(ArrayList<ArrayList<Integer>> matrix, int val) {
        final int x = matrix.get(0).size(), y = matrix.size();
        int row = 0, col = x - 1;

        while (col >= 0 && row < y) {
            int currentVal = matrix.get(row).get(col);

            if (val == currentVal) return new int[]{row, col};

            if (currentVal < val) {
                row++;
            } else {
                col--;
            }
        }

        return null;
    }

    public static class MinMax {
        public int min;
        public int max;

        public MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public static MinMax minMax(int a, int b) {
            return (a < b) ? new MinMax(a, b) : new MinMax(b, a);
        }
    }

    public static MinMax findMinMax(List<Integer> list) {
        if (list.size() < 1) return null;
        if (list.size() == 1) {
            final int val = list.get(0);
            return new MinMax(val, val);
        }

        final int first = list.get(0), second = list.get(1);

        MinMax globalMinMax = MinMax.minMax(first, second);

        for (int i = 2; i + 1 < list.size(); i += 2) {
            final int a = list.get(i), b = list.get(i + 1);
            final MinMax localMinMax = MinMax.minMax(a, b);
            globalMinMax = new MinMax(Math.min(globalMinMax.min, localMinMax.min), Math.max(globalMinMax.max, localMinMax.max));
        }

        if (list.size() % 2 != 0) {
            final int last = list.get(list.size() - 1);
            globalMinMax = new MinMax(Math.min(globalMinMax.min, last), Math.max(globalMinMax.max, last));
        }

        return globalMinMax;
    }

    private static class GreaterThan implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return (a > b) ? -1 : (a.equals(b)) ? 0 : 1;
        }
    }

    public static Integer quickSelect(List<Integer> list, int val) {
        if (val > list.size()) return null;

        int left = 0, right = list.size() - 1;
        Random random = new Random();
        GreaterThan cmp = new GreaterThan();

        while (left <= right) {
            int randIdx = left + random.nextInt(right - left + 1);
            int newPivotIdx = partitionList(list, left, right, randIdx, cmp);

            if (newPivotIdx == (val - 1)) {
                return list.get(newPivotIdx);
            } else if (newPivotIdx < (val - 1)) {
                left = newPivotIdx + 1;
            } else {
                right = newPivotIdx - 1;
            }
        }

        return null;
    }

    private static int partitionList(List<Integer> list, int left, int right, int randIdx, Comparator<Integer> cmp) {
        int newPivotIdx = left, pivotVal = list.get(randIdx);

        Collections.swap(list, randIdx, right);

        for (int i = left; i < right; i++) {
            if (cmp.compare(list.get(i), pivotVal) < 0) {
                Collections.swap(list, i, newPivotIdx++);
            }
        }

        Collections.swap(list, right, newPivotIdx);

        return newPivotIdx;
    }

    public static class MissingDuplicate {
        public Integer missing;
        public Integer duplicate;

        public MissingDuplicate(Integer missing, Integer duplicate) {
            this.missing = missing;
            this.duplicate = duplicate;
        }
    }

    public static MissingDuplicate findMissingDuplicate(List<Integer> list) {
        int missXORDup = 0;

        for (int i = 0; i < list.size(); i++) {
            missXORDup ^= (i ^ list.get(i));
        }

        int differBit = missXORDup & (~(missXORDup - 1));
        int missOrDup = 0;

        for (int i = 0; i < list.size(); i++) {
            if ((i & differBit) != 0) {
                missOrDup ^= i;
            }
            if ((list.get(i) & differBit) != 0) {
                missOrDup ^= list.get(i);
            }
        }

        for (int l : list) {
            if (l == missOrDup) {
                return new MissingDuplicate(missOrDup ^ missXORDup, missOrDup);
            }
        }

        return new MissingDuplicate(missOrDup, missOrDup ^ missXORDup);
    }

    public static Integer cyclicMin(List<Integer> list) {
        if (list.size() == 0) return null;

        int midIdx = list.size() / 2;

        boolean isLarger = list.get(midIdx) > list.get(list.size() - 1);

        int left = isLarger ? midIdx + 1 : 0;
        int right = isLarger ? list.size() : midIdx;

        Integer response = list.get(midIdx);

        while (left <= right) {
            midIdx = left + ((right - left) / 2);

            if (list.get(midIdx) < response) {
                response = list.get(midIdx);
                right = midIdx;
            } else {
                left = midIdx + 1;
            }
        }

        return response;
    }
}
