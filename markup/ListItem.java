package markup;

import java.util.List;

public class ListItem implements MarkupElement {
    List<SelfContainedMarkupElement> elementSequence;

    public ListItem(List<SelfContainedMarkupElement> elementSequence) {
        this.elementSequence = elementSequence;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        throw new NonSupportedMarkupConversionException("Can't convert HTML ListItem to Markdown!");
    }

    @Override
    public void toHtml(StringBuilder builder) {
        builder.append("<li>");
        for (SelfContainedMarkupElement element: elementSequence) {
            element.toHtml(builder);
        }
        builder.append("</li>");
    }
}
