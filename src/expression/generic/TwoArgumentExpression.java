package expression.generic;

import expression.Expression;
import expression.TripleExpression;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class TwoArgumentExpression extends ParenthesesTrackingExpression {
    abstract public int reductionOperation(int leftResult, int rightResult);

    ////////////////////////////////////////////////////////////////////////////////////
    private final ParenthesesTrackingExpression left;
    private final ParenthesesTrackingExpression right;

    private final OperatorTraits operatorInfo;
    private Optional<ParenthesesElisionTrackingInfo> cachedPriorityInfo = Optional.empty();

    public TwoArgumentExpression(Expression left, Expression right, OperatorTraits operatorInfo) {
        this(new SafestParenthesesTrackingExpressionWrapper(left), new SafestParenthesesTrackingExpressionWrapper(right), operatorInfo);
    }


    public TwoArgumentExpression(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right, OperatorTraits operatorInfo) {
        this.left = left;
        this.right = right;
        this.operatorInfo = operatorInfo;
    }

    @Override
    public int evaluate(int x) {
        return this.reductionOperation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.reductionOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    /////////////////////////////////////////////////////////



    @Override
    public void resetCachedPriorityInfo() {
        cachedPriorityInfo = Optional.empty();
    }


    /**
     *  Make decision if parentheses are necessary for children or not
     *  (It's easy to prove that greedy algorithm makes sense)
     *  — Decisions are made separately for left and right
     *  — If priority of smth is higher => don't have PS
     *  — If priority of smth is lower => have PS
     *  — If priority of smth is the same, for left don't have PS, but for right it becomes more interesting…
     *  — So, for right with same priorities it is removed if: ……………
     */
    @Override
    public ParenthesesElisionTrackingInfo getCachedPriorityInfo() {
        if (cachedPriorityInfo.isPresent()) {
            return cachedPriorityInfo.get();
        }

        ParenthesesElisionTrackingInfo leftInfo = left.getCachedPriorityInfo();
        ParenthesesElisionTrackingInfo rightInfo = right.getCachedPriorityInfo();

        cachedPriorityInfo = Optional.of(ParenthesesElisionTrackingInfo.neutralElement());
        ParenthesesElisionTrackingInfo cachingInfo = cachedPriorityInfo.get();


        cachingInfo.lowestPriorityAfterParentheses = this.operatorInfo.priority();
        cachingInfo.containsNonAssociativeLowestPriorityAfterParentheses = !this.operatorInfo.associativityAmongPriorityClass();

        // When to ADD brackets:
        if (this.operatorInfo.priority() > leftInfo.lowestPriorityAfterParentheses) {
            leftInfo.performParenthesesApplicationDecision(true);
        } else {
            cachingInfo.includeInParenthesesLessGroup(leftInfo);
        }

        // When to ADD brackets:
        if (this.operatorInfo.priority() > rightInfo.lowestPriorityAfterParentheses
            || (
                rightInfo.lowestPriorityAfterParentheses == this.operatorInfo.priority()
                &&
                    (
                        !this.operatorInfo.commutativityAmongPriorityClass()
                            || rightInfo.containsNonAssociativeLowestPriorityAfterParentheses
                    )
            )
        ) {
            rightInfo.performParenthesesApplicationDecision(true);
        } else {
            cachingInfo.includeInParenthesesLessGroup(rightInfo);
        }

        return cachingInfo;
    }

    /////////////////////////////////////////////////////////

    private void toBaseStringBuilder(StringBuilder builder, boolean addParentheses,
        BiConsumer<ParenthesesTrackingExpression, StringBuilder> buildCaller)
    {
        if (addParentheses) {
            builder.append("(");
        }
        buildCaller.accept(left, builder);

        builder
            .append(" ")
            .append(operatorInfo.operatorSymbol())
            .append(" ");

        buildCaller.accept(right, builder);
        if (addParentheses) {
            builder.append(")");
        }
    }

    @Override
    public void toMiniStringBuilder(StringBuilder builder) {
        ParenthesesElisionTrackingInfo thisInfo = getCachedPriorityInfo();

        toBaseStringBuilder(builder, thisInfo.parenthesesApplied, ParenthesesTrackingExpression::toMiniStringBuilder);

        resetCachedPriorityInfo();
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        toBaseStringBuilder(builder, true, ParenthesesTrackingExpression::toStringBuilder);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof TwoArgumentExpression)) {
            return false;
        }

        TwoArgumentExpression that = (TwoArgumentExpression) other;

        return other.getClass() == this.getClass() &&
            operatorInfo.equals(that.operatorInfo) &&
            left.equals(that.left) &&
            right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, operatorInfo);
    }
}
