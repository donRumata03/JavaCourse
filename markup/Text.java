package markup;

public class Text implements MarkupElement {
    private final String text;


    Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    @Override
    public void toHtml(StringBuilder builder) {
        builder.append(text);
    }
}
