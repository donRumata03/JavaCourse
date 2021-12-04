package expression;

import expression.generic.OperatorTraits;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.TwoArgumentExpression;

public class Multiply extends TwoArgumentExpression {
    public Multiply(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right, new OperatorTraits(
            2,
            true,
            true,
            "*"
        ));
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return leftResult * rightResult;
    }
}
