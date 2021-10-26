package markup;

public class Text implements MarkdownWrapableElement {
    private final String text;


    Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }
}
