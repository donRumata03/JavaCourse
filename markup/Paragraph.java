package markup;

import java.util.Collections;
import java.util.List;

public class Paragraph extends SymmetricWrappingMarkupElement implements SelfContainedMarkupElement {
    public Paragraph(List<InlineMarkupElement> children) {
        super(Collections.unmodifiableList(children));
    }

    @Override
    protected String getMarkdownDelimiter() {
        return "";
    }

    @Override
    protected String getHtmlTag() {
        return "p";
    }
}
