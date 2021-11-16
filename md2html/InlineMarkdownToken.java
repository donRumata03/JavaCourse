package md2html;

import java.util.Map;
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

    private TokenType type;
    private String text;
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
    public String toString() {
//        switch (this.type) {
//            case Text: return text;
//            case SpecialSymbol: return "";
//        }

//        throw new RuntimeException("");

        return text;
    }
}
