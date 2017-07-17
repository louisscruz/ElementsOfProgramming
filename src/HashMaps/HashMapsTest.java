package HashMaps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

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
}