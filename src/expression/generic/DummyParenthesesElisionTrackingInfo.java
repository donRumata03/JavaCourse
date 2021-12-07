package expression.generic;

public class DummyParenthesesElisionTrackingInfo {
    public boolean parenthesesApplied;
    public int lowestPriorityAfterParentheses;
    public boolean isAssociativeAmongPriorityClass;


    public DummyParenthesesElisionTrackingInfo(boolean parenthesesApplied, int priority, boolean isAssociativeAmongPriorityClass) {
        this.parenthesesApplied = parenthesesApplied;
        this.lowestPriorityAfterParentheses = priority;
        this.isAssociativeAmongPriorityClass = isAssociativeAmongPriorityClass;
    }

    public DummyParenthesesElisionTrackingInfo(OperatorTraits fromOperator) {
        this(false, fromOperator.priority(), fromOperator.associativityAmongPriorityClass());
    }

    void includeInParenthesesLessGroup(DummyParenthesesElisionTrackingInfo other) {
        this.lowestPriorityAfterParentheses = Integer.min(
            this.lowestPriorityAfterParentheses,
            other.lowestPriorityAfterParentheses
        );
    }


    static DummyParenthesesElisionTrackingInfo neutralElement() {
        return generateAtomicExpressionInfo();
    }

    public static DummyParenthesesElisionTrackingInfo generateAtomicExpressionInfo() {
        return new DummyParenthesesElisionTrackingInfo(
            false,
            Integer.MAX_VALUE,
            true
        );
    }

    public static DummyParenthesesElisionTrackingInfo generateSafestExpressionInfo() {
        return new DummyParenthesesElisionTrackingInfo(
            false,
            Integer.MIN_VALUE,
            false
        );
    }

}
