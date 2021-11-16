package md2html;

import HT_5.BufferedScanner;
import java.io.Closeable;
import java.io.IOException;
import java.util.Optional;

public class MarkdownBlockTokenizer implements Closeable, AutoCloseable {
    private final BufferedScanner in;

    public MarkdownBlockTokenizer(BufferedScanner bufferedScanner) {
        in = bufferedScanner;
    }

    Optional<String> nextBlock() {
        // TODO
        return Optional.empty();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
