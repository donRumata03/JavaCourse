package expression;

public final class Const extends ParenthesesTrackingExpression {

    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append(value);
    }

    @Override
    public void toMiniStringBuilder(StringBuilder builder) {
        this.toStringBuilder(builder);
    }
}
