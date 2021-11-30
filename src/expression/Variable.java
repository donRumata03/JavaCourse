package expression;

import java.util.Objects;

public final class Variable extends AtomicParenthesesTrackingExpression {
    private final String varName;

    public Variable(String varName) {
        this.varName = varName;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    void toStringBuilder(StringBuilder builder) {
        builder.append(varName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Variable)) {
            return false;
        }
        Variable variable = (Variable) o;
        return varName.equals(variable.varName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(varName);
    }
}
