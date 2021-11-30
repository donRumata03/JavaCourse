package expression;

public class Subtract extends TwoArgumentExpression {
    public Subtract(ParenthesesTrackingExpression left, ParenthesesTrackingExpression right) {
        super(left, right, 1);
    }

    @Override
    int reductionOperation(int leftResult, int rightResult) {
        return leftResult - rightResult;
    }

    @Override
    String operationSymbol() {
        return "-";
    }

}
