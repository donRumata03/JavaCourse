package HT_5;
import java.io.IOException;

public interface DelimiterConsumer {
    boolean consumeOneDelimiter(BufferedScanner bs) throws IOException;
}
