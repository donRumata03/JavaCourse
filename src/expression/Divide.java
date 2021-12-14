package expression;

import expression.generic.OperatorTraits;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.TwoArgumentExpression;
import java.math.BigDecimal;

public class Divide extends TwoArgumentExpression {

    public static final OperatorTraits OPERATOR_INFO = new OperatorTraits(
        3,
        false,
        false,
        "/"
    );

    public Divide(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right, OPERATOR_INFO);
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
