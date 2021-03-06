package markup;

import java.util.Collections;
import java.util.List;

public class Strong extends SymmetricWrappingMarkupElement implements InlineMarkupElement {
    public Strong(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "__";
    }

    @Override
    protected String getHtmlTag() {
        return "strong";
    }
}

