package markup;

import java.util.List;

public class Strikeout extends WrappingMarkupElement implements InlineMarkupElement {
    public Strikeout(List<InlineMarkupElement> children) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) children);
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "~";
    }

    @Override
    protected String getHtmlDelimiter() {
        return "s";
    }
}
