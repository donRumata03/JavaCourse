package markup;

import java.util.Map;

public class DelimiterDictionary {
    public enum OpenCloseness {
        Opening,
        Closing,
        Any
    }

    public static class DelimiterData {
        public Class<? extends InlineMarkupElement> markupClass;
        public OpenCloseness openCloseness;

        public DelimiterData(Class<? extends InlineMarkupElement> markupClass, OpenCloseness openCloseness) {
            this.markupClass = markupClass;
            this.openCloseness = openCloseness;
        }
    }

    public static Map<String, DelimiterData> inlineMarkupElementByMarkdownDelimiter = Map.of(
        "--", new DelimiterData(Strikeout.class, OpenCloseness.Any),
        "*", new DelimiterData(Emphasis.class, OpenCloseness.Any),
        "_", new DelimiterData(Emphasis.class, OpenCloseness.Any),
        "**", new DelimiterData(Strong.class, OpenCloseness.Any),
        "__", new DelimiterData(Strong.class, OpenCloseness.Any),
        "`", new DelimiterData(Code.class, OpenCloseness.Any),
        "<<", new DelimiterData(Insertion.class, OpenCloseness.Opening),
        ">>", new DelimiterData(Insertion.class, OpenCloseness.Closing),
        "}}", new DelimiterData(Deleted.class, OpenCloseness.Opening),
        "{{", new DelimiterData(Deleted.class, OpenCloseness.Closing)
    );
}
