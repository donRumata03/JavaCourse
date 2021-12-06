package md2html;

import bufferedScanning.BufferedScanner;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MarkdownBlockTokenizer implements Closeable, AutoCloseable {
    private final BufferedScanner in;
    String lastReadLine = "";

    public MarkdownBlockTokenizer(BufferedScanner bufferedScanner) {
        in = bufferedScanner;
    }

    Optional<String> nextBlock() throws IOException {
        // Skip empty lines before paragraph:
        while (lastReadLine != null && lastReadLine.isEmpty()) {
            lastReadLine = in.nextLine();
        }

        if (lastReadLine == null) {
            return Optional.empty();
        }

        List<String> lines = new ArrayList<>();

        // Split here by empty lines:
        while (lastReadLine != null && !lastReadLine.isEmpty()) {
            lines.add(lastReadLine);
            lastReadLine = in.nextLine();
        }
        return Optional.of(String.join("\n", lines));
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
