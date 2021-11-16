package markup;

import java.util.List;

public class Code extends WrappingMarkupElement implements InlineMarkupElement {

    public Code(List<InlineMarkupElement> children) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) children);
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "`";
    }

    @Override
    protected String getHtmlTag() {
        return "code";
    }
}
