package expression.parser;

import expression.TripleExpression;
import expression.parser.generic.ArithmeticExpressionTokenizer;
import java.io.IOException;


public class TokenizedExpressionParser {
    ArithmeticExpressionTokenizer tokenizer;

    public TokenizedExpressionParser(ArithmeticExpressionTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public TripleExpression parseAll() {
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
}
