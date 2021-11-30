package expression;

import java.util.Optional;

public abstract class TwoArgumentExpression extends ParenthesesTrackingExpression {
    abstract int reductionOperation(int leftResult, int rightResult);
    abstract String operationSymbol();

    ////////////////////////////////////////////////////////////////////////////////////
    private final ParenthesesTrackingExpression left;
    private final ParenthesesTrackingExpression right;

    private final OperatorTraits operatorInfo;
    private Optional<ParenthesesElisionTrackingInfo> cachedPriorityInfo = Optional.empty();


    public TwoArgumentExpression(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right, OperatorTraits operatorInfo) {
        this.left = left;
        this.right = right;
        this.operatorInfo = operatorInfo;
    }

    @Override
    public int evaluate(int x) {
        return this.reductionOperation(left.evaluate(x), right.evaluate(x));
    }

    /////////////////////////////////////////////////////////



    @Override
    void resetCachedPriorityInfo() {
        cachedPriorityInfo = Optional.empty();
    }

    @Override
    ParenthesesElisionTrackingInfo getCachedPriorityInfo() {
        if (cachedPriorityInfo.isPresent()) {
            return cachedPriorityInfo.get();
        }

        ParenthesesElisionTrackingInfo leftInfo = left.getCachedPriorityInfo();
        ParenthesesElisionTrackingInfo rightInfo = right.getCachedPriorityInfo();

        // Make decision if parentheses are necessary or not
        // (It's easy to prove that greedy algorithm makes sense)
        // — Decisions are made separately for left and right
        // — If priority of smth is higher => don't have PS
        // — If priority of smth is lower => have PS
        // — If priority of smth is the same, for left don't have PS, but for right it becomes more interesting…
        // — So, for right with same priorities it is removed if: ……………

        cachedPriorityInfo = Optional.of(new ParenthesesElisionTrackingInfo());
        ParenthesesElisionTrackingInfo cachingInfo = cachedPriorityInfo.get();

        cachingInfo.lowestPriorityAfterParentheses = this.operatorInfo.priority;

        if (left. lowestPriorityAfterParentheses < this.operatorInfo.priority) {
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
