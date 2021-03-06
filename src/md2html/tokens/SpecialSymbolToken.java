package md2html.tokens;

import markup.DelimiterDictionary;
import markup.DelimiterDictionary.OpenCloseness;
import markup.InlineMarkupElement;

public class SpecialSymbolToken extends InlineMarkdownToken {
    public static boolean isImdSpecialSymbol(char c) {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.containsKey(String.valueOf(c));
    }

    public static boolean isImdSpecialSymbolPair(char c1, char c2) {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.containsKey(String.valueOf(c1) + c2);
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

    public Class<? extends InlineMarkupElement> getImdClass() {
        return DelimiterDictionary.inlineMarkupElementByMarkdownDelimiter.get(text).markupClass;
    }

    public boolean canClose(SpecialSymbolToken opener) {
        return opener.getImdClass().equals(this.getImdClass()) &&
            (
                (this.getOpenCloseness() == OpenCloseness.Any && opener.getOpenCloseness() == OpenCloseness.Any && opener.equals(this))
            ) || (
                opener.getOpenCloseness() == OpenCloseness.Opening && this.getOpenCloseness() == OpenCloseness.Closing
            );
    }
}
