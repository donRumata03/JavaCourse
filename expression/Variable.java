package expression;

public class Variable implements Expression {
    private final String varName;

    public Variable(String varName) {
        this.varName = varName;
    }

    @Override
    public int evaluate(int x) {
        return 0;
    }

    @Override
    public String toString() {
        return varName;
    }
}
