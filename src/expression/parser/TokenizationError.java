package expression.parser;

import expression.generic.exceptions.ParseException;

public class TokenizationError extends ParseException {

    public TokenizationError(String message) {
        super(message);
    }
}
