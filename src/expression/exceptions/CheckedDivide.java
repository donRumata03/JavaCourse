package expression.exceptions;

import expression.Divide;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.exceptions.IntegerArithmeticException;
import expression.generic.exceptions.IntegerOverflowException;

public class CheckedDivide extends Divide {

    public CheckedDivide(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return CheckedIntMath.checkedDivide(leftResult, rightResult);
    }
}
