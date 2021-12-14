package expression.parser.tests;

import bufferedScanning.ReaderBufferizer;
import expression.parser.generic.ArithmeticExpressionTokenizer;
import expression.parser.generic.ParsableSource;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import expression.parser.generic.tokens.NumberToken;
import expression.parser.generic.tokens.OperatorToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class TokenizerTests {

    List<ArithmeticExpressionToken> getTokensFomTokenizer(ArithmeticExpressionTokenizer tok) throws IOException {
        List<ArithmeticExpressionToken> tokens = new ArrayList<>();
        while (true) {
            Random r = new Random(2342543);
            if (r.nextBoolean()) {
                for (int i = 0; i < r.nextInt(10); i++) {
                    tok.viewNextToken();
                }
            }

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
        String testCase = "   (       ---7 -xyj * l0 988 / t0 -14234 ) >>> 123134 x << 12 >> 9700000";

        var tokens = parseTokens(testCase);

    }

    void assertSingleTokenListsEqual(List<ArithmeticExpressionToken> l1, List<ArithmeticExpressionToken> l2) {
        Assert.assertEquals(l1.size(), l2.size());

        for (int i = 0; i < l1.size(); i++) {
            Assert.assertEquals(l1, l2);
        }
    }

    @Test
    public void testOneNumber() throws IOException {
        assertSingleTokenListsEqual(List.of(new NumberToken(1)), parseTokens("1"));
        assertSingleTokenListsEqual(List.of(OperatorToken.MINUS, new NumberToken(1)), parseTokens("-1"));
        assertSingleTokenListsEqual(List.of(OperatorToken.MINUS, new NumberToken(0)), parseTokens("-0"));
        assertSingleTokenListsEqual(List.of(new NumberToken(3324)), parseTokens("     3324     "));
//        var tokens = parseTokens("-1");
//        var tokens = parseTokens("-0");
//        var tokens = parseTokens("0");
    }
}
