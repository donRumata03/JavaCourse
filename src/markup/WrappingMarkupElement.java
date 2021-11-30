package markup;

import java.util.List;
import java.util.function.BiConsumer;


public abstract class WrappingMarkupElement implements MarkupElement {

    protected List<MarkupElement> children;

    public WrappingMarkupElement(List<MarkupElement> children) {
        this.children = children;
    }

    protected abstract String getHtmlTag();
    protected abstract String getMarkdownOpener();
    protected abstract String getMarkdownCloser();


    private void toStringBuilder(StringBuilder stringBuilder, BiConsumer<MarkupElement, StringBuilder> renderGetter,
        String opener, String closer)
    {
        stringBuilder.append(opener);
        for (MarkupElement child : children) {
            renderGetter.accept(child, stringBuilder);
        }
        stringBuilder.append(closer);
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        toStringBuilder(stringBuilder, MarkupElement::toMarkdown, getMarkdownOpener(), getMarkdownCloser());
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        String delimiter = getHtmlTag();

        toStringBuilder(
            stringBuilder, MarkupElement::toHtml,
            delimiter.isEmpty() ? "" : "<" + delimiter + ">",
            delimiter.isEmpty() ? "" : "</" + delimiter + ">"
        );
    }
}
