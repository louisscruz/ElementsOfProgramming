package Searching;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchingTest {
    @Nested
    class findFirstTests {
        @Test
        @DisplayName("properly returns the correct index")
        void findFirst() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 3, 4));
            List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 5));

            assertEquals(1, Searching.findFirst(list, 2));
            assertEquals(3, Searching.findFirst(list2, 4));
        }
    }
}