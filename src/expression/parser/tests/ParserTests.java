package expression.parser.tests;

import expression.TripleExpression;
import expression.parser.ExpressionParser;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;

public class ParserTests {

    public TripleExpression parseFactorFrom(String testCase) {
        return new ExpressionParser().parse(testCase);

    }

    @Test
    public void testFactor() throws IOException {
        var t1 = parseFactorFrom("-142490");
        var t2 = parseFactorFrom("2342134");
        var t3 = parseFactorFrom("    -   - -  - -2342134");
        var t4 = parseFactorFrom("  t0  -   - -  - l0 -2342134");
//        ExpectException parseFactorFrom("*");
    }


}
