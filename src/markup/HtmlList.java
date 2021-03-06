package markup;

import java.util.List;
import markup.ListItem;
import markup.MarkupElement;
import markup.NonSupportedMarkupConversionException;
import markup.SelfContainedMarkupElement;
import markup.SymmetricWrappingMarkupElement;
import markup.WrappingMarkupElement;


public abstract class HtmlList extends SymmetricWrappingMarkupElement implements SelfContainedMarkupElement {

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
