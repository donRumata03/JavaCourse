package expression;

import expression.exceptions.CheckedIntMath;
import expression.generic.BinaryOperation;
import expression.generic.OperatorTraits;
import expression.generic.ParenthesesTrackingExpression;
import expression.generic.UnaryOperation;
import expression.generic.UnaryOperatorTraits;
import expression.generic.exceptions.IntegerArithmeticException;
import java.math.BigDecimal;

public class Abs extends UnaryOperation {

    public static final UnaryOperatorTraits OPERATOR_INFO = new UnaryOperatorTraits("abs");

    public Abs(ParenthesesTrackingExpression child) {
        super(child, OPERATOR_INFO);
    }

    @Override
    public int reductionOperation(int childResult) {
        return CheckedIntMath.checkedAbs(childResult);
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal childResult) {
        return null;
    }
}
