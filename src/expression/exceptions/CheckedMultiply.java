package expression.exceptions;

import expression.Multiply;
import expression.generic.ParenthesesTrackingExpression;

public class CheckedMultiply extends Multiply {

    public CheckedMultiply(ParenthesesTrackingExpression left,
        ParenthesesTrackingExpression right) {
        super(left, right);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        // TODO
        return super.reductionOperation(leftResult, rightResult);
    }
}
