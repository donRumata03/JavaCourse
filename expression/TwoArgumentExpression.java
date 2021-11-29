package expression;

public abstract class TwoArgumentExpression implements Expression {
    abstract int reductionOperation(int leftResult, int rightResult);

    ////////////////////////////////////////////////////////////////////////////////////
    private final Expression left;
    private final Expression right;

    private final int priority;

    private final int lowestPriorityAfterBraces;

    public TwoArgumentExpression(Expression left, Expression right, int priority) {
        this.left = left;
        this.right = right;
        this.priority = priority;

        // Make decision if parentheses are necessary or not
        // It's easy to prove that greedy algorithm makes sense

    }

    @Override
    public int evaluate(int x) {
        return this.reductionOperation(left.evaluate(x), right.evaluate(x));
    }
}
