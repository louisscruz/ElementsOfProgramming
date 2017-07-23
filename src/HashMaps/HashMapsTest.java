package HashMaps;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.*;

class HashMapsTest {
    @Nested
    class canFormPalindromeTests {
        @Nested
        class whenTrue {
            @Test
            @DisplayName("returns true when of odd length")
            void trueTestOdd() {
                boolean answer = HashMaps.canFormPalindrome("teste");

                assertTrue(answer);
            }

            @Test
            @DisplayName("returns true when of even length")
            void trueTestEven() {
                boolean answer = HashMaps.canFormPalindrome("testes");

                assertTrue(answer);
            }
        }

        @Nested
        class whenFalse {
            @Test
            @DisplayName("returns false")
            void falseTest() {
                boolean answer = HashMaps.canFormPalindrome("test");

                assertFalse(answer);
            }
        }
    }

    @Nested
    class constructableTests {
        @Nested
        class whenTrue {
            @Test
            @DisplayName("properly returns true")
            void trueTest() {
                String targetText = "test the cats";
                String magazineText = "this is the magazine text. cats are the best";

                boolean answer = HashMaps.constructable(targetText, magazineText);

                assertTrue(answer);
            }
        }

        @Nested
        class whenFalse {
            @Test
            @DisplayName("properly returns false")
            void trueTest() {
                String targetText = "test the cats verifiably";
                String magazineText = "this is the magazine text. cats are the best";

                boolean answer = HashMaps.constructable(targetText, magazineText);

                assertFalse(answer);
            }
        }
    }

    @Nested
    class LRUCacheTests {
        @Test
        @DisplayName("behaves properly")
        void properly() {
            HashMaps.LRUCache cache = new HashMaps.LRUCache(1);

            assertNull(cache.lookup(1));

            cache.insert(1, 1);

            assertEquals(1, (int)cache.lookup(1));

            cache.insert(2, 2);

            assertNull(cache.lookup(1));

            assertEquals(2, (int)cache.lookup(2));
        }
    }

    @Nested
    class kMostFrequentQueriesTests {
        @Nested
        class whenValid {
            @Test
            @DisplayName("returns the proper queries")
            void threeQueries() {
                List<String> input = new ArrayList<>(Arrays.asList("one", "one", "two", "one", "two", "three", "one", "two", "three", "four", "five", "six"));

                List<String> expected = new ArrayList<>(Arrays.asList("one", "two", "three"));

                List<String> answer = HashMaps.kMostFrequentQueries(input, 3);

                boolean isCorrect = true;

                for (String s : answer) {
                    if (!expected.contains(s)) {
                        isCorrect = false;
                        break;
                    }
                }

                assertTrue(isCorrect);
            }
        }
    }

    @Nested
    class smallestSubarrayCoverageTests {
        @Test
        @DisplayName("finds the correct sub-array")
        void findSubArray() {
            Iterator<String> words = new ArrayList<>(Arrays.asList("one", "two", "one", "three", "four")).iterator();
            List<String> query = new ArrayList<>(Arrays.asList("two", "three"));

            HashMaps.SubArray answer = HashMaps.smallestSubArrayCoverage(words, query);

            HashMaps.SubArray expected = new HashMaps.SubArray(1, 3);

            assertEquals(expected.start, answer.start);
            assertEquals(expected.end, answer.end);
        }
    }

    @Nested
    class smallestConsecutiveSubarrayCoverageTests {
        @Test
        @DisplayName("finds the correct sub-array")
        void findSubArray() {
            List<String> words = new ArrayList<>(Arrays.asList("b", "a", "c", "b"));
            List<String> query = new ArrayList<>(Arrays.asList("a", "b"));

            HashMaps.SubArray answer = HashMaps.smallestConsecutiveSubArrayCoverage(words, query);

            HashMaps.SubArray expected = new HashMaps.SubArray(1, 3);

            assertEquals(expected.start, answer.start);
            assertEquals(expected.end, answer.end);
        }
    }

    @Nested
    class largestDistinctSubArrayTests {
        @Test
        @DisplayName("finds the correct sub-array")
        void distinct() {
            List<String> source = new ArrayList<>(Arrays.asList("a", "b", "b", "c", "d", "e", "c", "b", "d", "e"));

            HashMaps.SubArray expected = new HashMaps.SubArray(2, 5);

            HashMaps.SubArray answer = HashMaps.largestDistinctSubArray(source);

            assertEquals(expected.start, answer.start);

            assertEquals(expected.end, answer.end);
        }
    }

    @Nested
    class largestSequentialSubSet {
        @Test
        @DisplayName("returns the correct answer")
        void largest() {
            List<Integer> input = new ArrayList<>(Arrays.asList(5, 6, 4, 7, 3, 1, 1, 9));
            int expected = 5;

            int answer = HashMaps.largestSequentialSubSet(input);

            assertEquals(expected, answer);
        }
    }
}