package md2html.tokens;

import java.util.Objects;

public abstract class InlineMarkdownToken {
    // …
    protected String text;

    protected InlineMarkdownToken(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    public boolean equals(InlineMarkdownToken that) {
        if (this == that) {
            return true;
        }

        return
            this.getClass().equals(that.getClass()) &&
                this.text.equals(that.text);
    }
}
