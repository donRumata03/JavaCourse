package md2html.ParsedImds;

import java.util.List;
import java.util.Optional;
import markup.InlineMarkdownGrouper;
import markup.InlineMarkupElement;

public class GrouperParsedImd extends MotherableParsedImd {

    public GrouperParsedImd(MotherableParsedImd parent, List<ParsedInlineMarkdown> children) {
        super(Optional.of(parent), children);
    }

    @Override
    public InlineMarkupElement toInlineMarkdownElement() {
        return new InlineMarkdownGrouper(childrenAsImdElements());
    }
}
