package expression;

/**
 * We want to force implementors to be efficient :)
 */
public interface ProperExpression extends Expression {
    void toStringBuilder(StringBuilder builder);
    void toMiniStringBuilder(StringBuilder builder);
}
