package expression.generic;

public class ParenthesesElisionTrackingInfo {
    /* package-private */ public boolean parenthesesApplied;

    /* package-private */ public int lowestPriorityAfterParentheses;
    /* package-private */ boolean containsNonAssociativeLowestPriorityAfterParentheses;

    public static ParenthesesElisionTrackingInfo generateAtomicExpressionInfo() {
        return new ParenthesesElisionTrackingInfo(
            false,
            Integer.MAX_VALUE,
            false
        );
    }

    public static ParenthesesElisionTrackingInfo generateSafestExpressionInfo() {
        return new ParenthesesElisionTrackingInfo(
            false,
            Integer.MIN_VALUE,
            true
        );
    }

    public static ParenthesesElisionTrackingInfo neutralElement() {
        return new ParenthesesElisionTrackingInfo(
            false,
            Integer.MAX_VALUE,
            false
        );
    }

    void includeInParenthesesLessGroup(ParenthesesElisionTrackingInfo other) {
        // this.lowestPriorityAfterParentheses <= priority of current operation

        // Should update
        // — «lowestPriorityAfterParentheses»
        // — «containsNonAssociativeLowestPriorityAfterParentheses»
        if (this.lowestPriorityAfterParentheses > other.lowestPriorityAfterParentheses) {
            // Accept theirs:
            this.lowestPriorityAfterParentheses = other.lowestPriorityAfterParentheses;
            this.containsNonAssociativeLowestPriorityAfterParentheses =
                other.containsNonAssociativeLowestPriorityAfterParentheses;

        } else if (this.lowestPriorityAfterParentheses == other.lowestPriorityAfterParentheses) {
            // Combine having associative:
            this.containsNonAssociativeLowestPriorityAfterParentheses |=
                other.containsNonAssociativeLowestPriorityAfterParentheses;

        } else /* thisPriority < other.lowestPriorityAfterParentheses */ {
            // pass (accept «this» data)
        }
    }

    public ParenthesesElisionTrackingInfo(boolean parenthesesApplied, int lowestPriorityAfterParentheses,
        boolean containsNonAssociativeLowestPriorityAfterParentheses)
    {
        this.parenthesesApplied = parenthesesApplied;
        this.lowestPriorityAfterParentheses = lowestPriorityAfterParentheses;
        this.containsNonAssociativeLowestPriorityAfterParentheses = containsNonAssociativeLowestPriorityAfterParentheses;
    }

    void performParenthesesApplicationDecision(boolean toApplyOrNotToApply) {
        parenthesesApplied = toApplyOrNotToApply;
    }
}
