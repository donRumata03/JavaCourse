package markup;

import java.util.List;

public class Emphasis extends WrappingMarkupElement implements InlineMarkupElement {
    public Emphasis(List<InlineMarkupElement> children) {
        super(children);
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "*";
    }

    @Override
    protected String getHtmlDelimiter() {
        return "em";
    }
}
