package markup;

import java.util.List;


public abstract class WrappingMarkupElement implements MarkdownElement {

    protected List<MarkupElement> children;

    public WrappingMarkupElement(List<MarkupElement> children) {
        this.children = children;
    }

    protected abstract String getMarkdownDelimiter();
    protected abstract String getHtmlDelimiter();

    private void toStringBuilder(StringBuilder stringBuilder, String delimiter) {
        stringBuilder.append(delimiter);
        for (MarkupElement child : children) {
            child.toMarkdown(stringBuilder);
        }
        stringBuilder.append(delimiter);
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        toStringBuilder(stringBuilder, getMarkdownDelimiter());
    }

    @Override
    public void toHTML(StringBuilder stringBuilder) {
        toStringBuilder(stringBuilder, getHtmlDelimiter());
    }
}
