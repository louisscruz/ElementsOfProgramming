package Searching;

import java.util.List;

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
}
