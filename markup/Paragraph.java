package markup;

import java.util.List;

public class Paragraph extends WrappingMarkupElement implements SelfContainedMarkupElement {
    public Paragraph(List<InlineMarkupElement> children) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) children);
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "";
    }

    @Override
    protected String getHtmlDelimiter() {
        return "p";
    }
}
