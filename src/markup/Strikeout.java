package markup;

import java.util.Collections;
import java.util.List;

public class Strikeout extends SymmetricWrappingMarkupElement implements InlineMarkupElement {
    public Strikeout(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "~";
    }

    @Override
    protected String getHtmlTag() {
        return "s";
    }
}
