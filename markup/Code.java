package markup;

import java.util.List;

public class Code extends SymmetricWrappingMarkupElement implements InlineMarkupElement {

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
