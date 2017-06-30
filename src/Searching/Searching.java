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

            System.out.println(mid);

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
}
