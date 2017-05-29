package PrimitiveTypes;

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

    public static void main(String[] args) {
        PrimitiveTypes primitiveTypes = null;
        System.out.println(primitiveTypes.countBits(5) == 2);
        System.out.println(primitiveTypes.parity(10000) == 1);
        System.out.println(primitiveTypes.fasterParity(10000) == 1);
        System.out.println(primitiveTypes.evenFasterParity(10000) == 1);
        System.out.println(primitiveTypes.rightPropagate(80) == 95);
        System.out.println(modPowerOfTwo(205, 64) == 13);
    }
}
