package expression;

public class ParenthesesElisionTrackingInfo {
    protected int lowestPriorityAfterParentheses = Integer.MAX_VALUE;
    protected boolean containsNonAssociativeLowestPriorityAfterParentheses = false;

    ParenthesesElisionTrackingInfo() {}

    void includeInParenthesesLessGroup(ParenthesesElisionTrackingInfo other) {
        this.lowestPriorityAfterParentheses = Integer.min(
            this.lowestPriorityAfterParentheses, other.lowestPriorityAfterParentheses
        );

        this.containsNonAssociativeLowestPriorityAfterParentheses |= other.containsNonAssociativeLowestPriorityAfterParentheses;
    }
}
