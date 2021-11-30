package markup;

import java.util.Map;
import java.util.stream.Collectors;

public class HtmlEscaper {
    private static final Map<Character, String> htmlEscapeSequenceBySymbol = Map.of(
        '<', "&lt;",
        '>', "&gt;",
        '&', "&amp;",
        '"', "&quot;",
        '`', "&#x60;"
    );

    /**
     * < → &lt;
     *
     * > → &gt;
     *
     * & → &amp;
     *
     * " → &quot;
     *
     * ` → &#x60;
     */
    static String escapeText(String text) {
        // More efficient than multiple replace method calls:
        return text.codePoints()
            .mapToObj((int c) -> htmlEscapeSequenceBySymbol.getOrDefault((char)c, String.valueOf((char)c)))
            .map(Object::toString)
            .collect(Collectors.joining());
    }

}
