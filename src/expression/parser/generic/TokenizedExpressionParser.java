package expression.parser.generic;

import expression.Const;
import expression.TripleExpression;
import expression.Variable;
import expression.generic.ParenthesesTrackingExpression;
import expression.parser.ExpressionParser;
import expression.parser.generic.tokens.AbstractOperationToken;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import expression.parser.generic.tokens.NumberToken;
import expression.parser.generic.tokens.OperatorToken;
import expression.parser.generic.tokens.ParenthesesToken;
import expression.parser.generic.tokens.VariableToken;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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
        return parsePriorityLayer(
            TokenizedExpressionParser::parseExpression, List.of(
                OperatorToken.SHIFT_LEFT,
                OperatorToken.ARITHMETICAL_SHIFT,
                OperatorToken.LOGICAL_SHIFT_RIGHT
            )
        );
    }

    private ParenthesesTrackingExpression parseExpression() {
        return parsePriorityLayer(TokenizedExpressionParser::parseTerm, List.of(OperatorToken.PLUS, OperatorToken.MINUS));
    }

    private ParenthesesTrackingExpression parseTerm() {
        return parsePriorityLayer(TokenizedExpressionParser::parseFactor, List.of(OperatorToken.MULTIPLY, OperatorToken.DIVIDE));
    }

    private ParenthesesTrackingExpression parsePriorityLayer(
        Function<TokenizedExpressionParser, ParenthesesTrackingExpression> prevLayer,
        List<OperatorToken> operators
    ) {
        ParenthesesTrackingExpression left = prevLayer.apply(this);

        while (true) {
            var mayBeOperator = tryMatchToken(token -> {
                if (!(token instanceof OperatorToken operator)) {
                    return false;
                }
                return operators.contains(operator);
            });
            if (mayBeOperator.isEmpty()) {
                break;
            }

            left = ((AbstractOperationToken)mayBeOperator.get()).constructBinaryExpression(left, prevLayer.apply(this));
        }

        return left;
    }

    private ParenthesesTrackingExpression parseFactor() {
        return tryMatchToken(token -> token instanceof NumberToken).map(token -> (ParenthesesTrackingExpression)new Const((int)((NumberToken)token).value()))
            .or(() -> tryMatchToken(token -> token instanceof AbstractOperationToken operation && operation.canBeUnary())
                    .map(unaryOpToken -> {
                        if (unaryOpToken == OperatorToken.MINUS) {
                            var tryIntToken = tryMatchToken(t -> t instanceof NumberToken);
                            if (tryIntToken.isPresent()) return new Const((int)(-((NumberToken)tryIntToken.get()).value()));
                        }
                        return ((AbstractOperationToken)unaryOpToken).constructUnaryExpression(parseFactor());
                    })
            ).or(() -> tryMatchToken(token -> token instanceof VariableToken)
                    .map(varToken -> new Variable(((VariableToken)varToken).varName()))
            ).or(
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
            if (tokenizer.viewNextToken().isEmpty() || !predicate.test(tokenizer.viewNextToken().get())) {
                throw new RuntimeException(message);
            }
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred while parsing", e);
        }
    }

//    private List<ArithmeticExpressionToken> tryMatchTokenPattern(List<Predicate<ArithmeticExpressionToken>>) {
//
//    }
}
