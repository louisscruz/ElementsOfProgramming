package Searching;

import java.util.List;

public class Searching {
    public static int findFirst(List<Integer> list, int k) {
        int left = 0, right = list.size(), result = -1;

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
}
