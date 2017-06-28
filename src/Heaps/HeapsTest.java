package Heaps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapsTest {
    @Nested
    class mergeFilesTests {
        @Test
        @DisplayName("properly merges")
        void merge() {
            List<List<Integer>> lists = new LinkedList<>();
            List<Integer> firstList = new LinkedList<>(Arrays.asList(1, 3, 4));
            List<Integer> secondList = new LinkedList<>(Arrays.asList(2, 5, 6));
            List<Integer> thirdList = new LinkedList<>(Arrays.asList(0, 7, 8));
            lists.add(firstList);
            lists.add(secondList);
            lists.add(thirdList);

            List<Integer> expected = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

            assertEquals(expected, Heaps.mergeFiles(lists));
        }
    }
}