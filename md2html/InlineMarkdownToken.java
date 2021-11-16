package md2html;

import java.util.Map;
import java.util.Objects;
import markup.DelimiterDictionary;

public class InlineMarkdownToken {
    public enum TokenType {
        Text,
        SpecialSymbol;
    }

    public enum SpecialSymbolType {
        Emphasis,
        Strikeout,
        Strong,
        Code
    }

    // private static Map<SpecialSymbolType, String>

    static boolean isImdSpecialSymbol(char c) {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.containsKey(String.valueOf(c));
    }

    static boolean isImdDoubleableSpecialSymbol(char c) {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.containsKey(String.valueOf(c).repeat(2));
    }

    private final TokenType type;
    private final String text;
    // SpecialSymbolType specialSymbolType;

    public InlineMarkdownToken(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InlineMarkdownToken that = (InlineMarkdownToken) o;
        return type == that.type &&
            text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, text);
    }

    @Override
    public String toString() {
//        switch (this.type) {
//            case Text: return text;
//            case SpecialSymbol: return "";
//        }

//        throw new RuntimeException("");

        return text;
    }
}
