import java.io.Reader;
import java.util.function.IntPredicate;

public class BufferedScanner {
    private static final int defaultCharBufferSize = 8192;


    private Reader in;
    private char[] charBuffer;
    int charBufferPtr;

    public BufferedScanner (Reader reader, int bufferSize) {
        this.in = reader;
        charBuffer = new char[bufferSize];
        charBufferPtr = 0;
    }
    public BufferedScanner(Reader reader) { this(reader, defaultCharBufferSize); }

    public boolean hasNext() {
        return false;
    }

    public String nextSequence(IntPredicate isDelimiter, boolean ignoreEmptySequences) {
        return "";
    }
//    public String nextSequence(IntPredicate isDelimiter) { return nextSequence(isDelimiter, false); }


    public boolean hasNextLine() {
        return false;
    }
    public String nextLine() {
        return "";
    }


    public boolean hasNextInt() {
        return false;
    }
    public int nextInt() {
        return 0;
    }

    public boolean hasNextChar() {
        return false;
    }
    public char nextChar() {
        return 0;
    }

    public boolean hasNextWord() {
        return false;
    }
    public String nextWord() {
        return "";
    }
}
