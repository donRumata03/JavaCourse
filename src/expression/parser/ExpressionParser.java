package expression.parser;

import bufferedScanning.ReaderBufferizer;
import expression.TripleExpression;
import expression.parser.generic.ArithmeticExpressionTokenizer;
import expression.parser.generic.ParsableSource;
import java.io.IOException;
import java.io.StringReader;

public class ExpressionParser implements Parser {
    ArithmeticExpressionTokenizer tokenizer;

    public ExpressionParser(ArithmeticExpressionTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    private TripleExpression parseAll() {
        try {
            var result = parseShiftResult();

            if (tokenizer.viewNextToken().isPresent()) {
                throw new RuntimeException(
                    "Expression wasn't fully parsed: something's left starting with: "
                        + tokenizer.viewNextToken().toString()
                );
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException("IOException occured while parsing", e);
        }
    }

    private TripleExpression parseShiftResult() {
        return null;
    }

    private TripleExpression parseExpression() {
        return null;
    }

    private TripleExpression parseTerm() {
        return null;
    }

    private TripleExpression parseFactor() {
        return null;
    }



    @Override
    public TripleExpression parse(String expression) {
        return ExpressionParser.parseExpression(
            new ArithmeticExpressionTokenizer(
                new ParsableSource(new ReaderBufferizer(new StringReader(expression)))
            ));
    }
}
