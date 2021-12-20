package expression.exceptions;


import expression.generic.exceptions.IntegerArithmeticException;
import expression.generic.exceptions.IntegerOverflowException;

public class CheckedIntMath {

    static boolean areOpposite(int left, int right) {
        return Integer.min(left, right) == -Integer.max(left, right);
    }

    static int checkedNegate(int input) {
        if (input == Integer.MIN_VALUE) {
            throw new IntegerOverflowException("Can't negate minimal value in two's complement");
        }
        return -input;
    }

    static int checkedAdd(int left, int right) throws IntegerOverflowException {
        int uncheckedResult = left + right;

        if (CheckingUtils.sgn(left) == CheckingUtils.sgn(right)) {
            int bothResultsSign = CheckingUtils.sgn(left);
            if (bothResultsSign != CheckingUtils.sgn(uncheckedResult)) {
                throw new IntegerOverflowException(
                    (bothResultsSign == 1 ? "Overflow" : "Underflow")
                        + " has occurred while adding integers: " + left + " and " + right);
            }
        }

        return uncheckedResult;
    }

    static int checkedSubtract(int left, int right) throws IntegerOverflowException {
        int uncheckedResult = left - right;

        int sgnLeft = CheckingUtils.sgn(left);
        int sgnRight = CheckingUtils.sgn(right);
        int sgnSub = CheckingUtils.sgn(uncheckedResult);
        if (sgnLeft != sgnRight && right != 0) {
            int signMustBe = -sgnRight;
            if (signMustBe != sgnSub) {
                throw new IntegerOverflowException(
                    (sgnSub == 1 ? "Overflow" : "Underflow")
                        + " has occurred while adding integers: " + left + " and " + right);
            }
        }

        return uncheckedResult;
    }

    static int checkedMultiply(int left, int right) throws IntegerOverflowException {
        int proposedResult = left * right;

        if ((left != 0 && proposedResult / left != right)
            || (right == -1 && left == Integer.MIN_VALUE)
            || (right == Integer.MIN_VALUE && left == -1)
        ) {
            throw new IntegerOverflowException(
                "Overflow occurred while multiplying integers: " + right + " and " + left
            );
        }

        return proposedResult;
    }

    static int checkedDivide(int left, int right) throws IntegerOverflowException, IntegerArithmeticException {
        if (right == 0) {
            throw new IntegerArithmeticException("Can't divide by zero");
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new IntegerOverflowException("Overflow occurred when dividing Integer.MIN_VALUE by -1");
        }

        return left / right;
    }
}
