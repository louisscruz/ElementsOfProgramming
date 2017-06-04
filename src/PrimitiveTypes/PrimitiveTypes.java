package PrimitiveTypes;

import java.util.HashMap;

/**
 * Created by louiscruz on 5/16/17.
 */
public class PrimitiveTypes {
    public static short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            numBits += (x & 1);
            x >>>= 1;
        }
        return numBits;
    }

    // O(n) time, where n is the length of the word
    public static short parity(long x) {
        short result = 0;
        while(x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }

    // O(s) time, where s is the number of set bits in the word
    public static short fasterParity(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1;
            x &= (x - 1);
        }
        return result;
    }

    // O(log(n)) time, where n is the length of the word
    public static short evenFasterParity(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short)(x & 0x1);
    }

    // O(1) time
    public static long rightPropagate(long x) {
        return x | ((x & ~(x - 1)) - 1);
    }

    // O(1) time
    public static long modPowerOfTwo(long x, long y) {
        return x & (y - 1);
    }

    // O(1) time
    public static boolean isPowerOfTwo(long x) {
        return (x ^ (x & ~(x - 1))) == 0x0;
    }

    // O(1) time
    public static int swapBits(long x, int i, int j) {
        if (((x >>> i) & 1) != ((x >>> j) & 1)) {
            int bitMask = (1 << i) | (1 << j);
            x ^= bitMask;
        }
        return (int)x;
    }

    public static long reverseIntBits(int x) {
        byte left = 8;
        byte right = 7;

        for (int i = 0; i < 8; i++) {
            x = swapBits((long)x, (left + i), (right - i));
        }

        return x;
    }

    public static long[] generateReverseBitsTable() {
        final int MAX = 32768;
        long[] table;

        table = new long[MAX];

        for (int i = 0; i < MAX; i++) {
            table[i] = reverseIntBits(i);
        }

        return table;
    }

    // O(n/L) time (not counting the table generation), where n is the size of the word and L is the size of the mask
    // There's currently a one-off error in the binary of reverseIntBits for all powers of two
    public static long reverseBits(long x) {
        final int MASK_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        final long[] precomputedReverse = generateReverseBitsTable();

        return precomputedReverse[(int)(x & BIT_MASK)] << (3 * MASK_SIZE)
                | precomputedReverse[(int)((x >>> MASK_SIZE) & BIT_MASK)]
                    << (2 * MASK_SIZE)
                | precomputedReverse[(int)((x >>> (2 * MASK_SIZE)) & BIT_MASK)]
                    << MASK_SIZE
                | precomputedReverse[(int)((x >>> (3 * MASK_SIZE)) & BIT_MASK)];
    }

    public static long closestIntSameBitCount(long x) {
        final int NUM_UNSIGNED_BITS = 63;

        for (int i = 0; i < NUM_UNSIGNED_BITS; i++) {
            if ((((x >>> i) & 1) != (( x >>> (i + 1)) & 1))) {
                x ^= (1L << i) | (1L << (i + 1));
                return x;
            }
        }

        throw new IllegalArgumentException("All bits are 0 or 1");
    }

    // O(1) time
    public static long fasterClosestInt(long x) {
        final long notSet = ~x & (x + 1);
        final long set = x & ~(x - 1);

        if (notSet > set) {
            x |= notSet;
            x ^= notSet >> 1;
        } else {
            x ^= set;
            x |= set >> 1;
        }

        return x;
    }

    // O(n) time, where n is the number of bits required to represent the operands
    public static long add(long x, long y) {
        long sum = 0, carryIn = 0, k = 1, tempX = x, tempY = y;

        while (tempX != 0 || tempY != 0) {
            long xk = x & k, yk = y & k;
            long carryOut = (xk & yk) | (xk & carryIn) | (yk & carryIn);
            sum |= (xk ^ yk ^ carryIn);
            carryIn = carryOut << 1;
            k <<= 1;
            tempX >>>= 1;
            tempY >>>= 1;
        }

        return sum | carryIn;
    }

    // O(n^2) time, where n is the number of bits required to represent the operands
    public static long multiply(long x, long y) {
        long sum = 0;

        while (x != 0) {
            if ((x & 1) != 0) {
                sum = add(sum, y);
            }
            x >>>= 1;
            y <<= 1;
        }

        return sum;
    }

    // O(n) time, where n is the number of bits required to represent the operands
    public static long divide(long x, long y) {
        long result = 0;
        int power = 32;
        long yPower = y << power;

        while (x >= y) {
            while (yPower > x) {
                yPower >>>= 1;

                --power;
            }

            result += 1L << power;
            x -= yPower;
        }

        return result;
    }

    // O(n) time, where n is the number of bits required to represent the operands
    public static double power(double x, int y) {
        double result = 1.0;
        long power = y;

        if (y < 0) {
            power = -power;
            x = 1.0 / x;
        }

        while (power != 0) {
            if ((power & 1) != 0) {
                result *= x;
            }
            x *= x;
            power >>>= 1;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(countBits(5) == 2);
        System.out.println(parity(10000) == 1);
        System.out.println(fasterParity(10000) == 1);
        System.out.println(evenFasterParity(10000) == 1);
        System.out.println(rightPropagate(80) == 95);
        System.out.println(modPowerOfTwo(205, 64) == 13);
        System.out.println(isPowerOfTwo(8));
        System.out.println(!isPowerOfTwo(9));
        System.out.println(swapBits(73, (byte)1, (byte)6) == 11);
        System.out.println(reverseIntBits((256)) == 128);
        System.out.println(generateReverseBitsTable()[1] == 32768);
        System.out.println(reverseBits((long) 3) == -4611686018427387904L);
        System.out.println(closestIntSameBitCount(8) == 4);
        System.out.println(fasterClosestInt(8) == 4);
        System.out.println(multiply(13, 9) == 117);
        System.out.println(divide(9, 3) == 3);
        System.out.println(power(3, 3) == 27);
    }
}
