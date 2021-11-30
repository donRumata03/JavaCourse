package markup;

import java.util.Collections;
import java.util.List;

public class ListItem extends SymmetricWrappingMarkupElement {
    public ListItem(List<SelfContainedMarkupElement> elementSequence) {
        super(Collections.unmodifiableList(elementSequence));
    }

    @Override
    protected String getMarkdownDelimiter() {
        throw new NonSupportedMarkupConversionException(
            "Can't convert list element to Markdown (only HTML is supported)"
        );
    }

    @Override
    protected String getHtmlTag() {
        return "li";
    }
}
