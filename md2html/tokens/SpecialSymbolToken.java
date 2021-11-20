package md2html.tokens;

import markup.DelimiterDictionary;

public class SpecialSymbolToken extends InlineMarkdownToken {
    public static boolean isImdSpecialSymbol(char c) {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.containsKey(String.valueOf(c));
    }

    public static boolean isImdDoubleableSpecialSymbol(char c) {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.containsKey(String.valueOf(c).repeat(2));
    }


    public SpecialSymbolToken(String text) {
        super(text);
        assert checkCorrectness();
    }

    public boolean checkCorrectness() {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.containsKey(text);
    }

    public DelimiterDictionary.OpenCloseness getOpenCloseness() {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.get(text).openCloseness;
    }
}
