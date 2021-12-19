package expression.exceptions;

import expression.Add;
import expression.generic.ParenthesesTrackingExpression;

public class CheckedAdd extends Add {

    public CheckedAdd(ParenthesesTrackingExpression left,
        ParenthesesTrackingExpression right) {
        super(left, right);
    }

    @Override
    public int reductionOperation(int leftResult, int rightResult) {
        // TODO
        return super.reductionOperation(leftResult, rightResult);
    }
}
