package md2html;

import static md2html.StringUtils.findFirstNot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import markup.Header;
import markup.MarkupElement;
import markup.Paragraph;
import markup.SelfContainedMarkupElement;

public class ParsedMarkdown {
    List<? extends SelfContainedMarkupElement> blockList = new ArrayList<>();

    public void absorbStringBlock(String block) {
        // A header or a paragraph?

        int shouldBeAtSpace = StringUtils.findFirstNot(block, '#');
        if (shouldBeAtSpace <= block.length() - 2 && block.charAt(shouldBeAtSpace) == ' ' && shouldBeAtSpace < 6) {
            // For header: at least 1 character after space; <= 6 '#'s
            blockList.add(new Header(…parse…(block.substring(shouldBeAtSpace + 1))));
        } else {
            // Paragraph:
            blockList.add(new Paragraph(…parse…(block)));
        }
    }

    @Override
    public String toString() {
        return blockList.stream()
            .map((Function<SelfContainedMarkupElement, String>) MarkupElement::toHtml)
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
    }
}