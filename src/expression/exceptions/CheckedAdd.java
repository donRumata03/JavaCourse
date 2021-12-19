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
        int uncheckedResult = leftResult + rightResult;

        if (CheckingUtils.sgn(leftResult) == CheckingUtils.sgn(rightResult)) {
            int bothResultsSign = CheckingUtils.sgn(leftResult);
            if (bothResultsSign != CheckingUtils.sgn(uncheckedResult)) {
                throw new IntegerOverflowException(
                    (bothResultsSign == 1 ? "Overflow" : "Underflow")
                        + " has occurred while adding integers: " + leftResult + " and " + rightResult);
            }
        }

        return super.reductionOperation(leftResult, rightResult);
    }
}
