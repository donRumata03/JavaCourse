package md2html.ParsedImds;

import java.util.List;
import java.util.stream.Collectors;
import markup.InlineMarkupElement;
import md2html.ParsedInlineMarkdown;

public abstract class MotherableParsedImd extends ParsedInlineMarkdown {
    protected List<ParsedInlineMarkdown> children;

    public MotherableParsedImd(MotherableParsedImd parent, List<ParsedInlineMarkdown> children) {
        super(parent);
        this.children = children;
    }

    @Override
    public List<InlineMarkupElement> childrenAsImdElements() {
        return children.stream().map(ParsedInlineMarkdown::toInlineMarkdownElement).collect(Collectors.toList());
    }
}
