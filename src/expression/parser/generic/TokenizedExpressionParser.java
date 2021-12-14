package expression.parser.generic;

import expression.Const;
import expression.TripleExpression;
import expression.generic.ParenthesesTrackingExpression;
import expression.parser.generic.tokens.AbstractOperationToken;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import expression.parser.generic.tokens.NumberToken;
import expression.parser.generic.tokens.ParenthesesToken;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;


public class TokenizedExpressionParser {
    ArithmeticExpressionTokenizer tokenizer;

    public TokenizedExpressionParser(ArithmeticExpressionTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public ParenthesesTrackingExpression parseAll() {
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

    private ParenthesesTrackingExpression parseShiftResult() {
        return null;
    }

    private ParenthesesTrackingExpression parseExpression() {
        return null;
    }

    private ParenthesesTrackingExpression parseTerm() {
        return null;
    }

    private ParenthesesTrackingExpression parseFactor() {
        return tryMatchToken(token -> token instanceof NumberToken).map(token -> (ParenthesesTrackingExpression)new Const(((NumberToken)token).value()))
            .or(() -> tryMatchToken(token -> { if (!(token instanceof AbstractOperationToken operation)) { return false; } return operation.canBeUnary(); })
                    .map(unaryOpToken -> ((AbstractOperationToken)unaryOpToken).constructUnaryExpression(parseFactor()))
            ).or (
                () -> tryMatchToken(token -> token instanceof ParenthesesToken && ((ParenthesesToken)token).openCloseness())
                    .map(p -> {
                        var res = parseShiftResult();
                        expectNext(tk -> tk instanceof ParenthesesToken && !((ParenthesesToken)tk).openCloseness());
                        return res;
                    })
            ).get();
    }


    private Optional<ArithmeticExpressionToken> tryMatchToken(Predicate<ArithmeticExpressionToken> predicate) {
        try {
            if (tokenizer.viewNextToken().isPresent() && predicate.test(tokenizer.viewNextToken().get())) {
                return tokenizer.nextToken();
            }
        } catch (IOException e) {
            throw new RuntimeException("IO expression occurred while trying to get next token!");
        }

        return Optional.empty();
    }

    private void expectNext(Predicate<ArithmeticExpressionToken> predicate) {
        expectNext(predicate, "");
    }

    private void expectNext(Predicate<ArithmeticExpressionToken> predicate, String message) {
        try {
            if (tokenizer.viewNextToken().isEmpty() || predicate.test(tokenizer.viewNextToken().get())) {
                throw new RuntimeException(message);
            }
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException("IOException occured while parsing", e);
        }
    }

//    private List<ArithmeticExpressionToken> tryMatchTokenPattern(List<Predicate<ArithmeticExpressionToken>>) {
//
//    }
}
