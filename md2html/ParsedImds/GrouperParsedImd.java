package md2html.ParsedImds;

import java.util.List;
import markup.InlineMarkdownGrouper;
import markup.InlineMarkupElement;
import md2html.ParsedInlineMarkdown;

public class GrouperParsedImd extends MotherableParsedImd {

    public GrouperParsedImd(MotherableParsedImd parent, List<ParsedInlineMarkdown> children) {
        super(parent, children);
    }

    @Override
    public InlineMarkupElement toInlineMarkdownElement() {
        return new InlineMarkdownGrouper(childrenAsImdElements());
    }
}
