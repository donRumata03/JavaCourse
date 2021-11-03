package markup;

public interface MarkupElement {
    void toMarkdown(StringBuilder stringBuilder);
    void toHtml(StringBuilder builder);
}
