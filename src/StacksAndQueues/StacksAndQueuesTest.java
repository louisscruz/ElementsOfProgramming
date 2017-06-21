package StacksAndQueues;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class StacksAndQueuesTest {
    @Nested
    class maxStackTests {
        @Test
        @DisplayName("properly returns the max")
        void maxStack() {
            StacksAndQueues.Stack stack = new StacksAndQueues.Stack();
            stack.push(1);
            stack.push(3);
            stack.push(2);

            assertEquals(3, (int)stack.max());

            stack.pop();
            stack.pop();

            assertEquals(1, (int)stack.pop());
        }
    }

    @Nested
    class rpnTests {
        @Test
        @DisplayName("returns correct integer")
        void rpn() {
            int answer = StacksAndQueues.rpn("443*4+2--");

            assertEquals(-10, answer);
        }

    }

    @Nested
    class isWellFormedTests {
        @Test
        @DisplayName("properly returns false")
        void falseFormed() {
            assertFalse(StacksAndQueues.isWellFormed("(()(}))"));
        }

        @Test
        @DisplayName("properly returns true")
        void trueFormed() {
            assertTrue(StacksAndQueues.isWellFormed("({[]})"));
        }
    }

    @Nested
    class equivalentPathTests {
        @Test
        @DisplayName("properly returns correct path when beginning with a slash")
        void slash() {
            assertEquals("/test/thing", StacksAndQueues.shortestEquivalentPath("/test/other/../thing"));
        }

        @Test
        @DisplayName("properly returns correct path when it does not begin with a slash")
        void noSlash() {
            assertEquals("test/thing", StacksAndQueues.shortestEquivalentPath("test/other/../thing"));
        }
    }
}