package expression;

public class Subtract extends TwoArgumentExpression {
    public Subtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    int reductionOperation(int leftResult, int rightResult) {
        return leftResult - rightResult;
    }

}
