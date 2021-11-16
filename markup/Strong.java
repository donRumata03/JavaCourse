package markup;

import java.util.List;

public class Strong extends WrappingMarkupElement implements InlineMarkupElement {
    public Strong(List<InlineMarkupElement> children) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) children);
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "__";
    }

    @Override
    protected String getHtmlDelimiter() {
        return "strong";
    }
}
