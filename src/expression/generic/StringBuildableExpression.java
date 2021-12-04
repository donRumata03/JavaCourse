package expression.generic;

import expression.Expression;

/**
 * We want to force implementors to be efficient :)
 */
public abstract class StringBuildableExpression implements Expression {
    abstract public void toStringBuilder(StringBuilder builder);
    abstract public void toMiniStringBuilder(StringBuilder builder);

    @Override
    public String toMiniString() {
        StringBuilder builder = new StringBuilder();
        this.toMiniStringBuilder(builder);
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.toStringBuilder(builder);
        return builder.toString();
    }

}
