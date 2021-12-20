package expression.exceptions;

import expression.Negate;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.exceptions.IntegerArithmeticException;
import expression.generic.exceptions.IntegerOverflowException;

public class CheckedNegate extends Negate {

    public CheckedNegate(ParenthesesTrackingExpression child) {
        super(child);
    }

    @Override
    public int reductionOperation(int childResult) {
        return CheckedIntMath.checkedNegate(childResult);
    }
}
