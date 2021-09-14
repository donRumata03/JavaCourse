import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class SumBigIntegerHex {
    public static void main(String[] args) {
        BigInteger totalSum = BigInteger.ZERO;

        for (String arg : args) {
            List<String> numberStrings = splitBy(arg, Character::isWhitespace);

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


    private static ArrayList<String> splitBy(String stringToSplit, Predicate<Character> isSpace) {
        ArrayList<String> result = new ArrayList<String>();

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

    private static boolean startsWithIgnoreCase(String targetString, String prefix) {
        return prefix.length() <= targetString.length() && targetString.substring(0, prefix.length()).equalsIgnoreCase(prefix);
    }
}
