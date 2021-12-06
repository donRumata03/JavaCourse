package bufferedScanning;


import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.function.IntPredicate;

public class ReaderBufferizer implements Closeable, AutoCloseable {
    private static final int defaultCharBufferSize = 1024;

    private final Reader in;
    private final char[] charBuffer;
    private int currentBufferSize = 0;
    private int charBufferPtr = 0;
//    private int lastBufferSizeIfAny;
//    private boolean hasBuffer;


    public ReaderBufferizer(Reader reader, int bufferSize) {
        this.in = reader;
        charBuffer = new char[bufferSize];
        // lastBufferSizeIfAny = -1;
        // hasBuffer = false;
    }

    public ReaderBufferizer(Reader reader) { this(reader, defaultCharBufferSize); }

    private void tryReadNewChunk() throws IOException {
        if (charBufferPtr >= currentBufferSize) {
            do {
                currentBufferSize = in.read(charBuffer); // Can be -1, 0 or something meaningful…
            } while(currentBufferSize == 0);
            charBufferPtr = 0;
        }
    }

    public boolean hasNextChar() throws IOException {
        // If already have some characters, result definitely exists:
        if (charBufferPtr < currentBufferSize) {
            return true;
        }

        // Currently, buffer is exhausted but stream might be not.
        // The actual answer can't be known until some data arrives or stream is closed.
        tryReadNewChunk();

        // Current buffer size is either -1 (EOS => return false) or > 0 => return true
        return currentBufferSize != -1;
    }

    public char viewNext() throws IOException {
        if (!hasNextChar()) {
            throw new NoSuchElementException("[Bufferizer] EndOfStream: can't view next, there are no symbols to test");
        }
        return charBuffer[charBufferPtr];
    }


    public boolean testNext(IntPredicate predicate) throws IOException {
        return predicate.test(viewNext());
    }

    public boolean consumeIf(IntPredicate predicate) throws IOException {
        if (hasNextChar() && testNext(predicate)) {
            nextChar();
            return true;
        }
        return false;
    }

    public char[] viewNextN(int n) {
        if (n > charBuffer.length) {
            throw new IllegalArgumentException("for ReaderBufferizer::viewNextN(int n) «n» can't be greater than buffer size");
        }
        // TODO!!!
        return new char[0];
    }


    public char nextChar() throws IOException {
        if (!hasNextChar()) {
            throw new NoSuchElementException("[Bufferizer] EndOfStream: can't get next char, there are no symbols to read");
        }

        return charBuffer[charBufferPtr++];
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
