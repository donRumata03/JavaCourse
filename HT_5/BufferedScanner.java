package HT_5;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.function.IntPredicate;

public class BufferedScanner implements Closeable, AutoCloseable {
    // Default delimiters:
    private static boolean isFromWord(int c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || (char)c == '\'';
    }

    private static final char LF = 0x000A;
    private static final char CR = 0x000D;
    private static final char[] lineSeparators = { LF, 0x000B, 0x000C, CR, 0x0085, 0x2028, 0x2029 };

    private static boolean isLineSeparator(int c) {
        for (char sep : lineSeparators) {
            if (sep == c) {
                return true;
            }
        }
        return false;
    }

    // Default consumer generating:
    public static DelimiterConsumer generateSingleDelimiterConsumer(IntPredicate isDelimiter) {
        return (BufferedScanner bs) -> bs.consumeCharIf(isDelimiter);
    }



    // Special Read Indicators:
    private static final int DIDNT_READ_ANYTHING_EVER = -1;
    private static final int READ_CHARACTER_HIDDEN = -2;

    // Fields
    private final ReaderBufferizer in;
    private int read = DIDNT_READ_ANYTHING_EVER;

    /**
     * Scanner has been «touched» if either characters have been read or nextSequence[IgnoreEmpty] has been called
    */
    private boolean hasBeenTouched = false;

    // Constructors:
    public BufferedScanner (Reader reader) {
        in = new ReaderBufferizer(reader);
    }
    public BufferedScanner (Reader reader, int bufferSize) {
        in = new ReaderBufferizer(reader, bufferSize);
    }


    /**
     * If previous read character was CR and the next character is LF, it reads the LF
     */
    private void tryConsumeNewlineSecondHalf() throws IOException {
        if (read == CR && in.hasNextChar() && in.testNext((int ch) -> ch == LF)) {
            in.nextChar();
            read = READ_CHARACTER_HIDDEN; // Not to consume second half more than once
        }
    }

    /**
     * @return How many newlines consumed
     */
    public int consumeDelimitersAndNewlines(IntPredicate isDelimiter) throws IOException {
        int newlinesConsumed = 0;
        while (in.hasNextChar()) {
            if (in.testNext(BufferedScanner::isLineSeparator)) {
                read = in.nextChar();
                tryConsumeNewlineSecondHalf();
                newlinesConsumed++;
            } else if (in.testNext(isDelimiter)) {
                read = in.nextChar();
            } else {
                break;
            }
        }

        return newlinesConsumed;
    }

    /**
     * If buffer is empty, returns <code>null</code>;
     *
     * Else: Consumes one <b>delimiter if</b> there are some,
     * then — returns continuous, delimiter-free sequence of characters
     */
    public String nextSequence(IntPredicate isDelimiterStart, DelimiterConsumer consumeOneDelimiter) throws IOException {
        if (!in.hasNextChar()) {
            return null;
        }

        // Perform the consumption:
        if (hasBeenTouched) consumeOneDelimiter.consumeOneDelimiter(this);

        StringBuilder builder = new StringBuilder();

        while (in.hasNextChar() && !in.testNext(isDelimiterStart)) {
            read = in.nextChar();
            builder.append((char)read);
        }

        hasBeenTouched = true;
        return builder.toString();
    }
    public String nextSequence(IntPredicate isDelimiter) throws IOException {
        return nextSequence(isDelimiter, BufferedScanner.generateSingleDelimiterConsumer(isDelimiter));
    }


    /**
     * If buffer is empty, returns <code>null</code>;
     *
     * Else: Consumes delimiter<b>s while</b> there are some,
     * then — returns continuous, delimiter-free sequence of characters
     */
    public String nextSequenceIgnoreEmpty(IntPredicate isDelimiterStart, DelimiterConsumer consumeOneDelimiter) throws IOException {
        // Skip all preceding delimiters
        while (consumeOneDelimiter.consumeOneDelimiter(this));

        return nextSequence(isDelimiterStart, consumeOneDelimiter);
    }
    public String nextSequenceIgnoreEmpty(IntPredicate isDelimiter) throws IOException {
        return nextSequenceIgnoreEmpty(isDelimiter, BufferedScanner.generateSingleDelimiterConsumer(isDelimiter));
    }

    // ---------------------------       Simple public interface        ---------------------------

    public boolean hasNextChar() throws IOException {
        return in.hasNextChar();
    }

    /**
     * Test next char without consuming it
     */
    public boolean testNextChar(IntPredicate t) throws IOException {
        return in.testNext(t);
    }
    public boolean consumeCharIf(IntPredicate t) throws IOException {
        boolean testResult = in.testNext(t);
        if (testResult) {
            nextChar();
        }
        return testResult;
    }

    /**
     * @return Next char in the stream
     * @throws NoSuchElementException is the stream is exhausted
     */
    public char nextChar() throws IOException {
        if (!in.hasNextChar()) {
            throw new NoSuchElementException("Can't read next char: stream is exhausted!");
        }
        hasBeenTouched = true;
        return (char) (read = in.nextChar());
    }

    /**
     * @return next int in stream
     * @throws NoSuchElementException is EOS reached
     */
    public Integer nextInt() throws IOException, NoSuchElementException, NumberFormatException {
        String token = nextSequenceIgnoreEmpty(Character::isWhitespace);
        if (token == null) {
             throw new NoSuchElementException("EndOfStream: There are no integers to read");
        }
        return Integer.parseInt(token);
    }

    /**
     * @return next line of stream; «line» definition is just like in the Unicode standard:
     *         https://en.wikipedia.org/wiki/Newline#Unicode; Empty lines are not ignored;
     *         If there are no lines left, null is returned
     */
    public String nextLine() throws IOException {
        String lineAttempt = nextSequence(
            BufferedScanner::isLineSeparator,
            (BufferedScanner bs) -> {
                if (bs.consumeCharIf(BufferedScanner::isLineSeparator)) {
                    tryConsumeNewlineSecondHalf();
                    return true;
                }
                return false;
            }
        );

        if (lineAttempt != null) {
            tryConsumeNewlineSecondHalf();
        }
        return lineAttempt;
    }


    /**
     * @return next word in the stream or null (word delimiters are <code>!BufferedScanner::isFromWord()</code>)
    */
    public String nextWord() throws IOException {
        return nextSequenceIgnoreEmpty((int ch) -> !BufferedScanner.isFromWord(ch));
    }


    @Override
    public void close() throws IOException {
        in.close();
    }
}
