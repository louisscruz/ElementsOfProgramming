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

}