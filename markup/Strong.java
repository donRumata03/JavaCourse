package markup;

import java.util.List;

public class Strong extends WrappingMarkupElement {
    public Strong(List<InlineMarkupElement> children) {
        super(children);
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

