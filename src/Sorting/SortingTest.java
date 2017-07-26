package Sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
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
}