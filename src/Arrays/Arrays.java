package Arrays;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static Arrays.Arrays.Color.*;

public class Arrays {
    public static void evenOdd(int[] input) {
        int nextEven = 0, nextOdd = input.length - 1;
        while (nextEven < nextOdd) {
            if (input[nextEven] % 2 == 0) {
                nextEven++;
            } else {
                int temp = input[nextEven];
                input[nextEven] = input[nextOdd];
                input[nextOdd--] = temp;
            }
        }
    }

    public static enum Color { RED, WHITE, BLUE }

    // O(n^2) time
    public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);

        for (int i = 0; i < A.size(); ++i) {
            for (int j = i + 1; j < A.size(); ++j) {
                if (A.get(j).ordinal() < pivot.ordinal()) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }

        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); --i) {
            for (int j = i - 1; j >= 0 && A.get(j).ordinal() >= pivot.ordinal(); --j) {
                if (A.get(j).ordinal() > pivot.ordinal()) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }
    }

    // O(n) time, two passes
    public static void fasterDutchFlagPartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);
        int smaller = 0;

        for (int i = 0; i < A.size(); ++i) {
            if (A.get(i).ordinal() < pivot.ordinal()) {
                Collections.swap(A, smaller++, i);
            }
        }

        int larger = A.size() - 1;

        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); --i) {
            if (A.get(i).ordinal() > pivot.ordinal()) {
                Collections.swap(A, larger--, i);
            }
        }
    }

    // O(n) time, single pass
    public static void fastestDutchFlagPartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);

        int smaller = 0, equal = 0, larger = A.size();

        while (equal < larger) {
            if (A.get(equal).ordinal() < pivot.ordinal()) {
                Collections.swap(A, smaller++, equal++);
            } else if (A.get(equal).ordinal() == pivot.ordinal()) {
                equal++;
            } else {
                Collections.swap(A, equal, --larger);
            }
        }
    }

    public static void sameKeyDutchFlagPartition(List<Color> A) {

    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 4, 3};
        evenOdd(array1);
        System.out.println(java.util.Arrays.equals(array1, new int[]{4, 2, 3, 1}));
        List<Color> colors1 = new ArrayList<Color>();
        for (int i = 0; i < 3; i++) {
            colors1.add(RED);
            colors1.add(WHITE);
            colors1.add(BLUE);
        }
        List<Color> colors2 = new ArrayList<Color>(colors1);
        List<Color> colors3 = new ArrayList<Color>(colors1);
        List<Color> colorsAnswer = new ArrayList<Color>();
        for (Color color : Color.values()) {
            for (int i = 0; i < 3; i++) {
                colorsAnswer.add(color);
            }
        }

        dutchFlagPartition(1, colors1);
        fasterDutchFlagPartition(1, colors2);
        fastestDutchFlagPartition(1, colors3);

        System.out.println(colors1.equals(colorsAnswer));
        System.out.println(colors2.equals(colorsAnswer));
        System.out.println(colors3.equals(colorsAnswer));
    }
}
