import java.io.Closeable;
import java.io.IOException;
import java.util.NoSuchElementException;


public class HexAbcScanner implements Closeable, AutoCloseable {
    BufferedScanner rawReader;

    HexAbcScanner (BufferedScanner rawReader) {
        this.rawReader = rawReader;
    }

    public int nextInt() throws IOException {
        String token = rawReader.nextSequenceIgnoreEmpty(Character::isWhitespace);
        if (token == null) {
            throw new NoSuchElementException();
        }

        String stringToParse;
        int radix;
        if (token.length() > 2 && startsWithIgnoreCase(token, "0x")) {
            // Hex number:
            stringToParse = token.substring(2);
            radix = 16;

        } else if (Character.isDigit(token.charAt(0))) {
            // Regular number:
            stringToParse = token;
            radix = 10;
        } else {
            // Abc number:
            stringToParse = toDecimalString(token);
            radix = 10;
        }

        return Integer.parseInt(stringToParse, radix);
    }

    private static boolean startsWithIgnoreCase(String targetString, String prefix) {
        return targetString.length() >= prefix.length() &&
            targetString.substring(0, prefix.length()).toLowerCase().equals(prefix.toLowerCase());
//        return targetString.toLowerCase().startsWith(prefix.toLowerCase());
    }

    private static String toDecimalString(String abcString) {
        StringBuilder resultBuilder = new StringBuilder();

        for (char c: abcString.toCharArray()) {
            resultBuilder.append((char)(c + '0' - 'a'));
        }

        return resultBuilder.toString();
    }

    @Override
    public void close() throws IOException {
        rawReader.close();
    }
}
