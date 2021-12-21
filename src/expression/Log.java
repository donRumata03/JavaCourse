package expression;

import expression.generic.BinaryOperation;
import expression.generic.OperatorTraits;
import java.math.BigDecimal;

public class Log extends BinaryOperation {

    public static final OperatorTraits OPERATOR_INFO = new OperatorTraits(
        4,
        false,
        false,
        "//"
    );

    public Log(Expression left, Expression right) {
        super(left, right, OPERATOR_INFO);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        // TODO
        return 0;
    }

    @Override
    public BigDecimal reductionOperation(BigDecimal leftResult, BigDecimal rightResult) {
        return null;
    }
}
