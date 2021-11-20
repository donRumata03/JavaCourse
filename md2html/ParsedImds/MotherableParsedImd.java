package md2html.ParsedImds;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import markup.InlineMarkupElement;

public abstract class MotherableParsedImd extends ParsedInlineMarkdown {
    protected List<ParsedInlineMarkdown> children;

    public MotherableParsedImd(MotherableParsedImd parent, List<ParsedInlineMarkdown> children) {
        super(Optional.of(parent));
        this.children = children;
    }

    @Override
    public List<InlineMarkupElement> childrenAsImdElements() {
        return children.stream().map(ParsedInlineMarkdown::toInlineMarkdownElement).collect(Collectors.toList());
    }
}
