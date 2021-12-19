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
        int proposedResult = leftResult * rightResult;

        if ((proposedResult / leftResult != rightResult)
            || (rightResult == -1 && leftResult == Integer.MIN_VALUE)
            || (rightResult == Integer.MIN_VALUE && leftResult == -1)
        ) {
            throw new IntegerOverflowException(
                "Overflow occurred while multiplying integers: " + rightResult + " and " + leftResult
            );
        }

        return super.reductionOperation(leftResult, rightResult);
    }
}
