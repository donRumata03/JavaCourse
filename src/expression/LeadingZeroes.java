package expression;

import expression.generic.ParenthesesTrackingExpression;
import expression.generic.UnaryOperation;
import expression.generic.UnaryOperatorTraits;
import java.math.BigDecimal;

public class LeadingZeroes extends UnaryOperation {
    static UnaryOperatorTraits OPERATOR_TRAITS = new UnaryOperatorTraits(
        "l0"
    );

    public LeadingZeroes(ParenthesesTrackingExpression child) {
        super(child, OPERATOR_TRAITS);
    }

    @Override
    public int reductionOperation(int childResult) {
        return Integer.numberOfLeadingZeros(childResult);
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal childResult) {
        throw new RuntimeException("Works only with integers");
    }
}
