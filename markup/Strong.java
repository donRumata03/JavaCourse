package markup;

import java.util.List;

public class Strong extends MarkdownWrappingElement implements MarkdownWrapableElement {

    public Strong(List<MarkdownWrapableElement> children) {
        super(children);
    }

    @Override
    protected String getOpener() {
        return "__";
    }

    @Override
    protected String getCloser() {
        return "__";
    }
}

