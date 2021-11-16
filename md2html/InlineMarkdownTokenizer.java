package md2html;

import java.util.Optional;
import md2html.tokens.InlineMarkdownToken;
import md2html.tokens.InlineMarkdownToken.TokenType;


// #[derive(close)]
public class InlineMarkdownTokenizer {
    String source;
    int sourcePtr = 0;

    private char consumeNext() {
        return source.charAt(sourcePtr++);
    }

    private char viewNext() {
        return source.charAt(sourcePtr);
    }

    private boolean hasNext() {
        return sourcePtr < source.length();
    }

    private boolean nextIsSpecialSymbol() {
        return hasNext() && InlineMarkdownToken.isImdSpecialSymbol(viewNext());
    }

    private boolean nextIsDoubleSpecialSymbol() {
        return
            (sourcePtr + 1) < source.length() &&
                InlineMarkdownToken.isImdDoubleableSpecialSymbol(viewNext()) &&
                source.charAt(sourcePtr + 1) == viewNext();
    }

    private boolean nextIsEscaping() {
        return viewNext() == '\\';
    }


    public InlineMarkdownTokenizer(String source) {
        this.source = source;
    }

    public Optional<InlineMarkdownToken> nextToken() {
        if (!hasNext()) {
            return Optional.empty();
        }

        if (nextIsEscaping()) {
            consumeNext();
            return Optional.of(new InlineMarkdownToken(TokenType.Text, String.valueOf(consumeNext())));
        }
        else if (nextIsDoubleSpecialSymbol()) {
            return Optional.of(
                new InlineMarkdownToken(TokenType.SpecialSymbol, String.valueOf(consumeNext()) + consumeNext())
            );
        } else if (nextIsSpecialSymbol()) {
            return Optional.of(new InlineMarkdownToken(TokenType.SpecialSymbol, String.valueOf(consumeNext())));
        } else {
            // Scan while end or special symbol found:
            StringBuilder result = new StringBuilder();
            while (hasNext() && !(nextIsEscaping() || nextIsSpecialSymbol() || nextIsDoubleSpecialSymbol())) {
                result.append(consumeNext());
            }
            return Optional.of(new InlineMarkdownToken(TokenType.Text, result.toString()));
        }
    }
}
