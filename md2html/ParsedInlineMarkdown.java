package md2html;

import java.util.List;
import markup.InlineMarkupElement;
import markup.MarkupElement;
import markup.Text;

public class ParsedInlineMarkdown {
    public List<InlineMarkupElement> children;

    public static List<InlineMarkupElement> parseString(String source) {
        return List.of(new Text(source));
    }
}
