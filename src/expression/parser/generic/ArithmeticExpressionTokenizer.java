package expression.parser.generic;

import expression.parser.generic.tokens.ArithmeticExpressionToken;
import java.util.Optional;

public class ArithmeticExpressionTokenizer {

    ParsableSource source;

    public ArithmeticExpressionTokenizer(ParsableSource source) {
        this.source = source;
    }

    Optional<ArithmeticExpressionToken> nextToken() {
        return Optional.empty();
    }
}
