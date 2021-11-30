package expression;

public final class Variable extends ParenthesesTrackingExpression {
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
    void toMiniStringBuilder(StringBuilder builder) {
        this.toStringBuilder(builder);
    }
}
