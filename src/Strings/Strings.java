package Strings;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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

    public static String lookAndSay(int n) {
        String s = "1";

        for (int i = 1; i < n; i++) {
            s = nextNumber(s);
        }

        return s;
    }

    private static String nextNumber(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int count = 1;

            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                ++i;
                ++count;
            }

            result.append(count);
            result.append(s.charAt(i));
        }

        return result.toString();
    }

    public static int romanToInteger(String s) {
        Map<Character, Integer> T = new HashMap<Character, Integer>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };

        int sum = T.get(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            if (T.get(s.charAt(i)) < T.get(s.charAt(i + 1))) {
                sum -= T.get(s.charAt(i));
            } else {
                sum += T.get(s.charAt(i));
            }
        }

        return sum;
    }

    public static List<String> getValidAddress(String s) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i < 4 && i < s.length();  i++) {
            final String first = s.substring(0, i);

            if (isValidPart(first)) {
                for (int j = 1; i + j < s.length() && j < 4; j++) {
                    final String second = s.substring(i, i + j);

                    if (isValidPart(second)) {
                        for (int k = 1; i + j + k < s.length() && k < 4; k++) {
                            final String third = s.substring(i + j, i + j + k);
                            final String fourth = s.substring(i + j + k);

                            if (isValidPart(third) && isValidPart(fourth)) {
                                result.add(first + "." + second + "." + third + "." + fourth);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    private static boolean isValidPart(String s) {
        if (s.length() > 3) return false;

        if (s.startsWith("0") && s.length() > 1) return false;

        final int val = Integer.parseInt(s);

        return val <= 255 && val >= 0;
    }

    public static String snakeString(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < s.length(); i += 4) {
            result.append(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i += 2) {
            result.append(s.charAt(i));
        }

        for (int i = 3; i < s.length(); i += 4) {
            result.append(s.charAt(i));
        }

        return result.toString();
    }

    public static String decoding(String s) {
        int count = 0;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';
            } else {
                while (count > 0) {
                    result.append(c);
                    count--;
                }
            }
        }

        return result.toString();
    }

    public static String encoding(String s) {
        int count = 1;

        StringBuilder ss = new StringBuilder();

        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                ss.append(count);
                ss.append(s.charAt(i - 1));
                count = 1;
            } else {
                count++;
            }
        }

        return ss.toString();
    }

    public static int rabinKarp(String t, String s) {
        if (s.length() > t.length()) {
            return -1;
        }

        final int BASE = 26;

        int tHash = 0, sHash = 0;
        int powerS = 1;

        for (int i = 0; i < s.length(); i++) {
            powerS = i > 0 ? powerS * BASE : 1;
            tHash = tHash * BASE + t.charAt(i);
            sHash = sHash * BASE + s.charAt(i);
        }

        for (int i = s.length(); i < t.length(); i++) {
            if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
                return i - s.length();
            }

            tHash -= t.charAt(i - s.length()) * powerS;
            tHash = tHash * BASE + t.charAt(i);
        }

        if (tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
            return t.length() - s.length();
        }

        return -1;
    }
}
