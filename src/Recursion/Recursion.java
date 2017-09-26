package Recursion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Recursion {
    public static List<String> hanoi(int size) {
        List<Deque<Integer>> pylons = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            pylons.add(new LinkedList<>());
        }

        for (int i = 0; i < size; ++i) {
            pylons.get(0).addFirst(i);
        }

        return moveHanoi(size, pylons, 0, 1, 2, new LinkedList<>());
    }

    private static List<String> moveHanoi(int numToMove, List<Deque<Integer>> pylons, int from, int to, int use, List<String> instructions) {
        if (numToMove > 0) {
            moveHanoi(numToMove - 1, pylons, from, use, to, instructions);

            String newInstruction = "move " + String.valueOf(from) + " to " + String.valueOf(to);
            instructions.add(newInstruction);
            pylons.get(to).add(pylons.get(from).remove());
            moveHanoi(numToMove - 1, pylons, use, to, from, instructions);

            return instructions;
        }
        return instructions;
    }
}
