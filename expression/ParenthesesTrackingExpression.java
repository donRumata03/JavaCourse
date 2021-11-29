package expression;

public abstract class ParenthesesTrackingExpression implements StringBuildableExpression {
    protected int lowestPriorityAfterBraces = Integer.MAX_VALUE;
    protected boolean needsParentheses = false;

    public ParenthesesTrackingExpression() {}

    public ParenthesesTrackingExpression(int lowestPriorityAfterBraces, boolean needsParentheses) {
        this.lowestPriorityAfterBraces = lowestPriorityAfterBraces;
        this.needsParentheses = needsParentheses;
    }
}
