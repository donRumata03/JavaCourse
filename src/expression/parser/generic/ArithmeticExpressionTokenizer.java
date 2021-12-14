package expression.parser.generic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntPredicate;
import expression.parser.TokenizationError;
import expression.parser.generic.tokens.ArithmeticExpressionToken;
import expression.parser.generic.tokens.FunctionToken;
import expression.parser.generic.tokens.NumberToken;
import expression.parser.generic.tokens.OperatorToken;
import expression.parser.generic.tokens.ParenthesesToken;
import expression.parser.generic.tokens.VariableToken;


public class ArithmeticExpressionTokenizer {

    ParsableSource source;
    Optional<ArithmeticExpressionToken> cachedNextToken = Optional.empty();


    public ArithmeticExpressionTokenizer(ParsableSource source) {
        this.source = source;
    }

    public Optional<ArithmeticExpressionToken> viewNextToken() throws IOException {
        if (cachedNextToken.isPresent()) {
            return cachedNextToken;
        }

        // Compute. Cache. Return.
        cachedNextToken = rawNextToken();
        return cachedNextToken;
    }

    public Optional<ArithmeticExpressionToken> nextToken() throws IOException {
        if (cachedNextToken.isPresent()) {
            var result = cachedNextToken.get();
            cachedNextToken = Optional.empty();
            return Optional.of(result);
        }

        return rawNextToken();
    }

    private Optional<ArithmeticExpressionToken> rawNextToken() throws IOException {
        source.consumeWhitespace();
        if (source.isEof()) {
            return Optional.empty();
        }
        // Something is guaranteed to be in input:

        if (nextIsNumber()) {
            return Optional.of(new NumberToken(parseNumber()));
        } else if (nextIsOperator()) {
            return Optional.of(parseOperator());
        } else if (nextIsWord()) {
            String nextWord = parseWord();
            return tryGetFunctionToken(nextWord).or(
                    () -> Optional.of(new VariableToken(nextWord))
                );
        } else if (nextIsParentheses()) {
            return Optional.of(parseParentheses());
        }

        throw new TokenizationError("Next token is not recognized");
    }


    private String consumeSequenceOf(IntPredicate ofWhat) throws IOException {
        StringBuilder res = new StringBuilder();

        while (!source.isEof() && source.testNextChar(ofWhat)) {
            res.append(source.consumeChar());
        }

        return res.toString();
    }

    private boolean nextIsWord() throws IOException {
        return source.testNextChar(Character::isLetter);
    }

    private String parseWord() throws IOException {
        // Sequence of either letters or digits
        return consumeSequenceOf(ch -> Character.isLetter(ch) || Character.isDigit(ch));
    }

    private boolean nextIsParentheses() throws IOException {
        return source.testNextChar(ch -> ch == '(' || ch == ')');
    }

    private Optional<ArithmeticExpressionToken> tryGetFunctionToken(String word) {
        if (word.equals("l0")) {
            return Optional.of(FunctionToken.l0);
        } else if (word.equals("t0")) {
            return Optional.of(FunctionToken.t0);
        }

        return Optional.empty();
    }


    private ParenthesesToken parseParentheses() throws IOException {
        return new ParenthesesToken(
            source.consumeChar() == '('
        );
    }


    private static class OperatorTokenDescriptor {
        String stringRepr;
        OperatorToken token;

        public OperatorTokenDescriptor(String stringRepr, OperatorToken operatorToken) {
            this.stringRepr = stringRepr;
            this.token = operatorToken;
        }
    }
    static List<OperatorTokenDescriptor> operatorDecryption = new ArrayList(List.of(
        new OperatorTokenDescriptor("+", OperatorToken.PLUS),
        new OperatorTokenDescriptor("-", OperatorToken.MINUS),
        new OperatorTokenDescriptor("*", OperatorToken.MULTIPLY),
        new OperatorTokenDescriptor("/", OperatorToken.DIVIDE),
        new OperatorTokenDescriptor("<<", OperatorToken.SHIFT_LEFT),
        new OperatorTokenDescriptor(">>", OperatorToken.LOGICAL_SHIFT_RIGHT),
        new OperatorTokenDescriptor(">>>", OperatorToken.ARITHMETICAL_SHIFT)
    ));

    static Set<Character> operatorStarts;

    static {
        operatorDecryption.sort(Comparator.comparingInt(d -> -d.stringRepr.length()));

        operatorStarts = new HashSet<>();
        for (OperatorTokenDescriptor d : operatorDecryption) {
            operatorStarts.add(d.stringRepr.charAt(0));
        }
    }
//        = Set.of(
//        '+',
//        '-',
//        '*',
//        '/',
//        '<',
//        '>'
//    );

    private boolean nextIsOperator() throws IOException {
        return !nextIsNumber() && source.testNextChar(ch -> operatorStarts.contains((char) ch));
    }


    private OperatorToken parseOperator() throws IOException {
        // Try all in order:
        for (OperatorTokenDescriptor candidateOperator : operatorDecryption) {
            String stringRepr = candidateOperator.stringRepr;
            if (
                source.hasNChars(stringRepr.length())
                && source.viewNChars(stringRepr.length()).equals(stringRepr)
            ) {
                for (int i = 0; i < stringRepr.length(); i++) {
                    source.consumeChar();
                }
                return candidateOperator.token;
            }
        }
        throw new TokenizationError("Incorrect operator");
    }



    private boolean nextIsNumber() throws IOException {
        return source.testNextChar(Character::isDigit)
            || (
            source.testNextCharIs('-')
                && source.hasNChars(2)
                && Character.isDigit(source.viewNChars(2).charAt(1))
        );
    }

    private int parseNumber() throws IOException {
        String sign = source.testNextCharIs('-') ? String.valueOf(source.consumeChar()) : "";

        // After that — just a sequence of digits:
        return Integer.parseInt(sign + consumeSequenceOf(Character::isDigit));
    }
}
