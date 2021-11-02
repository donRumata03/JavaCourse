package markup;

import java.util.List;


public abstract class HtmlList implements MarkupElement {
    protected abstract String getTag();

    protected List<ListItem> items;

    public HtmlList(List<ListItem> items) {
        this.items = items;
    }


    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        throw new NonSupportedMarkupConversionException("Can't convert list to Markdown (only HTML is supported)");
    }

    @Override
    public void toHTML(StringBuilder builder) {
        builder.append();
    }
}
