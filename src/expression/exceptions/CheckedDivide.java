package expression.exceptions;

import expression.Divide;
import expression.generic.ParenthesesTrackingExpression;

public class CheckedDivide extends Divide {

    public CheckedDivide(ParenthesesTrackingExpression left,
        ParenthesesTrackingExpression right) {
        super(left, right);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        // TODO
        return super.reductionOperation(leftResult, rightResult);
    }
}
