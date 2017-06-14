package Strings;

public class Strings {
    public static String intToString(int x) {
        final boolean isNegative = x < 0;

        StringBuilder s = new StringBuilder();

        do {
            s.append((char)('0' + Math.abs(x % 10)));
            x /= 10;
        } while (x != 0);

        if (isNegative) {
            s.append('-');
        }
        s.reverse();
        return s.toString();
    }

    public static int stringToInt(String s) {
        final boolean isNegative = s.charAt(0) == '-';
        int result = 0;

        for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
            final int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }

        return isNegative ? -result : result;
    }

    public static String baseConvert(String s, int b1, int b2) {
        final boolean isNegative = s.charAt(0) == '-';

        int numAsInt = 0;

        for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
            numAsInt *= b1;
            numAsInt += Character.isDigit(s.charAt(i)) ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
        }

        return (isNegative ? "-" : "") + (numAsInt == 0 ? "0" : constructFromBase(numAsInt, b2));
    }

    static String constructFromBase(int i, int base) {
        return i == 0 ? "" : constructFromBase(i / base, base) + (char)(i % base >= 10 ? 'A' + i % base - 10 : '0' + i % base);
    }
}
