package markup;

import java.util.List;

public class ListItem extends WrappingMarkupElement {
    public ListItem(List<SelfContainedMarkupElement> elementSequence) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) elementSequence);
    }

    @Override
    protected String getMarkdownDelimiter() {
        throw new NonSupportedMarkupConversionException(
            "Can't convert list element to Markdown (only HTML is supported)"
        );
    }

    @Override
    protected String getHtmlDelimiter() {
        return "li";
    }
}