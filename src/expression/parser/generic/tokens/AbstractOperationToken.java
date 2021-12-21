package expression.parser.generic.tokens;

import expression.generic.ParenthesesTrackingExpression;

public interface AbstractOperationToken extends ArithmeticExpressionToken {
    boolean canBeUnary();
    boolean canBeBinary();

    ParenthesesTrackingExpression constructUnaryExpression(ParenthesesTrackingExpression child);

    ParenthesesTrackingExpression constructBinaryExpression(
        ParenthesesTrackingExpression left, ParenthesesTrackingExpression right, boolean checked
    );
}
