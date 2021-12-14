package expression.parser.generic.tokens;


import expression.LeadingZeroes;
import expression.TrailingZeroes;
import expression.generic.ParenthesesTrackingExpression;

public enum FunctionToken implements AbstractOperationToken {
    l0,
    t0;

    @Override
    public boolean canBeUnary() {
        return true;
    }

    @Override
    public boolean canBeBinary() {
        return false;
    }

    @Override
    public ParenthesesTrackingExpression constructUnaryExpression(ParenthesesTrackingExpression child) {
        return (this == l0) ?
            new LeadingZeroes(child) : new TrailingZeroes(child);
    }

    @Override
    public ParenthesesTrackingExpression constructBinaryExpression(ParenthesesTrackingExpression left,
        ParenthesesTrackingExpression right)
    {
        throw new RuntimeException();
    }
}
