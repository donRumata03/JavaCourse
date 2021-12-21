package expression;

import expression.exceptions.CheckedIntMath;
import expression.generic.BinaryOperation;
import expression.generic.OperatorTraits;
import java.math.BigDecimal;

public class Pow extends BinaryOperation {

    public static final OperatorTraits OPERATOR_INFO = new OperatorTraits(
        4,
        false,
        false,
        "**"
    );

    public Pow(Expression left, Expression right) {
        super(left, right, OPERATOR_INFO);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        return CheckedIntMath.checkedPow(leftResult, rightResult);
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal leftResult, BigDecimal rightResult) {
        return null;
    }
}
