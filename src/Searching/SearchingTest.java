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

    @Nested
    class findFirstGreater {
        @Test
        @DisplayName("returns the correct index when last")
        void findFirstGreatestLast() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 3, 4));

            assertEquals(5, Searching.findFirstGreater(list, 3));
        }

        @Test
        @DisplayName("returns the correct index when second")
        void findFirstGreatestSecond() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 3, 4));

            assertEquals(1, Searching.findFirstGreater(list, 1));
        }

        @Test
        @DisplayName("returns -1 when non-existent")
        void findFirstGreatestNone() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 3, 4));

            assertEquals(-1, Searching.findFirstGreater(list, 7));
        }
    }

    @Nested
    class findEqualsIndexTests {
        @Test
        @DisplayName("returns the correct indexes")
        void findEqualsIndex() {
            List<Integer> firstList = new ArrayList<>(Arrays.asList(-2, 0, 2, 4, 6, 7, 9));
            List<Integer> secondList = new ArrayList<>(Arrays.asList(-2, 0, 2, 3, 6, 7, 9));

            assertEquals(2, Searching.findEqualsIndex(firstList));
            assertEquals(2, Searching.findEqualsIndex(secondList));
        }
    }

    @Nested
    class findCyclicFirstTests {
        @Test
        @DisplayName("returns the correct index when cyclic")
        void findCyclicFirstCyclic() {
            List<Integer> list = new ArrayList<>(Arrays.asList(5, 6, 0, 1, 2, 3, 4));

            assertEquals(2, Searching.findCyclicFirst(list));
        }

        @Test
        @DisplayName("returns the correct index when non-cyclic")
        void findCyclicFirstNonCyclic() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

            assertEquals(0, Searching.findCyclicFirst(list));
        }
    }

    @Nested
    class intSquareRootTests {
        @Nested
        class whenExact {
            @Test
            @DisplayName("returns the proper value")
            void exactValue() {
                int i = 100;

                assertEquals(10, Searching.intSquareRoot(i));
            }
        }

        @Nested
        class whenInexact {
            @Test
            @DisplayName("returns the proper value")
            void inexactValue() {
                int i = 145;

                assertEquals(12, Searching.intSquareRoot(i));
            }
        }
    }

    @Nested
    class squareRootTests {
        @Test
        @DisplayName("returns the proper value")
        void squareRoot() {
            double i = 6.25;

            double answer = Searching.squareRoot(i);

            assertTrue(2.5 - answer < 0.001);
        }
    }

    @Nested
    class twoDimensionalSearchTests {
        @Nested
        class whenItExists {
            @Test
            @DisplayName("returns the proper index")
            void search() {
                ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
                ArrayList<Integer> first = new ArrayList<>(Arrays.asList(-1, 2, 3));
                ArrayList<Integer> second = new ArrayList<>(Arrays.asList(1, 2, 4));
                ArrayList<Integer> third = new ArrayList<>(Arrays.asList(3, 5, 6));

                matrix.add(first);
                matrix.add(second);
                matrix.add(third);

                int[] answer = Searching.twoDimensionalSearch(matrix, 5);

                assertTrue(Arrays.equals(new int[]{2, 1}, answer));
            }
        }
    }

    @Nested
    class minMaxTests {
        @Test
        @DisplayName("returns the correct min and max")
        void minMax() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 2, 5, 4));

            Searching.MinMax answer = Searching.findMinMax(list);

            assertEquals(1, answer.min);
            assertEquals(5, answer.max);
        }
    }

    @Nested
    class quickSelectTests {
        @Test
        @DisplayName("returns the correct value")
        void quickSelect() {
            List<Integer> list = new ArrayList<>(Arrays.asList(4, 2, 3, 1));

            int answer = Searching.quickSelect(list, 3);

            assertEquals(2, answer);
        }
    }

    @Nested
    class findMissingDuplicateTests {
        @Test
        @DisplayName("returns a correct values")
        void findMissingDuplicate() {
            List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 2, 4, 5));

            Searching.MissingDuplicate answer = Searching.findMissingDuplicate(list);

            assertEquals(3, (int)answer.missing);
            assertEquals(2, (int)answer.duplicate);
        }
    }

    @Nested
    class cyclicMinTests {
        @Test
        @DisplayName("returns the null when given an empty array")
        void empty() {
            Integer answer = Searching.cyclicMin(new ArrayList<Integer>());

            assertNull(answer);
        }

        @Nested
        class whenMidIsOnLarger {
            @Test
            @DisplayName("without repeats")
            void noRepeats() {
                List<Integer> list = new ArrayList<>(Arrays.asList(3, 4, 5, 1, 2));

                Integer answer = Searching.cyclicMin(list);

                assertEquals((Integer)1, answer);
            }

            @Test
            @DisplayName("without repeats")
            void withRepeats() {
                List<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 1, 1, 1, 1, 2, 2, 2));

                Integer answer = Searching.cyclicMin(list);

                assertEquals((Integer)1, answer);
            }
        }

        @Nested
        class whenMidIsOnSmaller {
            @Test
            @DisplayName("without repeats")
            void noRepeats() {
                List<Integer> list = new ArrayList<>(Arrays.asList(3, 4, 0, 1, 2));

                Integer answer = Searching.cyclicMin(list);

                assertEquals((Integer)0, answer);
            }

            @Test
            @DisplayName("without repeats")
            void withRepeats() {
                List<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 3, 3, 4, 4, 4, 0, 0, 1, 1, 2));

                Integer answer = Searching.cyclicMin(list);

                assertEquals((Integer)0, answer);
            }
        }
    }
}