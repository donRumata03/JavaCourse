package markup;

import java.util.Map;

public class DelimiterDictionary {
    enum OpenCloseness {
        Opening,
        Closing,
        Any
    }

    public static Map<String, Class<? extends InlineMarkupElement>> inlineMarkupElementByMarkdownDelimiter = Map.of(
        "--", Strikeout.class,
        "*", Emphasis.class,
        "_", Emphasis.class,
        "**", Strong.class,
        "__", Strong.class,
        "`", Code.class
    );

    public static Map<String, OpenCloseness> OpenClosenessByMarkdownDelimiter = Map.of(
        "--", OpenCloseness.Any,
        "*", OpenCloseness.Any,
        "_", OpenCloseness.Any,
        "**", OpenCloseness.Any,
        "__", OpenCloseness.Any,
        "`", OpenCloseness.Any
    );
}
