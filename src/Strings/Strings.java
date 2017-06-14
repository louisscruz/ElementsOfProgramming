package Strings;

import java.util.List;
import java.util.ArrayList;

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

    public static int spreadsheetEncode(final String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            final int c = s.charAt(i) - 'A' + 1;
            result = (result * 26) + c;
        }

        return result;
    }

    public static int replaceAndRemove(int size, char[] s) {
        int writeIdx = 0, aCount = 0;

        for (int i = 0; i < size; i++) {
            if (s[i] != 'b') {
                s[writeIdx++] = s[i];
            }

            if (s[i] == 'a') {
                aCount++;
            }
        }

        int curIdx = writeIdx - 1;
        writeIdx += aCount - 1;
        final int finalSize = writeIdx + 1;

        while (curIdx >= 0) {
            if (s[curIdx] == 'a') {
                s[writeIdx--] = 'd';
                s[writeIdx--] = 'd';
            } else {
                s[writeIdx--] = s[curIdx];
            }
            --curIdx;
        }

        return finalSize;
    }

    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            while (!Character.isLetter(s.charAt(i))) i++;
            while (!Character.isLetter(s.charAt(j))) j--;

            if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) return false;
        }

        return true;
    }

    public static void reverseWords(char[] input) {
        final int n = input.length;

        reverse(input, 0, n - 1);

        int start = 0, end = 0;

        while (start < n) {
            while (start < end || start < n && input[start] == ' ') {
                ++start;
            }

            while (end < start || end < n && input[end] != ' ') {
                ++end;
            }

            reverse(input, start, end - 1);
        }
    }

    private static void reverse(char[] input, int start, int end) {
        while (start < end) {
            char tmp = input[start];
            input[start++] = input[end];
            input[end--] = tmp;
        }
    }

    public static List<String> phoneMnemonic(String number) {
        char[] partialMnemonic = new char[number.length()];
        List<String> mnemonics = new ArrayList<>();
        generateMnemonics(number, 0, partialMnemonic, mnemonics);
        return mnemonics;
    }

    private static final String[] MAPPING = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    private static void generateMnemonics(String number, int digit, char[] partialMnemonic, List<String> mnemonics) {
        if (digit == number.length()) {
            mnemonics.add(new String(partialMnemonic));
        } else {
            final String options = MAPPING[number.charAt(digit) - '0'];

            for (int i = 0; i < options.length(); i++) {
                partialMnemonic[digit] = options.charAt(i);
                generateMnemonics(number, digit + 1, partialMnemonic, mnemonics);
            }
        }
    }
}