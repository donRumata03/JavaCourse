package expression.parser.generic.tokens;

public class VariableToken implements ArithmeticExpressionToken {
    String varName;

    public VariableToken(String varName) {
        this.varName = varName;
    }
}
