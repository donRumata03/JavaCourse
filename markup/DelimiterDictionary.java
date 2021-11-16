package markup;

import java.util.Map;

public class DelimiterDictionary {
    public static Map<String, Class<? extends InlineMarkupElement>> inlineMarkupElementByMarkdownDelimiter = Map.of(
        "--", Strikeout.class,
        "*", Emphasis.class,
        "_", Emphasis.class,
        "**", Strong.class,
        "__", Strong.class,
        "`", Code.class
    );
}
