package expression;

public class Multiply extends TwoArgumentExpression  {
    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    int reductionOperation(int leftResult, int rightResult) {
        return leftResult * rightResult;
    }
}
