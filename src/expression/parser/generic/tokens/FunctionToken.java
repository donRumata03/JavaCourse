package expression.parser.generic.tokens;


public record FunctionToken
    (FunctionType type)
{
    enum FunctionType {
        l0,
        t0
    }
}
