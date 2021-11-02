package markup;

import java.util.List;

public class Emphasis extends WrappingMarkupElement implements MarkdownElement {

    public Emphasis(List<MarkdownWrapableElement> children) {
        super(children);
    }

    @Override
    protected String getOpener() {
        return "*";
    }

    @Override
    protected String getCloser() {
        return "*";
    }
}
