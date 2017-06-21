package StacksAndQueues;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.*;

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

    @Nested
    class jumpOrderTests {
        @Nested
        class rec {
            @Test
            @DisplayName("returns the correct order")
            void order() {
                PostingListNode list = new PostingListNode(new PostingListNode(new PostingListNode(null)));
                list.jump = list.next.next;
                list.next.next.jump = list.next;

                StacksAndQueues.setJumpOrderRec(list);

                assertEquals(0, list.order);
                assertEquals(2, list.next.order);
                assertEquals(1, list.next.next.order);
            }
        }

        @Nested
        class iter {
            @Test
            @DisplayName("returns the correct order")
            void order() {
                PostingListNode list = new PostingListNode(new PostingListNode(new PostingListNode(null)));
                list.jump = list.next.next;
                list.next.next.jump = list.next;

                StacksAndQueues.setJumpOrderIter(list);

                assertEquals(0, list.order);
                assertEquals(2, list.next.order);
                assertEquals(1, list.next.next.order);
            }
        }
    }

    @Nested
    class buildingTests {
        @Test
        @DisplayName("properly return the correct buildings")
        void buildings() {
            Iterator<Integer> buildings = new LinkedList<>(Arrays.asList(7, 5, 6, 4)).iterator();
            Deque<Building> answerBuildings = new LinkedList<>(Arrays.asList(new Building(0, 7), new Building(2, 6), new Building(3, 4)));

            Deque<Building> answer = StacksAndQueues.examineBuildings(buildings);

            for (Building b : answerBuildings) {
                Building answerBuilding = answer.pop();
                assertEquals(b.height, answerBuilding.height);
                assertEquals(b.id, answerBuilding.id);
            }
        }
    }
}