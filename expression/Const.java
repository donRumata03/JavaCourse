package expression;

public class Const implements Expression {

    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toMiniString() {
        return null;
    }
}
