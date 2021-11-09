package markup;

import java.util.List;

public class Paragraph implements SelfContainedMarkupElement {
    List<InlineMarkupElement> children;

    public Paragraph(List<InlineMarkupElement> children) {
        this.children = children;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        for (InlineMarkupElement element: children) {
            element.toMarkdown(stringBuilder);
        }
    }

    @Override
    public void toHtml(StringBuilder builder) {
        for (InlineMarkupElement element: children) {
            element.toHtml(builder);
        }
    }
}
