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
}