package markup;

import java.util.List;

public class Paragraph extends WrappingMarkupElement {

    public Paragraph(List<MarkdownWrapableElement> children) {
        super(children);
    }

    @Override
    protected String getOpener() {
        return "";
    }

    @Override
    protected String getCloser() {
        return "";
    }
}
