package expression.parser.generic.tokens;

public class ParenthesesToken implements ArithmeticExpressionToken {
    public boolean openCloseness;

    public ParenthesesToken(boolean openCloseness) {
        this.openCloseness = openCloseness;
    }
}
