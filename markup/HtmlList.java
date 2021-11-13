package markup;

import java.util.List;


public abstract class HtmlList extends WrappingMarkupElement implements SelfContainedMarkupElement {

    public HtmlList(List<ListItem> items) {
        super((List<MarkupElement>)(List<? extends MarkupElement>) items);
    }

    @Override
    protected String getMarkdownDelimiter() {
        throw new NonSupportedMarkupConversionException(
            "Can't convert list to Markdown (only HTML is supported)"
        );
    }
}
