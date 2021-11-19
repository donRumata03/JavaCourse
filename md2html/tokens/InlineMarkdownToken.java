package md2html.tokens;

public abstract class InlineMarkdownToken {
    // …
    protected String text;

    protected InlineMarkdownToken(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
