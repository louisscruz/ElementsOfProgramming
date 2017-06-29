package Heaps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
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
    class almostSortedTests {
        @Test
        @DisplayName("properly returns the list sorted")
        void sort() {
            List<Integer> list = new LinkedList<>(Arrays.asList(3, -1, 2, 6, 4, 5, 8));

            List<Integer> expected = new LinkedList<>(Arrays.asList(-1, 2, 3, 4, 5, 6, 8));

            assertEquals(expected, Heaps.almostSorted(list.iterator(), 2));
        }
    }

    @Nested
    class starTests {
        @Test
        @DisplayName("properly returns the correct starts")
        void closestStars() {
            Heaps.Star firstStar = new Heaps.Star(0, 1, 1);
            Heaps.Star secondStar = new Heaps.Star(1, 1, 1);
            Heaps.Star thirdStar = new Heaps.Star(2, 2, 2);
            List<Heaps.Star> list = new LinkedList<>(Arrays.asList(firstStar, secondStar, thirdStar));

            List<Heaps.Star> expected = new LinkedList<>(Arrays.asList(firstStar, secondStar));

            assertEquals(expected, Heaps.closestStars(2, list.iterator()));
        }
    }

    @Nested
    class onlineMedianTests {
        @Test
        @DisplayName("properly returns the median when of odd cardinality")
        void onlineMedian() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 0, 3));

            assertEquals(1, Heaps.onlineMedian(list.iterator()));
        }
    }

    @Nested
    class maxMaxHeapTests {
        @Test
        @DisplayName("properly returns the correct elements")
        void maxMaxHeap() {
            List<Integer> list = new ArrayList<>(Arrays.asList(50, 7, 40, 5, 4, 39, 38));

            List<Integer> expected = new ArrayList<>(Arrays.asList(50, 40, 39));

            assertEquals(expected, Heaps.maxMaxHeap(list, 3));
        }
    }

    @Nested
    class stackHeapTests {
        @Test
        @DisplayName("properly returns the correct elements")
        void stackHeap() {
            Heaps.Stack s = new Heaps.Stack();
            s.push(3);
            s.push(2);
            s.push(1);

            assertEquals((Integer)1, s.pop());
            assertEquals((Integer)2, s.pop());
            assertEquals((Integer)3, s.pop());
        }
    }
}