package expression;

public abstract class TwoArgumentExpression extends ParenthesesTrackingExpression {
    abstract int reductionOperation(int leftResult, int rightResult);
    abstract String operationSymbol();

    ////////////////////////////////////////////////////////////////////////////////////
    private final StringBuildableExpression left;
    private final StringBuildableExpression right;

    private final OperatorTraits operatorInfo;


    public TwoArgumentExpression(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right, OperatorTraits operatorInfo) {
        this.left = left;
        this.right = right;
        this.operatorInfo = operatorInfo;

        // Make decision if parentheses are necessary or not
        // (It's easy to prove that greedy algorithm makes sense)
        // — Decisions are made separately for left and right
        // — If priority of smth is higher => don't have PS
        // — If priority of smth is lower => have PS
        // — If priority of smth is the same, for left don't have PS, but for right it becomes more interesting…
        // — So, for right with same priorities it is removed if: ……………

        this.lowestPriorityAfterParentheses = this.priority;

        if (left.lowestPriorityAfterParentheses < this.priority) {
            left.needsParentheses = true;
        } else {
            lowestPriorityAfterParentheses = Integer.min(
                lowestPriorityAfterParentheses, left.lowestPriorityAfterParentheses);
        }

        if (right.lowestPriorityAfterParentheses <= this.priority) {
            right.needsParentheses = true;
        } else {
            lowestPriorityAfterParentheses = Integer.min(
                lowestPriorityAfterParentheses, right.lowestPriorityAfterParentheses);
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
}
