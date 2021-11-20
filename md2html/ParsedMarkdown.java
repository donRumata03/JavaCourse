package md2html;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import markup.Header;
import markup.MarkupElement;
import markup.Paragraph;
import markup.SelfContainedMarkupElement;

public class ParsedMarkdown {
    List<SelfContainedMarkupElement> blockList = new ArrayList<>();

    public void absorbStringBlock(String block) {
        // A header or a paragraph?

        int shouldBeAtSpace = StringUtils.findFirstNot(block, '#');
        if (
            shouldBeAtSpace <= block.length() - 2 &&
                block.charAt(shouldBeAtSpace) == ' ' &&
                shouldBeAtSpace > 0 &&
                shouldBeAtSpace <= 6
        ) {
            // For header: at least 1 character after space; <= 6 '#'s
            blockList.add(new Header(
                ParsedInlineMarkdown.parseString(block.substring(shouldBeAtSpace + 1))
                    .childrenAsImdElements(), shouldBeAtSpace
            ));
        } else {
            // Paragraph:
            blockList.add(new Paragraph(ParsedInlineMarkdown.parseString(block).childrenAsImdElements()));
        }
    }

    @Override
    public String toString() {
        return blockList.stream()
            .map(MarkupElement::toHtml)
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
    }
}
