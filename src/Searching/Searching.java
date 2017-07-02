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
}
