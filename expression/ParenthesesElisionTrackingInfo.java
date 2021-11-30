package expression;

public class ParenthesesElisionTrackingInfo {
    protected boolean parenthesesApplied = false;

    protected int lowestPriorityAfterParentheses = Integer.MAX_VALUE;
    protected boolean containsNonAssociativeLowestPriorityAfterParentheses = false;

    ParenthesesElisionTrackingInfo() {}

    void includeInParenthesesLessGroup(ParenthesesElisionTrackingInfo other) {
        this.lowestPriorityAfterParentheses = Integer.min(
            this.lowestPriorityAfterParentheses, other.lowestPriorityAfterParentheses
        );

        this.containsNonAssociativeLowestPriorityAfterParentheses |= other.containsNonAssociativeLowestPriorityAfterParentheses;
    }

    void performParenthesesApplicationDecision(boolean toApplyOrNotToApply) {
        parenthesesApplied = toApplyOrNotToApply;
    }
}
