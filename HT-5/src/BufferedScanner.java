import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.function.IntPredicate;

public class BufferedScanner implements Closeable, AutoCloseable {
    private static boolean isFromWord(int c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || (char)c == '\'';
    }

    ReaderBufferizer in;
    int read = -1;

    public BufferedScanner (Reader reader) {
        in = new ReaderBufferizer(reader);
    }
    public BufferedScanner (Reader reader, int bufferSize) {
        in = new ReaderBufferizer(reader, bufferSize);
    }



    public String nextSequence(IntPredicate isDelimiter) throws IOException {
        if (!in.hasNextChar()) {
            throw new NoSuchElementException("EndOfStream: There are no sequences to read");
        }

        StringBuilder builder = new StringBuilder();

        while (in.hasNextChar()) {
            read = in.nextChar();
            if (isDelimiter.test(read)) {
                break;
            }
            builder.append((char)read);
        }
        return builder.toString();
    }

    public String nextSequenceIgnoreEmpty(IntPredicate isDelimiter) throws IOException {
        // Skip delimiters
        // (may be drastically faster than calling nextSequence every time while it doesn't become empty
        // for files with a lot of spaces)
        while (in.hasNextChar() && in.testNext(isDelimiter)) {
            read = in.nextChar();
        }

        // Read is now first character in sequence or EOS
        return nextSequence(isDelimiter);
    }
//    public String nextSequence(IntPredicate isDelimiter) { return nextSequence(isDelimiter, false); }



    public Integer nextInt() throws IOException, NoSuchElementException {
        return Integer.parseInt(nextSequenceIgnoreEmpty(Character::isWhitespace));
    }

    public String nextLine() {
        // String result = nextSequence()
        return null;
    }


    public String nextWord() throws IOException {
        try {
            return nextSequenceIgnoreEmpty((int ch) -> !BufferedScanner.isFromWord(ch));
        } catch (NoSuchElementException e) {
            return null;
        }
    }


    @Override
    public void close() throws IOException {
        in.close();
    }
}
