package expression;

import expression.generic.OperatorTraits;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.TwoArgumentExpression;

public class Add extends TwoArgumentExpression {
    public Add(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right, new OperatorTraits(
            1,
            true,
            true,
            "+"
        ));
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return leftResult + rightResult;
    }
}
