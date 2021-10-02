import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.function.IntPredicate;

public class BufferedScanner {
    private static boolean isFromWord(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }


    private static final int defaultCharBufferSize = 8192;

    private Reader in;
    private char[] charBuffer;
    private int lastBufferSizeIfAny;
    private int charBufferPtr;


    public BufferedScanner (Reader reader, int bufferSize) {
        this.in = reader;
        charBuffer = new char[bufferSize];
        charBufferPtr = 0;
        lastBufferSizeIfAny = -1;
    }
    public BufferedScanner(Reader reader) { this(reader, defaultCharBufferSize); }

    private void tryReadNewChunk() {

    }
    public boolean hasNextChar() {
        return false;
    }
    public char nextChar() throws NoSuchElementException {
        return 0;
    }


    public String nextSequence(IntPredicate isDelimiter, boolean ignoreEmptySequences) {

        return "";
    }
//    public String nextSequence(IntPredicate isDelimiter) { return nextSequence(isDelimiter, false); }



    public Integer nextInt() {
        return 0;
    }

    public String nextLine() {
        return "";
    }


    public String nextWord() {
        return "";
    }
}
