package expression.exceptions;

import expression.Add;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.exceptions.IntegerOverflowException;

public class CheckedAdd extends Add {

    public CheckedAdd(ParenthesesTrackingExpression left,
        ParenthesesTrackingExpression right) {
        super(left, right);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return CheckedIntMath.checkedAdd(leftResult, rightResult);
    }
}
