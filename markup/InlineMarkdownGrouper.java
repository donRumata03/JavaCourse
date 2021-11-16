package markup;

import java.util.Collections;
import java.util.List;

public class InlineMarkdownGrouper extends WrappingMarkupElement implements InlineMarkupElement {

    public InlineMarkdownGrouper(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "";
    }

    @Override
    protected String getHtmlDelimiter() {
        return "";
    }
}
