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

    @Nested
    class increasingDecreasingTests {
        @Test
        @DisplayName("properly returns the list sorted")
        void sort() {
            List<Integer> list = new LinkedList<>(Arrays.asList(1, 4, 7, 6, 3, 2, 9, 8, 5));

            List<Integer> expected = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

            assertEquals(expected, Heaps.increaseDecreaseSort(list));
        }
    }

    @Nested
    class almostSorted {
        @Test
        @DisplayName("properly returns the list sorted")
        void sort() {
            List<Integer> list = new LinkedList<>(Arrays.asList(3, -1, 2, 6, 4, 5, 8));

            List<Integer> expected = new LinkedList<>(Arrays.asList(-1, 2, 3, 4, 5, 6, 8));

            assertEquals(expected, Heaps.almostSorted(list.iterator(), 2));
        }
    }
}