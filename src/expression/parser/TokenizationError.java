package expression.parser;

public class TokenizationError extends RuntimeException {

    public TokenizationError() {
    }

    public TokenizationError(String message) {
        super(message);
    }
}
