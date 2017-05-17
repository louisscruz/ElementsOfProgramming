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

    public static short parity(long x) {
        short result = 0;
        while(x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        PrimitiveTypes klass = null;
        System.out.println(klass.countBits(5));
        System.out.println(klass.parity(123121));
    }
}
