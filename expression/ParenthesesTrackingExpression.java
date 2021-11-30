package expression;

public abstract class ParenthesesTrackingExpression extends StringBuildableExpression {
    protected int lowestPriorityAfterBraces = Integer.MAX_VALUE;
    protected boolean needsParentheses = false;
    protected boolean isAssociativeAmongPriorityClass = false;

    public ParenthesesTrackingExpression() {}
}
