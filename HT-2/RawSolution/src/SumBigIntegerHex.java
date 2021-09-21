import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class SumBigIntegerHex {
    public static void main(String[] args) {
        BigInteger totalSum = BigInteger.ZERO;

        for (String arg : args) {
            List<String> numberStrings = splitBy(arg, Character::isWhitespace);
            System.err.println(numberStrings);

            for (String numberString : numberStrings) {
                int radix;
                if (startsWithIgnoreCase(numberString, "0x")) {
                    radix = 16;
                    numberString = numberString.substring(2);
                } else {
                    radix = 10;
                }
                totalSum = totalSum.add(new BigInteger(numberString, radix));
            }
        }

        System.out.println(totalSum);
    }

    private static List<String> splitBy(String stringToSplit, IntPredicate isSpace) {
        List<String> result = new ArrayList<>();

        int n = stringToSplit.length();
        for (int wordStart = 0, wordEnd = 0; wordEnd <= n; wordEnd++) {
            if (wordEnd == n || isSpace.test(stringToSplit.charAt(wordEnd))) {
                if (wordEnd > wordStart) {
                    result.add(stringToSplit.substring(wordStart, wordEnd));
                }
                wordStart = wordEnd + 1;
            }

        }

        return result;
    }

    private static boolean startsWithIgnoreCase(String targetString, String prefix) {
        return targetString.toLowerCase().startsWith(prefix.toLowerCase());
    }

    private static List<String> middle_splitBy(String stringToSplit, IntPredicate isSpace) {
        List<String> result = new ArrayList<String>();

        int n = stringToSplit.length();
        int index = 0;
        while (index < n) {
            while (index < n && isSpace.test(stringToSplit.charAt(index))) {    // Looking for a word start:
                index++;
            }
            if (index == n) {
                break;
            }
            int wordStart = index;
            while (index < n && !isSpace.test(stringToSplit.charAt(index))) {     // Looking for a work end:
                index++;
            }
            result.add(stringToSplit.substring(wordStart, index));
        }

        return result;
    }

    private static List<String> dummySplitBy(String stringToSplit, Predicate<Character> isSpace) {
        List<String> result = new ArrayList<String>();

        boolean inNumber = false;
        int numberStartIndex = 0;

        for (int charIndex = 0; charIndex <= stringToSplit.length(); charIndex++) {
            boolean isEnd = charIndex == stringToSplit.length();
            boolean thisCharacterIsSpace = !isEnd && isSpace.test(stringToSplit.charAt(charIndex));

            if (inNumber && (isEnd || thisCharacterIsSpace)) {
                // Handle number end:
                result.add(stringToSplit.substring(numberStartIndex, charIndex));
                inNumber = false;
            } else if (!inNumber && !isEnd && !thisCharacterIsSpace) {
                // Handle number start:
                inNumber = true;
                numberStartIndex = charIndex;
            }
        }

        return result;
    }


}
