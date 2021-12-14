package expression;

import expression.generic.ParenthesesTrackingExpression;
import expression.generic.UnaryOperation;
import expression.generic.UnaryOperatorTraits;
import java.math.BigDecimal;

public class Negate extends UnaryOperation {
    static UnaryOperatorTraits OPERATOR_TRAITS = new UnaryOperatorTraits(
        "-"
    );

    public Negate(ParenthesesTrackingExpression child) {
        super(child, OPERATOR_TRAITS);
    }

    @Override
    public int reductionOperation(int childResult) {
        return -childResult;
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal childResult) {
        return childResult.negate();
    }
}
