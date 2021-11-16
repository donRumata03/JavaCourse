package markup;

import java.util.List;


public class Header extends WrappingMarkupElement implements SelfContainedMarkupElement {
    int headerLevel;

    public Header(List<InlineMarkupElement> children, int headerLevel) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) children);
        this.headerLevel = headerLevel;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append("#".repeat(headerLevel));

        for (MarkupElement e: children) {
            e.toMarkdown(stringBuilder);
        }
    }

    @Override
    protected String getMarkdownOpener() {
        throw new NonSupportedMarkupConversionException("Method is implemented another way");
    }
    @Override
    protected String getMarkdownCloser() {
        throw new NonSupportedMarkupConversionException("Method is implemented another way");
    }

    @Override
    protected String getHtmlTag() {
        return "h" + headerLevel;
    }
}
