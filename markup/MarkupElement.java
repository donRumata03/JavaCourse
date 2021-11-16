package markup;

public interface MarkupElement {
    void toMarkdown(StringBuilder stringBuilder);
    void toHtml(StringBuilder builder);

    default String toMarkdown() {
        StringBuilder builder = new StringBuilder();
        toMarkdown(builder);
        return builder.toString();
    }

    default String toHtml() {
        StringBuilder builder = new StringBuilder();
        toHtml(builder);
        return builder.toString();
    }
}
