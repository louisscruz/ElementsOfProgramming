package Recursion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

class RecursionTest {
    @Nested
    class Hanoi {
        @Test
        @DisplayName("correctly moves one piece")
        void oneMove() {
            List<String> instructions = Recursion.hanoi(1);
            assertEquals(instructions, Arrays.asList("move 0 to 1"));
        }

        @Test
        @DisplayName("correctly moves two pieces")
        void twoMove() {
            List<String> instructions = Recursion.hanoi(2);
            assertEquals(Arrays.asList("move 0 to 2", "move 0 to 1", "move 2 to 1"), instructions);
        }

        @Test
        @DisplayName("correctly moves three pieces")
        void threeMove() {
            List<String> instructions = Recursion.hanoi(3);
            List<String> expected = new LinkedList<>();
            expected.add("move 0 to 1");
            expected.add("move 0 to 2");
            expected.add("move 1 to 2");
            expected.add("move 0 to 1");
            expected.add("move 2 to 0");
            expected.add("move 2 to 1");
            expected.add("move 0 to 1");
            assertEquals(expected, instructions);
        }
    }

    @Nested
    class nQueens {
        @Test
        @DisplayName("does something")
        void correctResult() {
            List<List<Integer>> result = Recursion.nQueens(4);
            List<List<Integer>> expected = new ArrayList<>();
            expected.add(Arrays.asList(1, 3, 0, 2));
            expected.add(Arrays.asList(2, 0, 3, 1));

            assertEquals(expected, result);
        }
    }
}