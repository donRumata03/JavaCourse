package expression.generic;

import expression.Expression;

public class SafestParenthesesTrackingExpressionWrapper extends ParenthesesTrackingExpression {

    private final Expression inner;

    SafestParenthesesTrackingExpressionWrapper(Expression wrapped) {
        this.inner = wrapped;
    }


    @Override
    public ParenthesesElisionTrackingInfo getCachedPriorityInfo() {
        return ParenthesesElisionTrackingInfo.generateSafestExpressionInfo();
    }

    @Override
    public void resetCachedPriorityInfo() {}

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append(inner.toString());
    }

    @Override
    public void toMiniStringBuilder(StringBuilder builder) {
        builder.append(inner.toMiniString());
    }

    @Override
    public int evaluate(int x) {
        return inner.evaluate(x);
    }
}
