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

    public static long reverseShort(int x) {
        byte left = 8;
        byte right = 7;

        for (int i = 0; i < 8; i++) {
            x = swapBits(x, (left + i), (right - i));
        }

        return (long)x;
    }

    public static HashMap<Integer, Long> generateReverseBitsMap() {
        HashMap<Integer, Long> map = new HashMap<Integer, Long>();

        for (int i = 0; i < 16; i++) {
            long reversed = reverseShort(i);
            map.put(i, reversed);
        }

        return map;
    }

    // O(n/L) time, where n is the size of the word and L is the size of the mask
    public static long reverseBits(long x) {
        final HashMap<Integer, Long> map = generateReverseBitsMap();
        final int MASK_SIZE = 16;
        final int BIT_MASK = 0xFFFF;

        return map.get((x & BIT_MASK)) << (3 * MASK_SIZE);
//                | map.get((short)((x >>> MASK_SIZE) & BIT_MASK)) << (2 * MASK_SIZE)
//                | map.get((short)((x >>> (2 * MASK_SIZE) & BIT_MASK)) << MASK_SIZE)
//                | map.get((short)((x >>> (3 * MASK_SIZE) & BIT_MASK)));
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
        System.out.println(reverseShort((short)3) == -16384);
        System.out.println(generateReverseBitsMap().get(1) == -32768);
        System.out.println(reverseBits((long)32768));
    }
}
