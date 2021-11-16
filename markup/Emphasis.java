package markup;

import java.util.Collections;
import java.util.List;

public class Emphasis extends SymmetricWrappingMarkupElement implements InlineMarkupElement {
    public Emphasis(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "*";
    }

    @Override
    protected String getHtmlTag() {
        return "em";
    }
}
