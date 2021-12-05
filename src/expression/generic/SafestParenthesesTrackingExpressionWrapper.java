package expression.generic;

import expression.Expression;
import expression.ToMiniString;
import expression.TripleExpression;

public class SafestParenthesesTrackingExpressionWrapper extends ParenthesesTrackingExpression {

    private final ToMiniString inner;

    SafestParenthesesTrackingExpressionWrapper(Expression wrapped) {
        this.inner = wrapped;
    }
    SafestParenthesesTrackingExpressionWrapper(TripleExpression wrapped) {
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
        if (!(inner instanceof Expression expression)) {
            throw new AssertionError("Single-argument expression is only supported by ");
        }
        return expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (!(inner instanceof TripleExpression tripleExpression)) {
            throw new AssertionError("Single-argument expression is only supported by ");
        }
        return tripleExpression.evaluate(x, y, z);
    }
}