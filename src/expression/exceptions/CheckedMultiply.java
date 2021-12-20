package expression.exceptions;

import expression.Multiply;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.exceptions.IntegerOverflowException;

public class CheckedMultiply extends Multiply {

    public CheckedMultiply(ParenthesesTrackingExpression left,
        ParenthesesTrackingExpression right) {
        super(left, right);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return CheckedIntMath.checkedMultiply(leftResult, rightResult);
    }
}
