package expression.parser.generic;

import expression.generic.exceptions.ParseException;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BaseTokenParser {

    private final ArithmeticExpressionTokenizer tokenizer;

    public BaseTokenParser(ArithmeticExpressionTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Optional<ArithmeticExpressionToken> tryMatchToken(Predicate<ArithmeticExpressionToken> predicate) {
        if (viewRuntimeErrorizedNextToken().isPresent() && predicate.test(viewRuntimeErrorizedNextToken().get())) {
            return consumeRuntimeErrorizedNextToken();
        }

        return Optional.empty();
    }

//    public Optional<List<ArithmeticExpressionToken>> tryMatchTokenSequence(List<Predicate<ArithmeticExpressionToken>> checkers) {
//        List<ArithmeticExpressionToken> res = new ArrayList<>();
//
//        for (var checker : checkers) {
//            if ()
//        }
//
//        if (viewRuntimeErrorizedNextToken().isPresent() && predicate.test(viewRuntimeErrorizedNextToken().get())) {
//            return consumeRuntimeErrorizedNextToken();
//        }
//
//        return Optional.empty();
//    }

    public void expectNext(Predicate<ArithmeticExpressionToken> predicate) throws ParseException {
        expectNext(predicate, "");
    }

    public void expectNext(Predicate<ArithmeticExpressionToken> predicate, String message) throws ParseException {
        if (viewRuntimeErrorizedNextToken().isEmpty() || !predicate.test(viewRuntimeErrorizedNextToken().get())) {
            throw new ParseException(message);
        }
        consumeRuntimeErrorizedNextToken();
    }


    public Optional<ArithmeticExpressionToken> consumeRuntimeErrorizedNextToken() {
        return runtimeErrorizeIOException(tokenizer::nextToken);
    }

    public Optional<ArithmeticExpressionToken> viewRuntimeErrorizedNextToken() {
        return runtimeErrorizeIOException(tokenizer::viewNextToken);
    }

    public static <T> T runtimeErrorizeIOException(IOExceptionSupplier<T> ioSupplier) {
        try {
            return ioSupplier.get();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred while parsing", e);
        }
    }
}
