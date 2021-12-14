package expression;

import expression.generic.ParenthesesTrackingExpression;
import expression.generic.UnaryOperation;
import expression.generic.UnaryOperatorTraits;
import java.math.BigDecimal;


public class TrailingZeroes extends UnaryOperation {
    static UnaryOperatorTraits OPERATOR_TRAITS = new UnaryOperatorTraits(
        "t0"
    );

    public TrailingZeroes(ParenthesesTrackingExpression child) {
        super(child, OPERATOR_TRAITS);
    }

    @Override
    public int reductionOperation(int childResult) {
        return Integer.numberOfTrailingZeros(childResult);
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal childResult) {
        throw new RuntimeException("Works only with integers");
    }
}

