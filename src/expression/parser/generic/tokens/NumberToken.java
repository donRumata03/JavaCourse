package expression.parser.generic.tokens;

public class NumberToken implements ArithmeticExpressionToken {
    int value;

    public NumberToken(int value) {
        this.value = value;
    }
}
