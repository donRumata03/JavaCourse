package HT_5;


import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.function.IntPredicate;

public class ReaderBufferizer implements Closeable, AutoCloseable {
    private static final int defaultCharBufferSize = 1024;

    private final Reader in;
    private final char[] charBuffer;
    private int lastBufferSizeIfAny;
    private int charBufferPtr;
    private boolean hasBuffer;


    public ReaderBufferizer(Reader reader, int bufferSize) {
        this.in = reader;
        charBuffer = new char[bufferSize];
        charBufferPtr = 0;
        lastBufferSizeIfAny = -1;
        hasBuffer = false;
    }

    public ReaderBufferizer(Reader reader) { this(reader, defaultCharBufferSize); }

    private void tryReadNewChunk() throws IOException {
        if ((charBufferPtr >= charBuffer.length && lastBufferSizeIfAny == -1) || !hasBuffer) {
            // Need to and can try to increase:

            int proposedBufferSize = 0;
            int read;
            do {
                read = in.read(charBuffer, proposedBufferSize, charBuffer.length - proposedBufferSize);
                if (read != -1) {
                    proposedBufferSize += read;
                }
            } while (read != -1 && proposedBufferSize != charBuffer.length);

            // System.out.println(charactersRead);
            if (read == -1) {
                // This is the last buffer that might be not full
                // It can even have length «0»
                lastBufferSizeIfAny = proposedBufferSize;
            } else { // Read full buffer

            }
            charBufferPtr = 0;
            hasBuffer = true;
        }
    }

    private int currentBufferLength() {
        return (lastBufferSizeIfAny == -1) ? charBuffer.length : lastBufferSizeIfAny;
    }

    public boolean hasNextChar() throws IOException {
        tryReadNewChunk();

        return charBufferPtr < currentBufferLength();
    }

    public char viewNext() throws IOException {
        if (!hasNextChar()) {
            throw new NoSuchElementException("EndOfStream: There are no symbols to test");
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

    public char nextChar() throws IOException {
        if (!hasNextChar()) {
            throw new NoSuchElementException("[Bufferizer] EndOfStream: There are no symbols to read");
        }

        return charBuffer[charBufferPtr++];
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
