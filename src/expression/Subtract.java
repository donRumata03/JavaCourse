package expression;

import expression.generic.OperatorTraits;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.TwoArgumentExpression;
import java.math.BigDecimal;

public final class Subtract extends TwoArgumentExpression {

    public static final OperatorTraits OPERATOR_INFO = new OperatorTraits(
        2,
        false,
        true,
        "-"
    );

    public Subtract(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right, OPERATOR_INFO);
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
