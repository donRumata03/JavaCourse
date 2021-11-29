package expression;

public class Divide extends TwoArgumentExpression {

    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    int reductionOperation(int leftResult, int rightResult) {
        return leftResult / rightResult;
    }
}
