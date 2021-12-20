package expression.parser.generic;

import expression.Const;
import expression.Variable;
import expression.generic.ParenthesesTrackingExpression;
import expression.parser.generic.tokens.AbstractOperationToken;
import expression.parser.generic.tokens.NumberToken;
import expression.parser.generic.tokens.OperatorToken;
import expression.parser.generic.tokens.ParenthesesToken;
import expression.parser.generic.tokens.VariableToken;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class TokenizedExpressionParser {
    private final BaseTokenParser tokenParser;

    public TokenizedExpressionParser(ArithmeticExpressionTokenizer tokenizer) {
        this.tokenParser = new BaseTokenParser(tokenizer);
    }

    public ParenthesesTrackingExpression parseAll() throws ParseException {
        var result = parseShiftResult();

        if (tokenParser.viewRuntimeErrorizedNextToken().isPresent()) {
            throw new ParseException(
                "Expression wasn't fully parsed: something's left starting with: "
                    + tokenParser.viewRuntimeErrorizedNextToken().toString()
            );
        }

        return result;
    }

    private ParenthesesTrackingExpression parseShiftResult() throws ParseException {
        return parseLeftAssociativePriorityLayer(
            TokenizedExpressionParser::parseExpression, List.of(
                OperatorToken.SHIFT_LEFT,
                OperatorToken.ARITHMETICAL_SHIFT,
                OperatorToken.LOGICAL_SHIFT_RIGHT
            )
        );
    }

    private ParenthesesTrackingExpression parseExpression() throws ParseException {
        return parseLeftAssociativePriorityLayer(TokenizedExpressionParser::parseTerm, List.of(OperatorToken.PLUS, OperatorToken.MINUS));
    }

    private ParenthesesTrackingExpression parseTerm() throws ParseException {
        return parseLeftAssociativePriorityLayer(TokenizedExpressionParser::parseFactor, List.of(OperatorToken.MULTIPLY, OperatorToken.DIVIDE));
    }

    private ParenthesesTrackingExpression parseLeftAssociativePriorityLayer (
        Function<TokenizedExpressionParser, ParenthesesTrackingExpression> prevLayer,
        List<OperatorToken> operators
    ) throws ParseException
    {
        ParenthesesTrackingExpression left = prevLayer.apply(this);

        while (true) {
            var mayBeOperator = tokenParser.tryMatchToken(token -> {
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
        return maybeParsePositiveNumber()
            .or(this::maybeParseVariable)
            .or(this::maybeParseUnaryOperation)
            .or(this::maybeParseExpressionInParentheses)
            .orElseThrow(() -> {
                throw new ParseException(
                    "Can't parse atomic expression part. " +
                    "Should be one of { number, variable, unaryOp, '(' expr ')' }"
                );
            });
    }

    private Optional<ParenthesesTrackingExpression> maybeParsePositiveNumber() {
        return tokenParser.tryMatchToken(token -> token instanceof NumberToken)
            .map(token -> new Const(
                Integer.parseInt(((NumberToken)token).nonParsedValue())
            ));
    }

    private Optional<ParenthesesTrackingExpression> maybeParseUnaryOperation() {
        return tokenParser
            .tryMatchToken(token -> token instanceof AbstractOperationToken operation && operation.canBeUnary())
            .map(unaryOpToken -> {
                if (unaryOpToken == OperatorToken.MINUS) {
                    var tryIntToken = tokenParser.tryMatchToken(t -> t instanceof NumberToken, false);
                    if (tryIntToken.isPresent()) {
                        return new Const(Integer.parseInt(
                            "-" + ((NumberToken)tryIntToken.get()).nonParsedValue()
                        ));
                    }
                }
                return ((AbstractOperationToken)unaryOpToken).constructUnaryExpression(parseFactor());
            });
    }

    private Optional<ParenthesesTrackingExpression> maybeParseVariable() {
        return tokenParser
            .tryMatchToken(token -> token instanceof VariableToken)
            .map(varToken -> new Variable(((VariableToken)varToken).varName()));
    }

    private Optional<ParenthesesTrackingExpression> maybeParseExpressionInParentheses() {
        return tokenParser
            .tryMatchToken(token -> token instanceof ParenthesesToken && ((ParenthesesToken)token).openCloseness())
            .map(p -> {
                var res = parseShiftResult();
                tokenParser.expectNext(tk -> tk instanceof ParenthesesToken && !((ParenthesesToken)tk).openCloseness(),
                    "„)” is expected after parentheses-surrounded expression part"
                );
                return res;
            });
    }
}
