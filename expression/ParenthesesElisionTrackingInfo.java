package expression;

public abstract class ParenthesesElisionTrackingInfo {
    protected boolean parenthesesApplied = false;

    protected int lowestPriorityAfterParentheses = Integer.MAX_VALUE;
    protected boolean containsNonAssociativeLowestPriorityAfterParentheses = false;
}
