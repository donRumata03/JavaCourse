package expression;

/**
 * An expression is responsible for adding parentheses TO ITS CHILDREN if necessary (it knows better, when)
 */
public abstract class ParenthesesTrackingExpression extends StringBuildableExpression {
    protected boolean parenthesesApplied = false;

    abstract ParenthesesElisionTrackingInfo getCachedPriorityInfo();
    abstract ParenthesesElisionTrackingInfo resetCachedPriorityInfo();
}
