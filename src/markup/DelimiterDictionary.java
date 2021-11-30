package markup;

import java.util.List;
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

    public static InlineMarkupElement instantiateImdByDelimiter(String delimiter, List<InlineMarkupElement> children) {
        if (!inlineMarkupElementByMarkdownDelimiter.containsKey(delimiter)) {
            throw new IllegalArgumentException("Delimiter string doesn't correspond to any Inline Markdown Element");
        }

        Class<? extends InlineMarkupElement> selectedClass = inlineMarkupElementByMarkdownDelimiter.get(delimiter).markupClass;

        if (selectedClass == Strikeout.class) {
            return new Strikeout(children);
        } else if (selectedClass == Emphasis.class) {
            return new Emphasis(children);
        } else if(selectedClass == Strong.class) {
            return new Strong(children);
        } else if(selectedClass == Code.class) {
            return new Code(children);
        } else if(selectedClass == Insertion.class) {
            return new Insertion(children);
        } else if(selectedClass == Deleted.class) {
            return new Deleted(children);
        }

        throw new RuntimeException();
    }
}
