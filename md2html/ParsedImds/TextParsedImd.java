package md2html.ParsedImds;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import markup.InlineMarkupElement;
import markup.Text;
import md2html.ParsedInlineMarkdown;

public class TextParsedImd extends ParsedInlineMarkdown {
    protected String text;

    TextParsedImd(Optional<MotherableParsedImd> parent, String text) {
        super(parent);
        this.text = text;
    }
    TextParsedImd(MotherableParsedImd parent, String text) {
        this(Optional.of(parent), text);
    }

    @Override
    protected List<InlineMarkupElement> childrenAsImdElements() {
        return new ArrayList<>();
    }

    @Override
    public InlineMarkupElement toInlineMarkdownElement() {
        return new Text(text);
    }
}
