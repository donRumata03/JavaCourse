package expression;

import expression.generic.AtomicParenthesesTrackingExpression;
import java.util.Objects;

public final class Const extends AtomicParenthesesTrackingExpression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Const)) {
            return false;
        }
        Const aConst = (Const) o;
        return value == aConst.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
