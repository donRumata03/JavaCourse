package expression;

import expression.generic.OperatorTraits;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.TwoArgumentExpression;
import java.math.BigDecimal;

public class Divide extends TwoArgumentExpression {
    public Divide(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right, new OperatorTraits(
            2,
            false,
            false,
            "/"
        ));
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return leftResult / rightResult;
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal leftResult, BigDecimal rightResult) {
        return leftResult.divide(rightResult);
    }
}
