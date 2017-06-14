package Strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {
    @Nested
    class intToStringTests {
        @Test
        @DisplayName("should properly encode an integer into a string")
        void convertIntToString() {
            final String s = Strings.intToString(103);
            assertEquals("103", s);
        }

        @Test
        @DisplayName("should properly encode a negative integer into a string")
        void convertNegativeIntToString() {
            final String s = Strings.intToString(-103);
            assertEquals("-103", s);
        }
    }

    @Nested
    class stringToIntTests {
        @Test
        @DisplayName("should properly decode a string into an integer")
        void convertStringToInt() {
            final int i = Strings.stringToInt("103");
            assertEquals(103, i);
        }

        @Test
        @DisplayName("should properly decode a string into an integer when negative")
        void convertStringToIntNegative() {
            final int i = Strings.stringToInt("-103");
            assertEquals(-103, i);
        }
    }

    @Nested
    class baseConvertTests {
        @Test
        @DisplayName("should properly convert from one base to another")
        void convertTenToTwo() {
            final String answer = Strings.baseConvert("15", 10, 2);
            assertEquals("1111", answer);
        }
    }

    @Nested
    class spreadsheetEncodeTests {
        @Test
        @DisplayName("should properly convert numbers less than twenty-six")
        void convertSmall() {
            final int answer = Strings.spreadsheetEncode("Y");
            assertEquals(25, answer);
        }

        @Test
        @DisplayName("should properly convert numbers greater than twenty-six")
        void convertLarge() {
            final int answer = Strings.spreadsheetEncode("ZZ");
            assertEquals(702, answer);
        }
    }

    @Nested
    class replaceRemoveTests {
        @Test
        @DisplayName("should properly remove b's")
        void removeB() {
            char[] chars = new char[]{'b', 'b', 'c', 'c'};
            final int answer = Strings.replaceAndRemove(2, chars);
            assertEquals(0, answer);
            assertArrayEquals(new char[]{'b', 'b', 'c', 'c'}, chars);
        }

        @Test
        @DisplayName("should properly add d's")
        void addA() {
            char[] chars = new char[]{'a', 'a', 'c', 'c'};
            final int answer = Strings.replaceAndRemove(2, chars);
            assertEquals(4, answer);
            assertArrayEquals(new char[]{'d', 'd', 'd', 'd'}, chars);
        }

        @Test
        @DisplayName("should properly add d's and remove b's")
        void addARemoveB() {
            char[] chars = new char[]{'a', 'b', 'a', 'c', 'c'};
            final int answer = Strings.replaceAndRemove(3, chars);
            assertEquals(4, answer);
            assertArrayEquals(new char[]{'d', 'd', 'd', 'd', 'c'}, chars);
        }
    }
}