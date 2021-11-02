package markup;

public interface MarkupElement {
    void toMarkdown(StringBuilder stringBuilder);
    void toHTML(StringBuilder builder);
}
