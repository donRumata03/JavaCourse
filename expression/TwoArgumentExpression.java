package expression;

public abstract class TwoArgumentExpression implements ProperExpression {
    abstract int reductionOperation(int leftResult, int rightResult);
    abstract String operationSymbol();

    ////////////////////////////////////////////////////////////////////////////////////
    private final ProperExpression left;
    private final ProperExpression right;

    private final int priority;

    private final int lowestPriorityAfterBraces;

    public TwoArgumentExpression(ProperExpression left, ProperExpression right, int priority) {
        this.left = left;
        this.right = right;
        this.priority = priority;

        // Make decision if parentheses are necessary or not
        // (It's easy to prove that greedy algorithm makes sense)
        // — Decisions are made separately
    }

    @Override
    public int evaluate(int x) {
        return this.reductionOperation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public void toMiniStringBuilder(StringBuilder builder) {
        // TODO!
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder
            .append("(");

        left.toStringBuilder(builder);

        builder
            .append(" ")
            .append(operationSymbol())
            .append(" ");

        right.toStringBuilder(builder);

        builder.append(")");
    }

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
