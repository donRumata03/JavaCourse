package markup;

import java.util.Collections;
import java.util.List;

public class Paragraph extends SymmetricWrappingMarkupElement implements SelfContainedMarkupElement {
    private final boolean includeTag;

    public Paragraph(List<InlineMarkupElement> children, boolean includeTag) {
        super(Collections.unmodifiableList(children));
        this.includeTag = includeTag;
    }

    public Paragraph(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
        this.includeTag = false;
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "";
    }

    @Override
    protected String getHtmlTag() {
        return includeTag ? "p" : "";
    }
}
