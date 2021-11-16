package md2html;

import HT_5.BufferedScanner;
import java.io.Closeable;
import java.io.IOException;
import java.util.Optional;


// #[derive(close)]
public class MarkdownInlineTokenizer implements Closeable, AutoCloseable {
    BufferedScanner in;

    public MarkdownInlineTokenizer(BufferedScanner in) {
        this.in = in;
    }

    public Optional<InlineMarkdownToken> nextToken() {
        return Optional.empty();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
