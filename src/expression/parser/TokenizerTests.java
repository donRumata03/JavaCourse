package expression.parser;

import bufferedScanning.ReaderBufferizer;
import expression.parser.generic.ArithmeticExpressionTokenizer;
import expression.parser.generic.ParsableSource;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import expression.parser.generic.tokens.NumberToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TokenizerTests {

    List<ArithmeticExpressionToken> getTokensFomTokenizer(ArithmeticExpressionTokenizer tok) throws IOException {
        List<ArithmeticExpressionToken> tokens = new ArrayList<>();
        while (true) {
            var nt = tok.nextToken();
            if (nt.isPresent()) {
                tokens.add(nt.get());
            } else {
                break;
            }
        }

        return tokens;
    }

    List<ArithmeticExpressionToken> parseTokens(String str) throws IOException {
        return getTokensFomTokenizer(new ArithmeticExpressionTokenizer(new ParsableSource(new ReaderBufferizer(new StringReader(str)))));
    }

    @Test
    public void test() throws IOException {
        String testCase = "   (       ---7 -xyj * l0 988 / t0 -14234 )";

        var tokens = parseTokens(testCase);

    }

    void assertSingleTokenListsEqual(List<ArithmeticExpressionToken> l1, List<NumberToken> l2) {
        Assert.assertEquals(l1.size(), l2.size());

        for (int i = 0; i < l1.size(); i++) {
            Assert.assertEquals(((NumberToken)l1.get(i)).value(), l2.get(i).value());
        }
    }

    @Test
    public void testOneNumber() throws IOException {
        assertSingleTokenListsEqual(parseTokens("1"), List.of(new NumberToken(1)));
        assertSingleTokenListsEqual(parseTokens("-1"), List.of(new NumberToken(-1)));
        assertSingleTokenListsEqual(parseTokens("-0"), List.of(new NumberToken(0)));
        assertSingleTokenListsEqual(parseTokens("0"), List.of(new NumberToken(0)));
//        var tokens = parseTokens("-1");
//        var tokens = parseTokens("-0");
//        var tokens = parseTokens("0");
    }
}
