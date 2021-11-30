package markup;

import java.util.List;

public abstract class SymmetricWrappingMarkupElement extends WrappingMarkupElement {

    public SymmetricWrappingMarkupElement(List<MarkupElement> children) {
        super(children);
    }

    protected abstract String getMarkdownDelimiter();


    @Override
    protected String getMarkdownOpener() {
        return getMarkdownDelimiter();
    }

    @Override
    protected String getMarkdownCloser() {
        return getMarkdownDelimiter();
    }
}
