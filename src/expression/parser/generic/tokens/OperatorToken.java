package expression.parser.generic.tokens;

import expression.Add;
import expression.ArithmeticShiftRight;
import expression.Divide;
import expression.LogicalShiftRight;
import expression.Multiply;
import expression.Negate;
import expression.ShiftLeft;
import expression.Subtract;
import expression.generic.ParenthesesTrackingExpression;

public enum OperatorToken implements AbstractOperationToken {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    SHIFT_LEFT,
    LOGICAL_SHIFT_RIGHT,
    ARITHMETICAL_SHIFT;

    @Override
    public boolean canBeUnary() {
        return this == MINUS;
    }

    @Override
    public boolean canBeBinary() {
        return true;
    }

    @Override
    public ParenthesesTrackingExpression constructUnaryExpression(ParenthesesTrackingExpression child) {
        assert canBeUnary();

        return new Negate(child);
    }

    @Override
    public ParenthesesTrackingExpression constructBinaryExpression(ParenthesesTrackingExpression left,
        ParenthesesTrackingExpression right)
    {
        return switch (this) {
            case PLUS -> new Add(left, right);
            case MINUS -> new Subtract(left, right);
            case MULTIPLY -> new Multiply(left, right);
            case DIVIDE -> new Divide(left, right);
            case SHIFT_LEFT -> new ShiftLeft(left, right);
            case LOGICAL_SHIFT_RIGHT -> new LogicalShiftRight(left, right);
            case ARITHMETICAL_SHIFT -> new ArithmeticShiftRight(left, right);
        };
    }


}
