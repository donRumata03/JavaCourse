package expression.parser;

public class ArithmeticExpressionSyntaxError extends RuntimeException {

    public ArithmeticExpressionSyntaxError() {
    }

    public ArithmeticExpressionSyntaxError(String message) {
        super(message);
    }
}
