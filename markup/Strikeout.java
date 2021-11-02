package markup;

import java.util.List;

public class Strikeout extends WrappingMarkupElement implements MarkdownElement {

    public Strikeout(List<MarkdownWrapableElement> children) {
        super(children);
    }

    @Override
    protected String getOpener() {
        return "~";
    }

    @Override
    protected String getCloser() {
        return "~";
    }
}
