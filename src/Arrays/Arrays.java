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

    // O(n) time
    public static List<Integer> plusOne(List<Integer> A) {
        int n = A.size() - 1;

        A.set(n, A.get(n) + 1);

        for (int i = n; i > 0 && A.get(i) == 10; --i) {
            A.set(i, 0);
            A.set(i - 1, A.get(i - 1) + 1);
        }

        if (A.get(0) == 10) {
            A.set(0, 0);
            A.add(0, 1);
        }

        return A;
    }

    // O(n) time, where n is the number of bits used to represent a and b
    public static String bitStringAdd(String a, String b) {
        if (a.length() != b.length()) throw new IllegalArgumentException();

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < a.length(); i++) {
            char first = a.charAt(i);
            char second = b.charAt(i);

            if (!(first == '1' || first == '0') || !(second == '1' || second == '0')) throw new IllegalArgumentException();

            int firstValue = first == '1' ? 1 : 0;
            int secondValue = second == '1' ? 1 : 0;
            int newValue = firstValue + secondValue;

            result.add(newValue);
        }

        for (int i = result.size() - 1; i > 0; i--) {
            int carryOver = result.get(i) / 2;
            int remainder = result.get(i) % 2;

            if (carryOver > 0) {
                result.set(i, remainder);
                result.set(i - 1, result.get(i - 1) + carryOver);
            }
        }

        if (result.get(0) > 1) {
            int carryOver = result.get(0) / 2;
            int remainder = result.get(0) % 2;

            result.set(0, remainder);
            result.add(0, carryOver);
        }

        StringBuilder sb = new StringBuilder();

        for (int i : result) {
            sb.append(i);
        }

        return sb.toString();
    }

    // O(n) time
    public static List<Integer> multiply(List<Integer> a, List<Integer> b) {
        final int sign = a.get(0) < 0 ^ b.get(0) < 0 ? -1 : 1;
        a.set(0, Math.abs(a.get(0)));
        b.set(0, Math.abs(b.get(0)));

        List<Integer> result = new ArrayList<Integer>(Collections.nCopies(a.size() + b.size(), 0));

        for (int i = a.size() - 1; i >= 0; --i) {
            for (int j = b.size() - 1; j >= 0; --j) {
                result.set(i + j + 1, result.get(i + j + 1) + a.get(i) * b.get(j));
                result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
                result.set(i + j + 1, result.get(i + j + 1) % 10);
            }
        }

        int firstNotZero = 0;

        while (firstNotZero < result.size() && result.get(firstNotZero) == 0) {
            ++firstNotZero;
        }

        result = result.subList(firstNotZero, result.size());

        if (result.isEmpty()) {
            return java.util.Arrays.asList(0);
        }

        result.set(0, result.get(0) * sign);

        return result;
    }

    // O(n) time, O(1) space
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestReachSoFar = 0, lastIndex = maxAdvanceSteps.size() - 1;

        for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex; i++) {
            furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
        }

        return furthestReachSoFar >= lastIndex;
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

        List<Integer> decimalList = new ArrayList<Integer>();
        decimalList.add(1);
        decimalList.add(2);
        decimalList.add(9);
        System.out.println(plusOne(decimalList).get(1) == 3);

        System.out.println(bitStringAdd("1111", "1111").equals("11110"));

        List<Integer> multiplyAnswer = new ArrayList<Integer>(java.util.Arrays.asList(1, 6, 9, 0, 0));

        System.out.println(multiply(decimalList, decimalList).equals(multiplyAnswer));

        List<Integer> stepBoard = new ArrayList<Integer>(java.util.Arrays.asList(3, 3, 1, 0, 2, 0, 1));

        System.out.println(canReachEnd(stepBoard));
    }
}