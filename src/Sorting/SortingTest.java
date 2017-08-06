package Sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {
    @Nested
    class intersectionTests {
        @Test
        @DisplayName("returns the correct result")
        void intersectionResult() {
            List<Integer> first = new ArrayList<>(Arrays.asList(2, 3, 3, 5, 5, 6, 7, 7, 8, 12));
            List<Integer> second = new ArrayList<>(Arrays.asList(5, 5, 6, 8, 8, 10, 10));

            List<Integer> expected = new ArrayList<>(Arrays.asList(5, 6, 8));

            List<Integer> answer = Sorting.intersection(first, second);

            assertEquals(expected, answer);
        }
    }

    @Nested
    class mergeArraysTests {
        @Nested
        class whenEnoughBlankSpaces {
            @Test
            @DisplayName("correctly modifies the first array")
            void merge() {
                List<Integer> first = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, null, null, null, null, null));

                List<Integer> second = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));

                Sorting.mergeArrays(first, second);

                List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

                assertEquals(expected, first);
            }
        }
    }

    @Nested
    class uniqueNamesTests {
        @Test
        @DisplayName("returns the correct result")
        void uniqueNames() {
            List<Sorting.Name> names = new ArrayList<>();
            names.add(new Sorting.Name("jack", "noble"));
            names.add(new Sorting.Name("john", "doe"));
            names.add(new Sorting.Name("john", "don"));

            List<Sorting.Name> expected = new ArrayList<>();
            expected.add(new Sorting.Name("jack", "noble"));
            expected.add(new Sorting.Name("john", "doe"));

            Sorting.uniqueNames(names);

            assertEquals(expected.get(0).first, names.get(0).first);
            assertEquals(expected.get(0).last, names.get(0).last);
            assertEquals(expected.get(1).first, names.get(1).first);
            assertEquals(expected.get(1).last, names.get(1).last);
        }
    }

    @Nested
    class smallestNonConstructableTests {
        @Test
        @DisplayName("returns the proper value")
        void smallest() {
            List<Integer> coins = new LinkedList<>(Arrays.asList(12, 2, 1, 15, 2, 4));

            Integer expected = 10;

            Integer answer = Sorting.smallestNonConstructable(coins);

            assertEquals(expected, answer);
        }
    }

    @Nested
    class simultaneousEventsTests {
        @Test
        @DisplayName("returns the proper value")
        void simultaneous() {
            List<Sorting.Event> events = new ArrayList<>(Arrays.asList(new Sorting.Event(1, 3), new Sorting.Event(2, 3), new Sorting.Event(2, 4), new Sorting.Event(3, 4), new Sorting.Event(5, 6)));

            int expected = 4;

            int answer = Sorting.simultaneousEvents(events);

            assertEquals(expected, answer);
        }
    }

    @Nested
    class intervalUnionTests {
        @Test
        @DisplayName("returns the proper values")
        void union() {
            List<Sorting.Interval> intervals = new ArrayList<>();
            intervals.add(new Sorting.Interval(0, false, 3, false));
            intervals.add(new Sorting.Interval(1, true, 1, true));
            intervals.add(new Sorting.Interval(2, true, 4, true));
            intervals.add(new Sorting.Interval(3, true, 4, false));
            intervals.add(new Sorting.Interval(5, true, 7, false));
            intervals.add(new Sorting.Interval(7, true, 8, false));
            intervals.add(new Sorting.Interval(8, true, 11, false));
            intervals.add(new Sorting.Interval(9, false, 11, true));

            List<Sorting.Interval> expected = new ArrayList<>();
            expected.add(new Sorting.Interval(0, false, 4, true));
            expected.add(new Sorting.Interval(5, true, 11, true));

            List<Sorting.Interval> answer = Sorting.unionOfIntervals(intervals);

            assertEquals(expected, answer);
        }
    }
}