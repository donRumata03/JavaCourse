package expression;

/**
 * We want to force implementors to be efficient :)
 */
public interface StringBuildableExpression extends Expression {
    void toStringBuilder(StringBuilder builder);
    void toMiniStringBuilder(StringBuilder builder);
}
