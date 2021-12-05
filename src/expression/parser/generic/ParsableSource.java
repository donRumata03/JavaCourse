package expression.parser.generic;

import HT_5.ReaderBufferizer;
import java.io.IOException;
import java.util.function.IntPredicate;

public class ParsableSource {
    private final ReaderBufferizer in;
    private int nextPos = 0;

    public ParsableSource(ReaderBufferizer in) {
        this.in = in;
    }

    public char consumeChar() throws IOException {
        nextPos++;
        return in.nextChar();
    }
    
    public char viewChar() throws IOException {
        return in.viewNext();
    }

    public boolean testNextChar(IntPredicate predicate) throws IOException {
        return in.testNext(predicate);
    }

    public boolean consumeIfExpected(char expected) throws IOException {
        if (viewChar() == expected) {
            consumeChar();
            return true;
        }
        return false;
    }

    public boolean nextIsBetweenInclusive(final char from, final char to) throws IOException {
        return testNextChar(ch -> from <= ch && ch <= to );
    }

    public int getNextPos() {
        return nextPos;
    }
}
