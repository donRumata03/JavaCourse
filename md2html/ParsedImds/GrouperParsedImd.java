package md2html.ParsedImds;

import java.util.List;
import markup.InlineMarkdownGrouper;
import markup.InlineMarkupElement;

public class GrouperParsedImd extends MotherableParsedImd {

    public GrouperParsedImd(MotherableParsedImd parent, List<ParsedInlineMarkdown> children) {
        super(parent, children);
    }

    @Override
    public InlineMarkupElement toInlineMarkdownElement() {
        return new InlineMarkdownGrouper(childrenAsImdElements());
    }
}
