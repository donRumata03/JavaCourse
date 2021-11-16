package markup;

import java.util.List;

public class Emphasis extends WrappingMarkupElement implements InlineMarkupElement {
    public Emphasis(List<InlineMarkupElement> children) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) children);
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "*";
    }

    @Override
    protected String getHtmlTag() {
        return "em";
    }
}
