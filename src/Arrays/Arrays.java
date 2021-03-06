package Arrays;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

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

    // O(n^2) time, O(1) space
    public static double doubleStock(List<Double> A) {
        double minFirstPrice = Double.MAX_VALUE, minSecondPrice = Double.MAX_VALUE, maxProfit = 0.0;

        for (int i = 0; i < A.size(); i++) {
            minSecondPrice = Double.MAX_VALUE;
            for (int j = i + 1; j < A.size(); j++) {
                maxProfit = Math.max(maxProfit, A.get(i) - minFirstPrice + A.get(j) - minSecondPrice);
                minFirstPrice = Math.min(minFirstPrice, A.get(i));
                minSecondPrice = Math.min(minSecondPrice, A.get(j));
            }
        }

        return maxProfit;
    }

    // O(n) time, O(n) space
    public static double fastDoubleStock(List<Double> A) {
        double maxProfit = 0.0;
        List<Double> firstProfits = new ArrayList<>();
        double comparisonPoint = Double.MAX_VALUE;

        for (Double price : A) {
            comparisonPoint = Math.min(comparisonPoint, price);
            maxProfit = Math.max(maxProfit, price - comparisonPoint);
            firstProfits.add(maxProfit);
        }

        comparisonPoint = Double.MIN_VALUE;

        for (int i = A.size() - 1; i > 0; i--) {
            comparisonPoint = Math.max(comparisonPoint, A.get(i));
            maxProfit = Math.max(maxProfit, comparisonPoint - A.get(i) + firstProfits.get(i - 1));
        }

        return maxProfit;
    }

    // O(n) time, O(1) space
    public static void rearrange(List<Integer> A) {
        for (int i = 0; i < A.size() - 1; i++) {
            if ((i % 2 == 0 && A.get(i) > A.get(i + 1)) || i % 2 == 1 && A.get(i) < A.get(i + 1)) {
                Collections.swap(A, i, i + 1);
            }
        }
    }

    // O(n log log n) time, O(n) space
    public static List<Integer> primes(int n) {
        final int size = (int)Math.floor(0.5 * (n - 3)) + 1;

        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(size, true));
        for (int i = 0; i < size; i++) {
            if (isPrime.get(i)) {
                int p = ((i * 2) + 3);
                primes.add(p);

                for (long j = ((i * i) * 2) + 6 * i + 3; j < size; j += p) {
                    isPrime.set((int)j, false);
                }
            }
        }

        return primes;
    }

    // O(n) time, O(1) space
    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
        for (int i = 0; i < A.size(); i++) {
            int next = i;

            while (perm.get(next) >= 0) {
                Collections.swap(A, i, perm.get(next));
                int temp = perm.get(next);
                perm.set(next, perm.get(next) - perm.size());
                next = temp;
            }
        }

        for (int i = 0; i < perm.size(); i++) {
            perm.set(i, perm.get(i) + perm.size());
        }
    }

    // O(n) time, O(1) space
    public static List<Integer> nextPermutation(List<Integer> perm) {
        int inversionPoint = perm.size() - 2;
        while (inversionPoint >= 0 && perm.get(inversionPoint) >= perm.get(inversionPoint + 1)) {
            inversionPoint--;
        }

        if (inversionPoint == -1) {
            return Collections.emptyList();
        }

        for (int i = perm.size() - 1; i > inversionPoint; i--) {
            if (perm.get(i) > perm.get(inversionPoint)) {
                Collections.swap(perm, inversionPoint, i);
                break;
            }
        }

        Collections.reverse(perm.subList(inversionPoint + 1, perm.size()));
        return perm;
    }

    public static List<Integer> nthPermutation(List<Integer> perm, int n) {
        for (int i = 0; i < n; i++) {
            nextPermutation(perm);
        }

        return perm;
    }

    public static List<Integer> randomSampling(int k, List<Integer> A) {
        Random gen = new Random();

        for (int i = 0; i < k; i++) {
            Collections.swap(A, i, i + gen.nextInt(A.size() - i));
        }

        return A.subList(0, k);
    }

    public static List<Integer> onlineRandomSample(Iterator<Integer> sequence, int k) {
        List<Integer> runningSample = new ArrayList<>(k);

        for (int i = 0; sequence.hasNext() && i < k; i++) {
            runningSample.add(sequence.next());
        }

        int numSeenSoFar = k;
        Random randIdxGen = new Random();

        while (sequence.hasNext()) {
            Integer x = sequence.next();
            ++numSeenSoFar;
            final int idxToReplace = randIdxGen.nextInt(numSeenSoFar);

            if (idxToReplace < k) {
                runningSample.set(idxToReplace, x);
            }
        }

        return runningSample;
    }

    public static List<Integer> computeRandomPermutation(int n) {
        List<Integer> perm = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            perm.add(i);
        }

        Random gen = new Random();
        for (int i = 0; i < n; i++) {
            Collections.swap(perm, i,i + gen.nextInt(n - i));
        }

        return perm;
    }

    public static List<Integer> randomSubset(int n, int k) {
        Map<Integer, Integer> changedElements = new HashMap<>();
        Random randIdxGen = new Random();

        for (int i = 0; i < k; i++) {
            int randIdx = i + randIdxGen.nextInt(n - i);
            Integer ptr1 = changedElements.get(randIdx);
            Integer ptr2 = changedElements.get(i);

            if (ptr1 == null && ptr2 == null) {
                changedElements.put(randIdx, i);
                changedElements.put(i, randIdx);
            } else if (ptr1 == null && ptr2 != null) {
                changedElements.put(randIdx, ptr2);
                changedElements.put(i, randIdx);
            } else if (ptr1 != null && ptr2 == null) {
                changedElements.put(i, ptr2);
                changedElements.put(randIdx, i);
            } else {
                changedElements.put(i, ptr1);
                changedElements.put(randIdx, ptr2);
            }
        }

        List<Integer> result = new ArrayList<>(k);

        for (int i = 0; i < k; i++) {
            result.add(changedElements.get(i));
        }

        return result;
    }

    public static int nonuniformRandomNumberGeneration(List<Integer> values, List<Double> probabilities) {
        List<Double> prefixSumOfProbabilities = new ArrayList<>();
        prefixSumOfProbabilities.add(0.0);

        for (double p : probabilities) {
            prefixSumOfProbabilities.add(prefixSumOfProbabilities.get(prefixSumOfProbabilities.size() - 1) + p);
        }

        Random r = new Random();
        final double uniform01 = r.nextDouble();
        int it = Collections.binarySearch(prefixSumOfProbabilities, uniform01);

        if (it < 0) {
            final int intervalIdx = (Math.abs(1) - 1) - 1;
            return values.get(intervalIdx);
        } else {
            return values.get(it);
        }
    }

    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        final int size = partialAssignment.size();

        for (int i = 0; i < size; i++) {
            if (hasDuplicate(partialAssignment, i, i + 1, 0, size)) return false;
        }

        for (int j = 0; j < size; j++) {
            if (hasDuplicate(partialAssignment, 0, size, j, j + 1)) return false;
        }

        final int regionSize = (int)Math.sqrt(size);
        for (int x = 0; x < regionSize; x++) {
            for (int y = 0; y < regionSize; y++) {
                if (hasDuplicate(partialAssignment, regionSize * x, regionSize * (x + 1), regionSize * y, regionSize * (y + 1))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean hasDuplicate(List<List<Integer>> partialAssignment, int startRow, int endRow, int startCol, int endCol) {
        List<Boolean> isPresent = new ArrayList<>(Collections.nCopies(partialAssignment.size() + 1, false));

        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (partialAssignment.get(i).get(j) != 0 && isPresent.get(partialAssignment.get(i).get(j))) {
                    return true;
                }
                isPresent.set(partialAssignment.get(i).get(j), true);
            }
        }

        return false;
    }

    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        List<Integer> spiralOrdering = new ArrayList<>();

        for (int offset = 0; offset < Math.ceil(0.5 * squareMatrix.size()); offset++) {
            matrixLayerInClockwise(squareMatrix, offset, spiralOrdering);
        }

        return spiralOrdering;
    }

    public static void matrixLayerInClockwise(List<List<Integer>> squareMatrix, int offset, List<Integer> spiralOrdering) {
        if (offset == squareMatrix.size() - offset - 1) {
            spiralOrdering.add(squareMatrix.get(offset).get(offset));
            return;
        }

        for (int j = offset; j < squareMatrix.size() - offset - 1; j++) {
            spiralOrdering.add(squareMatrix.get(offset).get(j));
        }

        for (int i = offset; i < squareMatrix.size() - offset - 1; i++) {
            spiralOrdering.add(squareMatrix.get(i).get(squareMatrix.size() - offset - 1));
        }

        for (int j = squareMatrix.size() - offset - 1; j > offset; j--) {
            spiralOrdering.add(squareMatrix.get(squareMatrix.size() - offset - 1).get(j));
        }

        for (int i = squareMatrix.size() - offset - 1; i > offset; i--) {
            spiralOrdering.add(squareMatrix.get(i).get(offset));
        }
    }

    public static List<Integer> easierMatrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        final int[][] SHIFT = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0, x = 0, y = 0;

        List<Integer> spiralOrdering = new ArrayList<>();

        final int size = squareMatrix.size();

        for (int i = 0; i < size * size; i++) {
            spiralOrdering.add(squareMatrix.get(x).get(y));
            squareMatrix.get(x).set(y, 0);
            int nextX = x + SHIFT[dir][0], nextY = y + SHIFT[dir][1];

            if (nextX < 0 || nextX >= size || nextY < 0 || nextY >= size || squareMatrix.get(nextX).get(nextY) == 0) {
                dir = (dir + 1) % 4;
                nextX = x + SHIFT[dir][0];
                nextY = y + SHIFT[dir][1];
            }

            x = nextX;
            y = nextY;
        }

        return spiralOrdering;
    }

    // O(n^2) time, O(1) space
    public static List<List<Integer>> rotateMatrix(List<List<Integer>> matrix) {
        final int size = matrix.size() - 1;
        for (int i = 0; i < (size + 1) / 2; i++) {
            for (int j = i; j < size - i; j++) {
                int top = matrix.get(i).get(j);
                int right = matrix.get(j).get(size - i);
                int bottom = matrix.get(size - i).get(size - j);
                int left = matrix.get(size - j).get(i);

                matrix.get(i).set(j, left);
                matrix.get(j).set(size - i, top);
                matrix.get(size - i).set(size - j, right);
                matrix.get(size - j).set(i, bottom);
            }
        }

        return matrix;
    }

    // O(1) time, O(1) space
    public static class RotatedMatrix {
        private List<List<Integer>> wrappedSquareMatrix;

        public RotatedMatrix(List<List<Integer>> squareMatrix) {
            this.wrappedSquareMatrix = squareMatrix;
        }

        public int readEntry(int i, int j) {
            return wrappedSquareMatrix.get(wrappedSquareMatrix.size() - 1 - j).get(i);
        }

        public void writeEntry(int i, int j, int v) {
            wrappedSquareMatrix.get(wrappedSquareMatrix.size() - 1 - j).set(i, v);
        }
    }

    // O(n^2) time, O(n^2) space
    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> currRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                currRow.add((0 < j && j < i) ? pascalTriangle.get(i - 1).get(j - 1) + pascalTriangle.get(i - 1).get(j) : 1);
            }

            pascalTriangle.add(currRow);
        }

        return pascalTriangle;
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 4, 3};
        evenOdd(array1);
        System.out.println(java.util.Arrays.equals(array1, new int[]{4, 2, 3, 1}));

        List<Color> colors1 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            colors1.add(RED);
            colors1.add(WHITE);
            colors1.add(BLUE);
        }

        List<FourColor> fourColors = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (FourColor color : FourColor.values()) {
                fourColors.add(color);
            }
        }

        List<Color> colors2 = new ArrayList<>(colors1);
        List<Color> colors3 = new ArrayList<>(colors1);
        List<Color> colors4 = new ArrayList<>(colors1);
        List<Color> colorsAnswer = new ArrayList<>();

        for (Color color : Color.values()) {
            for (int i = 0; i < 3; i++) {
                colorsAnswer.add(color);
            }
        }

        List<FourColor> fourColorAnswer = new ArrayList<>();

        for (FourColor color : FourColor.values()) {
            for (int i = 0; i < 4; i++) {
                fourColorAnswer.add(color);
            }
        }

        List<Boolean> booleanList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            booleanList.add(true);
            booleanList.add(false);
        }

        List<Thing> thingList = new ArrayList<>();

        thingList.add(new Thing(true, 1));
        thingList.add(new Thing(false, 0));

        List<Boolean> booleanDutchAnswer = new ArrayList<>();

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

        List<Integer> decimalList = new ArrayList<>();
        decimalList.add(1);
        decimalList.add(2);
        decimalList.add(9);
        System.out.println(plusOne(decimalList).get(1) == 3);

        System.out.println(bitStringAdd("1111", "1111").equals("11110"));

        List<Integer> multiplyAnswer = new ArrayList<>(java.util.Arrays.asList(1, 6, 9, 0, 0));

        System.out.println(multiply(decimalList, decimalList).equals(multiplyAnswer));

        List<Integer> stepBoard = new ArrayList<>(java.util.Arrays.asList(3, 3, 1, 0, 2, 0, 1));

        System.out.println(canReachEnd(stepBoard));

        List<Integer> repeatedList = new ArrayList<>(java.util.Arrays.asList(1, 1, 2, 2, 3, 3));

        System.out.println(deleteDuplicates(repeatedList) == 3);

        System.out.println(removeDuplicates(repeatedList, 2) == 5);

        List<Double> stocks = new ArrayList<>(java.util.Arrays.asList(3.0, 4.0, 2.0, 5.0));

        System.out.println(singleStock(stocks) == 3.0);

        List<Integer> longestList = new ArrayList<>(java.util.Arrays.asList(1, 2, 2, 2, 2, 2, 2, 3, 3));

        System.out.println(longestSub(longestList) == 6);

        List<Double> doubleStocks = new ArrayList<>(java.util.Arrays.asList(3.0, 4.0, 5.0, 2.0, 1.0, 3.0, 10.0));
        List<Double> secondDoubleStocks = new ArrayList<>(java.util.Arrays.asList(12.0, 11.0, 13.0, 9.0, 12.0, 8.0, 14.0, 13.0, 15.0));

        System.out.println(doubleStock(doubleStocks) == 11);
        System.out.println(doubleStock(secondDoubleStocks) == 10);

        System.out.println(fastDoubleStock(doubleStocks) == 11);
        System.out.println(fastDoubleStock(secondDoubleStocks) == 10);

        List<Integer> rearrangeList = new ArrayList<>(java.util.Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1));

        rearrange(rearrangeList);

        boolean properlyArranged = true;

        for (int i = 0; i < rearrangeList.size() - 1; i++) {
            List<Integer> A = rearrangeList;
            if ((i % 2 == 0 && A.get(i) > A.get(i + 1)) || i % 2 == 1 && A.get(i) < A.get(i + 1)) {
                properlyArranged = false;
            }
        }

        System.out.println(properlyArranged);

        List<Integer> primeList = new ArrayList<>(java.util.Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));

        System.out.println(primes(20).equals(primeList));

        List<Integer> permuteList = new ArrayList<>(java.util.Arrays.asList(3, 2, 4, 1, 0));

        List<Integer> perm = new ArrayList<>(java.util.Arrays.asList(3, 2, 4, 1, 0));

        applyPermutation(permuteList, perm);

        System.out.println(perm.equals(java.util.Arrays.asList(0, 1, 2, 3, 4)));

        System.out.println(nextPermutation(perm).equals(java.util.Arrays.asList(0, 1, 2, 4, 3)));

        List<Integer> nthPerm = new ArrayList<>(java.util.Arrays.asList(0, 1, 2, 3, 4));

        nthPermutation(nthPerm, 12);

        System.out.println(nthPerm.equals(java.util.Arrays.asList(0, 3, 1, 2, 4)));

        System.out.println(randomSampling(3, perm).size() == 3);

        List<List<Integer>> spiralList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            spiralList.add(new ArrayList<>());
            spiralList.get(i).add(1 + (3 * i));
            spiralList.get(i).add(2 + (3 * i));
            spiralList.get(i).add(3 + (3 * i));
        }

        System.out.println(matrixInSpiralOrder(spiralList).equals(java.util.Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5)));

        System.out.println(easierMatrixInSpiralOrder(spiralList).equals(java.util.Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5)));

        List<List<Integer>> matrix = new ArrayList<>();
        List<List<Integer>> rotatedMatrix = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            matrix.add(new ArrayList<>());
            rotatedMatrix.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                matrix.get(i).add((4 * i) + j);
                rotatedMatrix.get(i).add(0);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rotatedMatrix.get(j).set(3 - i, (4 * i) + j);
            }
        }

        System.out.println(rotateMatrix(matrix).equals(rotatedMatrix));

        RotatedMatrix classMatrix = new RotatedMatrix(rotatedMatrix);

        System.out.println(classMatrix.readEntry(0, 3) == 12);

        System.out.println(generatePascalTriangle(4));
    }
}
