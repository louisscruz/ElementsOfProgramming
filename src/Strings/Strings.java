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
}
