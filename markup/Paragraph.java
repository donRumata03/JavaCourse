package markup;

import java.util.List;

public class Paragraph implements SelfContainedMarkupElement {
    List<MarkupElement> children;

    public Paragraph(List<MarkupElement> children) {
        this.children = children;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        for (MarkupElement element: children) {
            element.toMarkdown(stringBuilder);
        }
    }

    @Override
    public void toHtml(StringBuilder builder) {
        for (MarkupElement element: children) {
            element.toHtml(builder);
        }
    }
}
