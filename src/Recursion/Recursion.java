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

    public static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        solveQueens(n, 0, new ArrayList<>(), result);
        return result;
    }

    private static void solveQueens(int n, int row, List<Integer> colPlacement, List<List<Integer>> result) {
        if (row == n) {
            result.add(new ArrayList<>(colPlacement));
        };

        for (int i = 0; i < n; ++i) {
            colPlacement.add(i);
            if (isValid(colPlacement)) {
                solveQueens(n, row + 1, colPlacement, result);
            }
            colPlacement.remove(colPlacement.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> colPlacement) {
        int rowId = colPlacement.size() - 1;

        for (int i = 0; i < rowId; ++i) {
            int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowId));
            if (diff == 0 || diff == rowId - i) {
                return false;
            }
        }
        return true;
    }
}
