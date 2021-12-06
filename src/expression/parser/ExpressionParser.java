package expression.parser;

import bufferedScanning.ReaderBufferizer;
import expression.TripleExpression;
import expression.parser.generic.ArithmeticExpressionTokenizer;
import expression.parser.generic.ParsableSource;
import java.io.StringReader;

public class ExpressionParser implements Parser {

    @Override
    public TripleExpression parse(String expression) {
        return ExpressionParser.parseExpression(
            new ArithmeticExpressionTokenizer(
                new ParsableSource(new ReaderBufferizer(new StringReader(expression)))
            ));
    }

    public static TripleExpression parseExpression(ArithmeticExpressionTokenizer tokenizer) {
        return null;
    }

    public static TripleExpression parseTerm(ArithmeticExpressionTokenizer tokenizer) {
        return null;
    }
}
