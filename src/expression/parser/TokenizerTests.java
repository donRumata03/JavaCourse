package expression.parser;

import bufferedScanning.ReaderBufferizer;
import expression.parser.generic.ArithmeticExpressionTokenizer;
import expression.parser.generic.ParsableSource;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TokenizerTests {

    @Test
    public void test() throws IOException {
        String testCase = "   (       ---7 -xyj * l0 988 / t0 -14234 )";

        var tok = new ArithmeticExpressionTokenizer(new ParsableSource(new ReaderBufferizer(new StringReader(testCase))));

        List<ArithmeticExpressionToken> tokens = new ArrayList<>();
        while (true) {
            var nt = tok.nextToken();
            if (nt.isPresent()) {
                tokens.add(nt.get());
            } else {
                break;
            }
        }

    }

}
