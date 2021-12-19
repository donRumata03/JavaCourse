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
        if (rightResult == 0) {
            throw new IntegerArithmeticException("Can't divide by zero");
        }
        if (leftResult == Integer.MIN_VALUE && rightResult == -1) {
            throw new IntegerOverflowException("Overflow occurred when dividing Integer.MIN_VALUE by -1");
        }

        return super.reductionOperation(leftResult, rightResult);
    }
}
