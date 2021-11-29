package expression;

public abstract class TwoArgumentExpression extends ParenthesesTrackingExpression {
    abstract int reductionOperation(int leftResult, int rightResult);
    abstract String operationSymbol();

    ////////////////////////////////////////////////////////////////////////////////////
    private final StringBuildableExpression left;
    private final StringBuildableExpression right;

    private final int priority;


    public TwoArgumentExpression(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right, int priority) {
        this.left = left;
        this.right = right;
        this.priority = priority;

        // Make decision if parentheses are necessary or not
        // (It's easy to prove that greedy algorithm makes sense)
        // — Decisions are made separately for left and right

        this.lowestPriorityAfterBraces = this.priority;

        if (left.lowestPriorityAfterBraces < this.priority) {
            left.needsParentheses = true;
        } else {
            lowestPriorityAfterBraces = Integer.min(lowestPriorityAfterBraces, left.lowestPriorityAfterBraces);
        }

        if (right.lowestPriorityAfterBraces <= this.priority) {
            right.needsParentheses = true;
        } else {
            lowestPriorityAfterBraces = Integer.min(lowestPriorityAfterBraces, right.lowestPriorityAfterBraces);
        }
    }

    @Override
    public int evaluate(int x) {
        return this.reductionOperation(left.evaluate(x), right.evaluate(x));
    }

    /////////////////////////////////////////////////////////

    private void toBasicStringBuilder(StringBuilder builder) {
        left.toStringBuilder(builder);

        builder
            .append(" ")
            .append(operationSymbol())
            .append(" ");

        right.toStringBuilder(builder);
    }

    @Override
    public void toMiniStringBuilder(StringBuilder builder) {
        if (needsParentheses) {
            builder.append("(");
        }

        toBasicStringBuilder(builder);

        if (needsParentheses) {
            builder.append(")");
        }
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append("(");
        toBasicStringBuilder(builder);
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
