package md2html;

import HT_5.BufferedScanner;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MarkdownBlockTokenizer implements Closeable, AutoCloseable {
    private final BufferedScanner in;

    public MarkdownBlockTokenizer(BufferedScanner bufferedScanner) {
        in = bufferedScanner;
    }

    Optional<String> nextBlock() throws IOException {
        if (!in.hasNextChar()) {
            return Optional.empty();
        }

        List<String> lines = new ArrayList<>();

        // Split here by empty lines:
        while (true) {
            String nextLine = in.nextLine();
            if (nextLine.equals("")) {
                break;
            }
            lines.add(nextLine);
        }
        return Optional.of(String.join("\n", lines));
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
