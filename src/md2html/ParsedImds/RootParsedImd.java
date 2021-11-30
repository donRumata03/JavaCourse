package md2html.ParsedImds;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import markup.InlineMarkupElement;

public class RootParsedImd extends MotherableParsedImd {

    public RootParsedImd(List<ParsedInlineMarkdown> children) {
        super(Optional.empty(), children);
    }

    public RootParsedImd() {
        this(new ArrayList<>());
    }

    @Override
    public InlineMarkupElement toInlineMarkdownElement() {
        throw new RuntimeException();
    }
}
