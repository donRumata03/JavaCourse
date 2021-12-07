package expression;

import expression.generic.OperatorTraits;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.TwoArgumentExpression;
import java.math.BigDecimal;

public final class Subtract extends TwoArgumentExpression {
    public Subtract(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right, new OperatorTraits(
            1,
            false,
            true,
            "-"
        ));
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return leftResult - rightResult;
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal leftResult, BigDecimal rightResult) {
        return leftResult.subtract(rightResult);
    }
}
