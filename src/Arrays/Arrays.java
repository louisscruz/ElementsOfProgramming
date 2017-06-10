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

    public static enum FourColor { RED, WHITE, BLUE, GREEN }

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

    // O(n) time, single pass
    public static void anotherDutchFlagPartition(List<Color> A) {
        int pivot = 1;

        int low = 0, mid = 0, high = A.size();

        while (mid < high) {
            int midValue = A.get(mid).ordinal();
            if (midValue < pivot) {
                Collections.swap(A, low++, mid++);
            } else if (midValue == pivot) {
                mid++;
            } else {
                Collections.swap(A, mid, --high);
            }
        }
    }

    // O(n) time, O(1) space
    public static void fourDutchFlagPartition(List<FourColor> A) {
//        for (FourColor color : FourColor.values()) {
        for (int i = 1; i < 3; i++) {
            FourColor color = FourColor.values()[i];
            int pivot = color.ordinal();

            int low = 0, mid = 0, high = A.size();

            while (mid < high) {
                int midValue = A.get(mid).ordinal();

                if (midValue < pivot) {
                    Collections.swap(A, low++, mid++);
                } else if (midValue == pivot) {
                    mid++;
                } else {
                    Collections.swap(A, mid, --high);
                }
            }
        }
    }

    // O(n) time, O(1) space
    public static void booleanDutchPartition(List<Boolean> A) {
        int low = 0, mid = 0;

        while (mid < A.size()) {
            boolean midValue = A.get(mid);

            if (!midValue) {
                Collections.swap(A, low++, mid++);
            } else {
                mid++;
            }
        }
    }

    public static class Thing {
        public boolean key;
        public int value;

        public Thing(boolean a1, int a2) {
            key = a1;
            value = a2;
        }

        public boolean equals(Thing other) {
            if (!(other instanceof Thing)) return false;

            Thing that = other;

            return this.key == that.key && this.value == that.value;
        }
    }

    // O(n) time, O(1) space
    public static void objectPartition(List<Thing> A) {
        int low = 0, mid = 0;

        while (mid < A.size()) {
            Thing midValue = A.get(mid);

            if (!midValue.key) {
                Collections.swap(A, low++, mid++);
            } else {
                mid++;
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

    // O(n) time
    public static int deleteDuplicates(List<Integer> A) {
        if (A.isEmpty()) {
            return 0;
        }

        int writeIndex = 1;

        for (int i = 1; i < A.size(); i++) {
            if (!A.get(writeIndex - 1).equals(A.get(i))) {
                A.set(writeIndex++, A.get(i));
            }
        }

        return writeIndex;
    }

    // O(n) time, O(1) space
    public static int removeDuplicates(List<Integer> A, int key) {
        int copyPoint = 0;

        for (int i = 0; i < A.size(); i++) {
            int element = A.get(i);
            if (element == key) {
                A.set(copyPoint, element);
            } else {
                copyPoint++;
            }
        }

        return copyPoint + 1;
    }

    // O(n) time, O(1) space
    public static double singleStock(List<Double> A) {
        double minPrice = Double.MAX_VALUE, maxProfit = 0.0;

        for (Double price : A) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }

        return maxProfit;
    }

    // O(n) time, O(1) space
    public static int longestSub(List<Integer> A) {
        if (A.isEmpty()) return 0;

        int maxLength = 1, currentLength = 1;
        int currentValue = A.get(0);

        for (int i = 0; i < A.size() - 1; i++) {
            if (A.get(i) == A.get(i + 1) && A.get(i) == currentValue) {
                if (++currentLength > maxLength) {
                    maxLength = currentLength;
                }
            } else {
                currentLength = 1;
                currentValue = A.get(i + 1);
            }
        }

        return maxLength;
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

        List<FourColor> fourColors = new ArrayList<FourColor>();

        for (int i = 0; i < 4; i++) {
            for (FourColor color : FourColor.values()) {
                fourColors.add(color);
            }
        }

        List<Color> colors2 = new ArrayList<Color>(colors1);
        List<Color> colors3 = new ArrayList<Color>(colors1);
        List<Color> colors4 = new ArrayList<Color>(colors1);
        List<Color> colorsAnswer = new ArrayList<Color>();

        for (Color color : Color.values()) {
            for (int i = 0; i < 3; i++) {
                colorsAnswer.add(color);
            }
        }

        List<FourColor> fourColorAnswer = new ArrayList<FourColor>();

        for (FourColor color : FourColor.values()) {
            for (int i = 0; i < 4; i++) {
                fourColorAnswer.add(color);
            }
        }

        List<Boolean> booleanList = new ArrayList<Boolean>();

        for (int i = 0; i < 10; i++) {
            booleanList.add(true);
            booleanList.add(false);
        }

        List<Thing> thingList = new ArrayList<Thing>();

        thingList.add(new Thing(true, 1));
        thingList.add(new Thing(false, 0));

        List<Boolean> booleanDutchAnswer = new ArrayList<Boolean>();

        for (int i = 1; i < 20; i += 2) {
            booleanDutchAnswer.add(false);
        }

        for (int i = 0; i < 20; i += 2) {
            booleanDutchAnswer.add(true);
        }


        dutchFlagPartition(1, colors1);
        fasterDutchFlagPartition(1, colors2);
        fastestDutchFlagPartition(1, colors3);
        anotherDutchFlagPartition(colors4);
        fourDutchFlagPartition(fourColors);
        booleanDutchPartition(booleanList);
        objectPartition(thingList);

        System.out.println(colors1.equals(colorsAnswer));
        System.out.println(colors2.equals(colorsAnswer));
        System.out.println(colors3.equals(colorsAnswer));
        System.out.println(colors4.equals(colorsAnswer));
        System.out.println(fourColors.equals(fourColorAnswer));
        System.out.println(booleanList.equals(booleanDutchAnswer));
        System.out.println(!thingList.get(0).key && thingList.get(1).key);

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

        List<Integer> repeatedList = new ArrayList<Integer>(java.util.Arrays.asList(1, 1, 2, 2, 3, 3));

        System.out.println(deleteDuplicates(repeatedList) == 3);

        System.out.println(removeDuplicates(repeatedList, 2) == 5);

        List<Double> stocks = new ArrayList<Double>(java.util.Arrays.asList(3.0, 4.0, 2.0, 5.0));

        System.out.println(singleStock(stocks) == 3.0);

        List<Integer> longestList = new ArrayList<Integer>(java.util.Arrays.asList(1, 2, 2, 2, 2, 2, 2, 3, 3));

        System.out.println(longestSub(longestList) == 6);
    }
}
