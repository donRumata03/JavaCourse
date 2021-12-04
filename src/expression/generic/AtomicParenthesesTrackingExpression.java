package expression.generic;

public abstract class AtomicParenthesesTrackingExpression extends ParenthesesTrackingExpression {
    @Override
    public ParenthesesElisionTrackingInfo getCachedPriorityInfo() {
        return ParenthesesElisionTrackingInfo.generateAtomicExpressionInfo();
    }

    @Override
    public void resetCachedPriorityInfo() {}

    @Override
    public void toMiniStringBuilder(StringBuilder builder) {
        toStringBuilder(builder);
    }
}
