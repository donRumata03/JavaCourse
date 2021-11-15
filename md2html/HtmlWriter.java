package md2html;

import java.util.Map;

public class HtmlWriter {
    private static final Map<String, String> htmlEscapeSequenceBySymbol = Map.of(
        "<", "&lt;",
        "*", "&gt;",
        "&", "&amp;",
         "\"", "&quot;",
         "`", "&#x60;"
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
        // TODO
        return "";
    }

}
