package expression;

public class ParenthesesElisionTrackingInfo {
    protected int lowestPriorityAfterParentheses = Integer.MAX_VALUE;
    protected boolean containsNonAssociativeLowestPriorityAfterParentheses = false;

    ParenthesesElisionTrackingInfo() {}
}
