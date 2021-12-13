package expression.parser.generic;

import bufferedScanning.ScanningUtils;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import java.io.IOException;
import java.util.Optional;

public class ArithmeticExpressionTokenizer {

    ParsableSource source;

    public ArithmeticExpressionTokenizer(ParsableSource source) {
        this.source = source;
    }

    Optional<ArithmeticExpressionToken> nextToken() throws IOException {
        source.consumeWhitespace();
        if (source.isEof()) {
            return Optional.empty();
        }
        // Something is guaranteed to be in input:


        return Optional.empty();
    }


    boolean nextIsNumber() throws IOException {
        return source.testNextChar(Character::isDigit)
            || (
                source.testNextCharIs('-')
                    && source.hasNChars(2)
                    && Character.isDigit(source.viewNChars(2).charAt(1))
        );
    }

    boolean nextIsWord() throws IOException {
        return source.testNextChar(Character::isLetter);
    }

    boolean nextIsParentheses() throws IOException {
        return source.testNextChar(ch -> ch == '(' || ch == ')');
    }
}
