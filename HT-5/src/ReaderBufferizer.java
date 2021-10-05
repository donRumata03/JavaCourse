import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;

public class ReaderBufferizer implements Closeable, AutoCloseable {
    private static final int defaultCharBufferSize = 8192;

    private Reader in;
    private char[] charBuffer;
    private int lastBufferSizeIfAny;
    private int charBufferPtr;


    public ReaderBufferizer(Reader reader, int bufferSize) {
        this.in = reader;
        charBuffer = new char[bufferSize];
        charBufferPtr = 0;
        lastBufferSizeIfAny = -1;
    }

    public ReaderBufferizer(Reader reader) { this(reader, defaultCharBufferSize); }

    private void tryReadNewChunk() throws IOException {
        if (charBufferPtr >= charBuffer.length && lastBufferSizeIfAny == -1) {
            int charactersRead = in.read(charBuffer);
            System.out.println(charactersRead);
            if (charactersRead != charBuffer.length) {
                // This is the last buffer that might be not full
                // It can even have length «0»
                lastBufferSizeIfAny = charactersRead;
            }
        }
    }

    private int currentBufferLength() {
        return (lastBufferSizeIfAny == -1) ? charBuffer.length : lastBufferSizeIfAny;
    }

    public boolean hasNextChar() throws IOException {
        tryReadNewChunk();

        return charBufferPtr < currentBufferLength();
    }
    public char nextChar() throws IOException {
        if (!hasNextChar()) {
            throw new NoSuchElementException("EndOfStream: There are no symbols to read");
        }

        return charBuffer[charBufferPtr];
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
