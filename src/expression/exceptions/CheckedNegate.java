package expression.exceptions;

import expression.Negate;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.UnaryOperation;
import expression.generic.UnaryOperatorTraits;
import java.math.BigDecimal;

public class CheckedNegate extends Negate {

    public CheckedNegate(ParenthesesTrackingExpression child) {
        super(child);
    }

    @Override
    public int reductionOperation(int childResult) {
        if (childResult == Integer.MIN_VALUE) {
            throw new IntegerArithmeticException("Can't negate minimal value in two's complement");
        }
        return -childResult;
    }
}
