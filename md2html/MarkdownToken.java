package md2html;

import java.util.Map;

public class MarkdownToken {
    public enum TokenType {
        Text,
        SpecialSymbol;
    }

    public enum SpecialSymbolType {
        Emphasis,
        Strikeout,
        Strong
    }

    private static Map<SpecialSymbolType, >

    private TokenType type;
    String text;
    SpecialSymbolType specialSymbolType;

    @Override
    public String toString() {
        switch (this.type) {
            case Text: return text;
            case SpecialSymbol: return "";
        }

        throw new RuntimeException("");
    }
}
