package expression.generic;

import java.math.BigDecimal;

public abstract class UnaryOperation extends AtomicParenthesesTrackingExpression {

    ParenthesesTrackingExpression child;
    UnaryOperatorTraits operatorInfo;

    public UnaryOperation(ParenthesesTrackingExpression child, UnaryOperatorTraits operatorInfo) {
        this.child = child;
        this.operatorInfo = operatorInfo;
    }

    public abstract int reductionOperation(int childResult);
    public abstract BigDecimal reductionOperation(BigDecimal childResult);

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append(operatorInfo.operatorSymbol()).append("(");
        child.toStringBuilder(builder);
        builder.append(")");
    }

    @Override
    public void toMiniStringBuilderCorrect(StringBuilder builder) {
        builder
            .append(operatorInfo.operatorSymbol())
            .append(" ");

        child.toMiniStringBuilder(builder);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return reductionOperation(child.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return reductionOperation(child.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return reductionOperation(child.evaluate(x, y, z));
    }
}
