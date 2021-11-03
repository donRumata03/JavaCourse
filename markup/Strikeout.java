package markup;

import java.util.List;

public class Strikeout extends WrappingMarkupElement {
    public Strikeout(List<MarkupElement> children) {
        super(children);
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "~";
    }

    @Override
    protected String getHtmlDelimiter() {
        return "s";
    }
}
