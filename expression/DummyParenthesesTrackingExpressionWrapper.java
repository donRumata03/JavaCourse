package expression;

public class DummyParenthesesTrackingExpressionWrapper extends ParenthesesTrackingExpression {

    private final Expression inner;

    DummyParenthesesTrackingExpressionWrapper(Expression wrapped) {
        this.inner = wrapped;
    }


    @Override
    ParenthesesElisionTrackingInfo getCachedPriorityInfo() {
        return new ParenthesesElisionTrackingInfo();
    }

    @Override
    void resetCachedPriorityInfo() {}

    @Override
    void toStringBuilder(StringBuilder builder) {
        builder.append(inner.toString());
    }

    @Override
    void toMiniStringBuilder(StringBuilder builder) {
        builder.append(inner.toMiniString());
    }

    @Override
    public int evaluate(int x) {
        return inner.evaluate(x);
    }
}
