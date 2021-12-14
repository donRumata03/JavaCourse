package expression.parser.tests;

import expression.TripleExpression;
import expression.parser.ExpressionParser;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class ParserTests {

    public TripleExpression parseFrom(String testCase) {
        return new ExpressionParser().parse(testCase);

    }

    @Test
    public void testFactor() throws IOException {
        var t1 = parseFrom("-142490");
        var t2 = parseFrom("2342134");
        var t3 = parseFrom("    -   - -  - -2342134");
        var t4 = parseFrom("  t0  -   - -  - l0 -2342134");
//        ExpectException parseFactorFrom("*");
    }

    @Test
    public void testParentheses() throws IOException {
        var t1 = parseFrom("x*y+(z-1   )/10");
//        Assert.assertEquals("-(x + y)", t1.toMiniString());
    }



}
