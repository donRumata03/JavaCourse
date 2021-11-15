package md2html;

import HT_5.BufferedScanner;
import java.io.Closeable;
import java.io.IOException;
import java.util.Optional;


// #[derive(close)]
public class MarkdownTokenizer implements Closeable, AutoCloseable {
    BufferedScanner in;

    public MarkdownTokenizer(BufferedScanner in) {
        this.in = in;
    }

    public Optional<MarkdownToken> nextToken() {
        return Optional.empty();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
