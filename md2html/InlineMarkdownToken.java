package md2html;

import java.util.Map;

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

    private TokenType type;
    private String text;
    // SpecialSymbolType specialSymbolType;

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
