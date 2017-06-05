package Arrays;

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

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 4, 3};
        evenOdd(array1);
        System.out.println(java.util.Arrays.toString(array1));
    }
}
